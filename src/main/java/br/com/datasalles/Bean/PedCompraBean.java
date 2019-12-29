package br.com.datasalles.Bean;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;
import br.com.datasalles.dao.FornecedorDAO;
import br.com.datasalles.dao.FuncionarioDAO;
import br.com.datasalles.dao.PedCompraDAO;
import br.com.datasalles.dao.ProdutoDAO;
import br.com.datasalles.domain.Fornecedor;
import br.com.datasalles.domain.Funcionario;
import br.com.datasalles.domain.ItemPedCompra;
import br.com.datasalles.domain.Produto;
import br.com.datasalles.domain.PedCompra;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PedCompraBean implements Serializable {
	
	private PedCompra pedcompra;
	private List<Produto> produtos;
	private List<ItemPedCompra> itensPedcompra;
	private List<Fornecedor> fornecedores;
	private List<Funcionario> funcionarios;
	private List<PedCompra> pedcompras;
	
	public PedCompra getPedcompra() {
		return pedcompra;
	}

	public void setPedcompra(PedCompra pedcompra) {
		this.pedcompra = pedcompra;
	}

	public List<ItemPedCompra> getItensPedcompra() {
		return itensPedcompra;
	}

	public void setItensPedcompra(List<ItemPedCompra> itensPedcompra) {
		this.itensPedcompra = itensPedcompra;
	}

	public List<PedCompra> getPedcompras() {
		return pedcompras;
	}

	public void setPedcompras(List<PedCompra> pedcompras) {
		this.pedcompras = pedcompras;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	@PostConstruct
	public void novo() {
		try {
			pedcompra = new PedCompra();
			pedcompra.setPrecoTotal(new BigDecimal("0.00"));

			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar("descricao");

			itensPedcompra = new ArrayList<>();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar carregar a tela de PedCompras");
			erro.printStackTrace();
		}
	}

	public void adicionar(ActionEvent evento) {
		Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

		int achou = -1;
		for (int posicao = 0; posicao < itensPedcompra.size(); posicao++) {
			if (itensPedcompra.get(posicao).getProduto().equals(produto)) {
				achou = posicao;
			}
		}

		if (achou < 0) {
			ItemPedCompra itemPedcompra = new ItemPedCompra();
			itemPedcompra.setPrecoParcial(produto.getPreco());
			itemPedcompra.setProduto(produto);
			itemPedcompra.setQuantidade(new Short("1"));

			itensPedcompra.add(itemPedcompra);
		} else {
			ItemPedCompra itemPedCompra = itensPedcompra.get(achou);
			itemPedCompra.setQuantidade(new Short(itemPedCompra.getQuantidade() + 1 + ""));
			itemPedCompra.setPrecoParcial(produto.getPreco().multiply(new BigDecimal(itemPedCompra.getQuantidade())));
		}

		calcular();
	}
	
	public void subtrair(ActionEvent evento) {
		Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

		int achou = -1;
		for (int posicao = 0; posicao < itensPedcompra.size(); posicao++) {
			if (itensPedcompra.get(posicao).getProduto().equals(produto)) {
				achou = posicao;
			}
		}

		if (achou < 0) {
			ItemPedCompra itemPedCompra = new ItemPedCompra();
			itemPedCompra.setPrecoParcial(produto.getPreco());
			itemPedCompra.setProduto(produto);
			itemPedCompra.setQuantidade(new Short("1"));

			itensPedcompra.add(itemPedCompra);
		} else {
			ItemPedCompra itemPedCompra = itensPedcompra.get(achou);
			itemPedCompra.setQuantidade(new Short(itemPedCompra.getQuantidade() - 1 + ""));
			itemPedCompra.setPrecoParcial(produto.getPreco().multiply(new BigDecimal(itemPedCompra.getQuantidade())));
		}

		calcular();
	}

	public void remover(ActionEvent evento) {
		ItemPedCompra itemPedCompra = (ItemPedCompra) evento.getComponent().getAttributes().get("itemSelecionado");

		int achou = -1;
		for (int posicao = 0; posicao < itensPedcompra.size(); posicao++) {
			if (itensPedcompra.get(posicao).getProduto().equals(itemPedCompra.getProduto())) {
				achou = posicao;
			}
		}

		if (achou > -1) {
			itensPedcompra.remove(achou);
		}

		calcular();
	}

	public void calcular() {
		pedcompra.setPrecoTotal(new BigDecimal("0.00"));

		for (int posicao = 0; posicao < itensPedcompra.size(); posicao++) {
			ItemPedCompra itemPedCompra = itensPedcompra.get(posicao);
			pedcompra.setPrecoTotal(pedcompra.getPrecoTotal().add(itemPedCompra.getPrecoParcial()));
		}
	}

	public void finalizar() {
		try {
			pedcompra.setHorario(new Date());
			pedcompra.setFornecedor(null);
			pedcompra.setFuncionario(null);
			
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listarOrdenado();

			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			fornecedores = fornecedorDAO.listarOrdenado();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar finalizar a pedcompra");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			if(pedcompra.getPrecoTotal().signum() == 0){
				Messages.addGlobalError("Informe pelo menos um item para a pedcompra");
				return;
			}
			
			PedCompraDAO pedcompraDAO = new PedCompraDAO();
			pedcompraDAO.salvar(pedcompra, itensPedcompra);
			
			pedcompra = new PedCompra();
			pedcompra.setPrecoTotal(new BigDecimal("0.00"));

			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar("descricao");

			itensPedcompra = new ArrayList<>();
			
			Messages.addGlobalInfo("Pedido de Compras realizada com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a orçamento");
			erro.printStackTrace();
		}
	}
	
	public void listar(){
		PedCompraDAO dao = new PedCompraDAO();
		pedcompras = dao.listar("codigo");
	}
}