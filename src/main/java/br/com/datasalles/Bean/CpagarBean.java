package br.com.datasalles.Bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;
import br.com.datasalles.dao.CpagarDAO;
import br.com.datasalles.dao.FornecedorDAO;
import br.com.datasalles.domain.Cpagar;
import br.com.datasalles.domain.Fornecedor;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CpagarBean implements Serializable {
	private Cpagar cpagar;
	private Fornecedor fornecedor;
	private List<Cpagar> cpagares;
	private List<Fornecedor> fornecedores;

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public Cpagar getCpagar() {
		return cpagar;
	}

	public void setCpagar(Cpagar cpagar) {
		this.cpagar = cpagar;
	}

	public List<Cpagar> getCpagares() {
		return cpagares;
	}

	public void setCpagares(List<Cpagar> cpagares) {
		this.cpagares = cpagares;
	}

	public void novo() {
		try {
			cpagar = new Cpagar();

			FornecedorDAO fornecedoresDAO = new FornecedorDAO();
			fornecedores = fornecedoresDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar gerar um novo produto");
			erro.printStackTrace();
		}

	}


	public void salvar() {
		try {
			CpagarDAO cpagarDAO = new CpagarDAO();
			System.out.println(cpagarDAO);
			cpagarDAO.merge(cpagar);


			novo();
			cpagares = cpagarDAO.listar();


			Messages.addGlobalInfo("Contas a Pagar salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o Contas a Pagar");
			erro.printStackTrace();
		}
	}



	@PostConstruct
	public void listar(){
		try{
			CpagarDAO cpagarDAO = new CpagarDAO();
			cpagares = cpagarDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar o Contas a Pagar");
			erro.printStackTrace();
		}
	}


	public void excluir(ActionEvent evento) {
		try {
			cpagar = (Cpagar) evento.getComponent().getAttributes().get("cpagarSelecionado");

			CpagarDAO cpagarDAO = new CpagarDAO();
			cpagarDAO.excluir(cpagar);

			cpagares = cpagarDAO.listar();

			Messages.addGlobalInfo("Fornecedor removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o Contas a Pagar");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento){
		cpagar = (Cpagar) evento.getComponent().getAttributes().get("cpagarSelecionado");
	}

}
