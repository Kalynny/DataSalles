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
import br.com.datasalles.domain.Cliente;
import br.com.datasalles.domain.Fornecedor;
import br.com.datasalles.domain.Funcionario;
import br.com.datasalles.domain.ItemPedCompra;
import br.com.datasalles.domain.PedCompra;
import br.com.datasalles.domain.Produto;


@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PedCompraBean implements Serializable {
	
	private PedCompra pedcompra;
	private List<Produto> produtos;
	private List<ItemPedCompra> itenspedcompra;
	private List<Cliente> clientes;
	private List<Fornecedor> fornecedores;
	private List<Funcionario> funcionarios;
	private List<PedCompra> pedcompras;

	public PedCompra getPedcompra() {
		return pedcompra;
	}

	public void setPedcompra(PedCompra pedcompra) {
		this.pedcompra = pedcompra;
	}

	public List<ItemPedCompra> getItenspedcompra() {
		return itenspedcompra;
	}

	public void setItenspedcompra(List<ItemPedCompra> itenspedcompra) {
		this.itenspedcompra = itenspedcompra;
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

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	@PostConstruct
	public void novo() {
		try {
			pedcompra = new PedCompra();
			pedcompra.setPrecoTotal(new BigDecimal("0.00"));

			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar("descricao");

			itenspedcompra = new ArrayList<>();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar carregar a tela de Pedido de Compras");
			erro.printStackTrace();
		}
	}

	public void adicionar(ActionEvent evento) {
		Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

		int achou = -1;
		for (int posicao = 0; posicao < itenspedcompra.size(); posicao++) {
			if (itenspedcompra.get(posicao).getProduto().equals(produto)) {
				achou = posicao;
			}
		}

		if (achou < 0) {
			ItemPedCompra itempedcompra = new ItemPedCompra();
			itempedcompra.setPrecoParcial(produto.getPreco());
			itempedcompra.setProduto(produto);
			itempedcompra.setQuantidade(new Short("1"));

			itenspedcompra.add(itempedcompra);
		} else {
			ItemPedCompra itempedcompra = itenspedcompra.get(achou);
			itempedcompra.setQuantidade(new Short(itempedcompra.getQuantidade() + 1 + ""));
			itempedcompra.setPrecoParcial(produto.getPreco().multiply(new BigDecimal(itempedcompra.getQuantidade())));
		}

		calcular();
	}
	
	public void subtrair(ActionEvent evento) {
		Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

		int achou = -1;
		for (int posicao = 0; posicao < itenspedcompra.size(); posicao++) {
			if (itenspedcompra.get(posicao).getProduto().equals(produto)) {
				achou = posicao;
			}
		}

		if (achou < 0) {
			ItemPedCompra itempedcompra = new ItemPedCompra();
			itempedcompra.setPrecoParcial(produto.getPreco());
			itempedcompra.setProduto(produto);
			itempedcompra.setQuantidade(new Short("1"));

			itenspedcompra.add(itempedcompra);
		} else {
			ItemPedCompra itempedcompra = itenspedcompra.get(achou);
			itempedcompra.setQuantidade(new Short(itempedcompra.getQuantidade() - 1 + ""));
			itempedcompra.setPrecoParcial(produto.getPreco().multiply(new BigDecimal(itempedcompra.getQuantidade())));
		}

		calcular();
	}

	public void remover(ActionEvent evento) {
		ItemPedCompra itempedcompra = (ItemPedCompra) evento.getComponent().getAttributes().get("itemSelecionado");

		int achou = -1;
		for (int posicao = 0; posicao < itenspedcompra.size(); posicao++) {
			if (itenspedcompra.get(posicao).getProduto().equals(itempedcompra.getProduto())) {
				achou = posicao;
			}
		}

		if (achou > -1) {
			itenspedcompra.remove(achou);
		}

		calcular();
	}

	public void calcular() {
		pedcompra.setPrecoTotal(new BigDecimal("0.00"));

		for (int posicao = 0; posicao < itenspedcompra.size(); posicao++) {
			ItemPedCompra itempedcompra = itenspedcompra.get(posicao);
			pedcompra.setPrecoTotal(pedcompra.getPrecoTotal().add(itempedcompra.getPrecoParcial()));
		}
	}

	public void finalizar() {
		try {
			pedcompra.setAtual(new Date());
			pedcompra.setFornecedor(null);
			pedcompra.setFuncionario(null);
			
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listarOrdenado();
			
			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			fornecedores = fornecedorDAO.listar();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar finalizar o Pedido de Compras");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			if(pedcompra.getPrecoTotal().signum() == 0){
				Messages.addGlobalError("Informe pelo menos um item para o Pedido de Compras");
				return;
			}
			
			PedCompraDAO pedcompraDAO = new PedCompraDAO();
			pedcompraDAO.salvar(pedcompra, itenspedcompra);
			
			pedcompra = new PedCompra();
			pedcompra.setPrecoTotal(new BigDecimal("0.00"));

			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar("descricao");

			itenspedcompra = new ArrayList<>();
			
			Messages.addGlobalInfo("Pedido de Compras realizada com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o Pedido de Compras");
			erro.printStackTrace();
		}
	}
	
	public void listar(){
		PedCompraDAO dao = new PedCompraDAO();
		pedcompras = dao.listar("codigo");
	}
	
	public void atualizarPrecoParcial(){
		for(ItemPedCompra itempedcompra : this.itenspedcompra){
		itempedcompra.setPrecoParcial(itempedcompra.getProduto().getPreco().multiply(new BigDecimal(itempedcompra.getQuantidade())));
		}
		this.calcular();
	}
	
}