package br.com.datasalles.Bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;
import br.com.datasalles.dao.FornecedorDAO;
import br.com.datasalles.domain.Fornecedor;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CpagarBean implements Serializable {
	private Fornecedor fornecedor;
	private List<Fornecedor>fornecedores;
	
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
	

	public void novo() {
		fornecedor = new Fornecedor();
	}

	public void salvar() {
		try {
			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			fornecedorDAO.merge(fornecedor);

			novo();
			fornecedores = fornecedorDAO.listar();
			
			Messages.addGlobalInfo("Fornecedor salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o estado");
			erro.printStackTrace();
		}
	}
	
	

@PostConstruct
	public void listar(){
		try{
			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			fornecedores = fornecedorDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os fornecedores");
			erro.printStackTrace();
		}
	}


public void excluir(ActionEvent evento) {
	try {
		fornecedor = (Fornecedor) evento.getComponent().getAttributes().get("fornecedorSelecionado");

		FornecedorDAO fornecedorDAO = new FornecedorDAO();
		fornecedorDAO.excluir(fornecedor);
		
		fornecedores = fornecedorDAO.listar();

		Messages.addGlobalInfo("Fornecedor removido com sucesso");
	} catch (RuntimeException erro) {
		Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o Fornecedor");
		erro.printStackTrace();
	}
}

public void editar(ActionEvent evento){
	fornecedor = (Fornecedor) evento.getComponent().getAttributes().get("fornecedorSelecionado");
}

	
	

}
