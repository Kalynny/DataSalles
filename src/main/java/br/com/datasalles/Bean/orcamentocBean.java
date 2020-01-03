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
import br.com.datasalles.dao.ProdutoDAO;
import br.com.datasalles.dao.OrcamentoCDAO;
import br.com.datasalles.domain.Fornecedor;
import br.com.datasalles.domain.Funcionario;
import br.com.datasalles.domain.ItemOrcaC;
import br.com.datasalles.domain.Produto;
import br.com.datasalles.domain.OrcamentoC;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class orcamentocBean implements Serializable {
	
	private OrcamentoC orcamentoc;
	private List<Produto> produtos;
	private List<ItemOrcaC> itensOrcaC;
	private List<Fornecedor> fornecedores;
	private List<Funcionario> funcionarios;
	private List<OrcamentoC> orcamentocs;

	public OrcamentoC getOrcamentoC() {
		return orcamentoc;
	}

	public void setOrcamentoC(OrcamentoC orcamentoc) {
		this.orcamentoc = orcamentoc;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<ItemOrcaC> getItensOrcaC() {
		return itensOrcaC;
	}

	public void setItensOrcaC(List<ItemOrcaC> itensOrcaC) {
		this.itensOrcaC = itensOrcaC;
	}
	
	public OrcamentoC getOrcamentoc() {
		return orcamentoc;
	}

	public void setOrcamentoc(OrcamentoC orcamentoc) {
		this.orcamentoc = orcamentoc;
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public List<OrcamentoC> getOrcamentocs() {
		return orcamentocs;
	}

	public void setOrcamentocs(List<OrcamentoC> orcamentocs) {
		this.orcamentocs = orcamentocs;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	public List<OrcamentoC> getOrcamentoCs() {
		return orcamentocs;
	}

	public void setOrcamentoCs(List<OrcamentoC> orcamentocs) {
		this.orcamentocs = orcamentocs;
	}

	@PostConstruct
	public void novo() {
		try {
			orcamentoc = new OrcamentoC();
			orcamentoc.setPrecoTotal(new BigDecimal("0.00"));

			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar("descricao");

			itensOrcaC = new ArrayList<>();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar carregar a tela de Orcamentos de Compras");
			erro.printStackTrace();
		}
	}

	public void adicionar(ActionEvent evento) {
		Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

		ItemOrcaC item = null;
		for (ItemOrcaC rs : itensOrcaC) {
			if (rs.getProduto().equals(produto)) {
				item = rs;
			}
		}

		if (item == null) {
			ItemOrcaC itemOrcaC = new ItemOrcaC();
			itemOrcaC.setPrecoParcial(produto.getPreco());
			itemOrcaC.setProduto(produto);
			itemOrcaC.setQuantidade(new Short("1"));

			itensOrcaC.add(itemOrcaC);
		} else {
			ItemOrcaC itemOrcaC = item;
			itemOrcaC.setQuantidade(new Short(itemOrcaC.getQuantidade() + 1 + ""));
			itemOrcaC.setPrecoParcial(produto.getPreco().multiply(new BigDecimal(itemOrcaC.getQuantidade())));
		}

		calcular();
	}
		
	public void subtrair(ActionEvent evento) {
		Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
		if (produto.getQuantidade()>0) {
		
			ItemOrcaC item = null;
			for (ItemOrcaC rs : itensOrcaC) {
				if (rs.getProduto().equals(produto)) {
					item = rs;
				}
			}
	
			if (item == null) {
				ItemOrcaC itemOrcaC = new ItemOrcaC();
				itemOrcaC.setPrecoParcial(produto.getPreco());
				itemOrcaC.setProduto(produto);
				itemOrcaC.setQuantidade(new Short("1"));
	
				itensOrcaC.add(itemOrcaC);
			} else {
				ItemOrcaC itemOrcaC = item;
				if (itemOrcaC.getQuantidade() != null && itemOrcaC.getQuantidade()>0) {
					itemOrcaC.setQuantidade(new Short(itemOrcaC.getQuantidade() - 1 + ""));
				}
				itemOrcaC.setPrecoParcial(produto.getPreco().multiply(new BigDecimal(itemOrcaC.getQuantidade())));
			}
	
			calcular();
		}
	}

	public void remover(ActionEvent evento) {
		ItemOrcaC itemOrcaC = (ItemOrcaC) evento.getComponent().getAttributes().get("itemSelecionado");

		int achou = -1;
		for (int posicao = 0; posicao < itensOrcaC.size(); posicao++) {
			if (itensOrcaC.get(posicao).getProduto().equals(itemOrcaC.getProduto())) {
				achou = posicao;
			}
		}

		if (achou > -1) {
			itensOrcaC.remove(achou);
		}

		calcular();
	}

	public void calcular() {
		orcamentoc.setPrecoTotal(new BigDecimal("0.00"));

		for (int posicao = 0; posicao < itensOrcaC.size(); posicao++) {
			ItemOrcaC itemOrcaC = itensOrcaC.get(posicao);
			orcamentoc.setPrecoTotal(orcamentoc.getPrecoTotal().add(itemOrcaC.getPrecoParcial()));
		}
	}

	public void finalizar() {
		try {
			orcamentoc.setHorario(new Date());
			orcamentoc.setFornecedor(null);
			orcamentoc.setFuncionario(null);
			
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listarOrdenado();

			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			fornecedores = fornecedorDAO.listarOrdenado();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar finalizar a Orcamento de Comras");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			if(orcamentoc.getPrecoTotal().signum() == 0){
				Messages.addGlobalError("Informe pelo menos um item para a Orcamento de Compras");
				return;
			}
			
			OrcamentoCDAO orcamentocDAO = new OrcamentoCDAO();
			orcamentocDAO.salvar(orcamentoc, itensOrcaC);
			
			orcamentoc = new OrcamentoC();
			orcamentoc.setPrecoTotal(new BigDecimal("0.00"));

			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar("descricao");

			itensOrcaC = new ArrayList<>();
			
			Messages.addGlobalInfo("Pedido de Compras realizada com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o Pedido de Compras");
			erro.printStackTrace();
		}
	}
	
	public void listar(){
		OrcamentoCDAO dao = new OrcamentoCDAO();
		orcamentocs = dao.listar("codigo");
	}
}