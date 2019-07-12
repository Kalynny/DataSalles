package br.com.datasalles.Bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;
import br.com.datasalles.dao.FornecedorDAO;
import br.com.datasalles.dao.CpagarDAO;
import br.com.datasalles.domain.Fornecedor;
import br.com.datasalles.domain.Cpagar;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CpagarBean implements Serializable {
	private Cpagar cpagar;
	private List<Cpagar> cpagars;
	private List<Fornecedor> fornecedores;
		
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

	public List<Cpagar> getCpagars() {
		return cpagars;
	}

	public void setCpagars(List<Cpagar> cpagars) {
		this.cpagars = cpagars;
	}
	
	@PostConstruct
	public void novo() {
		try {
			cpagar = new Cpagar();

			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			fornecedores = fornecedorDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar gerar um novo produto");
			erro.printStackTrace();
		}
	}

	
	public void listar() {
		try {
			CpagarDAO cpagarDAO = new CpagarDAO();
			cpagars = cpagarDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os produtos");
			erro.printStackTrace();
		}
	}
		
	public void editar(ActionEvent evento){
		try {
			cpagar = (Cpagar) evento.getComponent().getAttributes().get("cpagarSelecionado");

			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			fornecedores = fornecedorDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um cpagar");
			erro.printStackTrace();
		}	
	}
	
	public void salvar() {
		try {
			CpagarDAO cpagarDAO = new CpagarDAO();
			cpagarDAO.merge(cpagar);

			cpagar = new Cpagar();

			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			fornecedores = fornecedorDAO.listar();

			cpagars = cpagarDAO.listar();

			Messages.addGlobalInfo("Conta a Pagar salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar o contas a Pagar");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			cpagar = (Cpagar) evento.getComponent().getAttributes().get("cpagarSelecionado");

			CpagarDAO cpagarDAO = new CpagarDAO();
			cpagarDAO.excluir(cpagar);

			cpagars = cpagarDAO.listar();

			Messages.addGlobalInfo("Contas a Pagar removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o contas a pagar");
			erro.printStackTrace();
		}
	}
}