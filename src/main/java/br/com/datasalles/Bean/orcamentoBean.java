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
import br.com.datasalles.dao.ClienteDAO;
import br.com.datasalles.dao.FuncionarioDAO;
import br.com.datasalles.dao.ProdutoDAO;
import br.com.datasalles.dao.OrcamentoDAO;
import br.com.datasalles.domain.Cliente;
import br.com.datasalles.domain.Funcionario;
import br.com.datasalles.domain.ItemOrca;
import br.com.datasalles.domain.Produto;

import br.com.datasalles.domain.Orcamento;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class orcamentoBean implements Serializable {

	private Orcamento orcamento;
	private List<Produto> produtos;
	private List<ItemOrca> itensOrca;
	private List<Cliente> clientes;
	private List<Funcionario> funcionarios;
	private List<Orcamento> orcamentos;

	public Orcamento getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<ItemOrca> getItensOrca() {
		return itensOrca;
	}

	public void setItensOrca(List<ItemOrca> itensOrca) {
		this.itensOrca = itensOrca;
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

	public List<Orcamento> getOrcamentos() {
		return orcamentos;
	}

	public void setOrcamentos(List<Orcamento> orcamentos) {
		this.orcamentos = orcamentos;
	}

	@PostConstruct
	public void novo() {
		try {
			orcamento = new Orcamento();
			orcamento.setPrecoTotal(new BigDecimal("0.00"));

			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar("descricao");

			itensOrca = new ArrayList<>();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar carregar a tela de Orcamentos");
			erro.printStackTrace();
		}
	}

	public void adicionar(ActionEvent evento) {
		Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

		ItemOrca item = null;
		for (ItemOrca rs : itensOrca) {
			if (rs.getProduto().equals(produto)) {
				item = rs;
			}
		}

		if (item == null) {
			ItemOrca itemOrca = new ItemOrca();
			itemOrca.setPrecoParcial(produto.getPreco());
			itemOrca.setProduto(produto);
			itemOrca.setQuantidade(new Short("1"));

			itensOrca.add(itemOrca);
		} else {
			ItemOrca itemOrca = item;
			itemOrca.setQuantidade(new Short(itemOrca.getQuantidade() + 1 + ""));
			itemOrca.setPrecoParcial(produto.getPreco().multiply(new BigDecimal(itemOrca.getQuantidade())));
		}

		calcular();
	}

	public void subtrair(ActionEvent evento) {
		Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

		if (produto.getQuantidade()>0) {

			ItemOrca item = null;
			for (ItemOrca rs : itensOrca) {
				if (rs.getProduto().equals(produto)) {
					item = rs;

				}
			}

			if (item == null) {
				ItemOrca itemOrca = new ItemOrca();
				itemOrca.setPrecoParcial(produto.getPreco());
				itemOrca.setProduto(produto);
				itemOrca.setQuantidade(new Short("1"));


				itensOrca.add(itemOrca);
			} else {
				ItemOrca itemOrca = item;
				if (itemOrca.getQuantidade() != null && itemOrca.getQuantidade()>0) {
					itemOrca.setQuantidade(new Short(itemOrca.getQuantidade() - 1 + ""));
				}
				itemOrca.setPrecoParcial(produto.getPreco().multiply(new BigDecimal(itemOrca.getQuantidade())));
			}

			calcular();
		}

	}

	public void remover(ActionEvent evento) {
		ItemOrca itemOrca = (ItemOrca) evento.getComponent().getAttributes().get("itemSelecionado");

		int achou = -1;
		for (int posicao = 0; posicao < itensOrca.size(); posicao++) {
			if (itensOrca.get(posicao).getProduto().equals(itemOrca.getProduto())) {
				achou = posicao;
			}
		}

		if (achou > -1) {
			itensOrca.remove(achou);
		}

		calcular();
	}

	public void calcular() {
		orcamento.setPrecoTotal(new BigDecimal("0.00"));

		for (int posicao = 0; posicao < itensOrca.size(); posicao++) {
			ItemOrca itemOrca = itensOrca.get(posicao);
			orcamento.setPrecoTotal(orcamento.getPrecoTotal().add(itemOrca.getPrecoParcial()));
		}
	}

	public void finalizar() {
		try {
			orcamento.setHorario(new Date());
			orcamento.setCliente(null);
			orcamento.setFuncionario(null);

			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listarOrdenado();

			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listarOrdenado();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar finalizar a orcamento");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			if(orcamento.getPrecoTotal().signum() == 0){
				Messages.addGlobalError("Informe pelo menos um item para a orcamento");
				return;
			}

			OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
			orcamentoDAO.salvar(orcamento, itensOrca);

			orcamento = new Orcamento();
			orcamento.setPrecoTotal(new BigDecimal("0.00"));

			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar("descricao");

			itensOrca = new ArrayList<>();

			Messages.addGlobalInfo("Orçamento realizada com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a orçamento");
			erro.printStackTrace();
		}
	}

	public void listar(){
		OrcamentoDAO dao = new OrcamentoDAO();
		orcamentos = dao.listar("codigo");
	}
}