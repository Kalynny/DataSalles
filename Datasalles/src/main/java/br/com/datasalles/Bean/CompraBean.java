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
import br.com.datasalles.dao.FuncionarioDAO;
import br.com.datasalles.dao.ProdutoDAO;
import br.com.datasalles.dao.CompraDAO;
import br.com.datasalles.dao.FornecedorDAO;
import br.com.datasalles.domain.Funcionario;
import br.com.datasalles.domain.ItemCompra;
import br.com.datasalles.domain.Produto;
import br.com.datasalles.domain.Compra;
import br.com.datasalles.domain.Fornecedor;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CompraBean implements Serializable {
	private Compra compra;
	private List<Produto> produtos;
	private List<ItemCompra> itensCompra;
	private List<Fornecedor> fornecedores;
	private List<Funcionario> funcionarios;

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<ItemCompra> getItensCompra() {
		return itensCompra;
	}

	public void setItensCompra(List<ItemCompra> itensCompra) {
		this.itensCompra = itensCompra;
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
			compra = new Compra();
			compra.setPrecoTotal(new BigDecimal("0.00"));

			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar("descricao");

			itensCompra = new ArrayList<>();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar carregar a tela de compras");
			erro.printStackTrace();
		}
	}

	public void adicionar(ActionEvent evento) {
		Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

		int achou = -1;
		for (int posicao = 0; posicao < itensCompra.size(); posicao++) {
			if (itensCompra.get(posicao).getProduto().equals(produto)) {
				achou = posicao;
			}
		}

		if (achou < 0) {
			ItemCompra itemCompra = new ItemCompra();
			itemCompra.setPrecoParcial(produto.getPreco());
			itemCompra.setProduto(produto);
			itemCompra.setQuantidade(new Short("1"));

			itensCompra.add(itemCompra);
		} else {
			ItemCompra itemCompra = itensCompra.get(achou);
			itemCompra.setQuantidade(new Short(itemCompra.getQuantidade() + 1 + ""));
			itemCompra.setPrecoParcial(produto.getPreco().multiply(new BigDecimal(itemCompra.getQuantidade())));
		}

		calcular();
	}
	
	public void subtrair(ActionEvent evento) {
		Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

		int achou = -1;
		for (int posicao = 0; posicao < itensCompra.size(); posicao++) {
			if (itensCompra.get(posicao).getProduto().equals(produto)) {
				achou = posicao;
			}
		}

		if (achou < 0) {
			ItemCompra itemCompra = new ItemCompra();
			itemCompra.setPrecoParcial(produto.getPreco());
			itemCompra.setProduto(produto);
			itemCompra.setQuantidade(new Short("1"));

			itensCompra.add(itemCompra);
		} else {
			ItemCompra itemCompra = itensCompra.get(achou);
			itemCompra.setQuantidade(new Short(itemCompra.getQuantidade() - 1 + ""));
			itemCompra.setPrecoParcial(produto.getPreco().multiply(new BigDecimal(itemCompra.getQuantidade())));
		}

		calcular();
	}
	

	public void remover(ActionEvent evento) {
		ItemCompra itemCompra = (ItemCompra) evento.getComponent().getAttributes().get("itemSelecionado");

		int achou = -1;
		for (int posicao = 0; posicao < itensCompra.size(); posicao++) {
			if (itensCompra.get(posicao).getProduto().equals(itemCompra.getProduto())) {
				achou = posicao;
			}
		}

		if (achou > -1) {
			itensCompra.remove(achou);
		}

		calcular();
	}
	
	

	public void calcular() {
		compra.setPrecoTotal(new BigDecimal("0.00"));

		for (int posicao = 0; posicao < itensCompra.size(); posicao++) {
			ItemCompra itemCompra = itensCompra.get(posicao);
			compra.setPrecoTotal(compra.getPrecoTotal().add(itemCompra.getPrecoParcial()));
		}
	}

	public void finalizar() {
		try {
			compra.setHorario(new Date());
			compra.setFornecedor(null);
			compra.setFuncionario(null);
			
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listarOrdenado();

			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			fornecedores = fornecedorDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar finalizar a compra");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			if(compra.getPrecoTotal().signum() == 0){
				Messages.addGlobalError("Informe pelo menos um item para a compra");
				return;
			}
			
			CompraDAO compraDAO = new CompraDAO();
			compraDAO.salvar(compra, itensCompra);
			
			compra = new Compra();
			compra.setPrecoTotal(new BigDecimal("0.00"));

			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar("descricao");

			itensCompra = new ArrayList<>();
			
			Messages.addGlobalInfo("Compra realizada com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a compra");
			erro.printStackTrace();
		}
	}
}