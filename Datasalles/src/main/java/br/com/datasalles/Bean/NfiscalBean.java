package br.com.datasalles.Bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.datasalles.dao.NfiscalDAO;
import br.com.datasalles.domain.Compra;
import br.com.datasalles.domain.Fornecedor;
import br.com.datasalles.domain.Nfiscal;


@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class NfiscalBean implements Serializable {
	private Nfiscal nfiscal;
	private List<Nfiscal>nfiscals;
	private List<Compra>compras;
	private List<Fornecedor>fornecedores;
	
	public Nfiscal getNfiscal() {
		return nfiscal;
	}
	
	public void setNfiscal(Nfiscal nfiscal) {
		this.nfiscal = nfiscal;
	}
	
	public List<Nfiscal> getNfiscals() {
		return nfiscals;
	}
	
	public void setNfiscals(List<Nfiscal> nfiscals) {
		this.nfiscals = nfiscals;
	}
	
	public List<Compra> getCompras() {
		return compras;
	}
	
	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}
	
	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}
	
	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public void novo() {
		nfiscal = new Nfiscal();
	}

	public void salvar() {
		try {
			NfiscalDAO nfiscalDAO = new NfiscalDAO();
			nfiscalDAO.merge(nfiscal);

			novo();
			nfiscals = nfiscalDAO.listar();
			
			Messages.addGlobalInfo("nfiscal salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o estado");
			erro.printStackTrace();
		}
	}
	
	

@PostConstruct
	public void listar(){
		try{
			NfiscalDAO nfiscalDAO = new NfiscalDAO();
			nfiscals = nfiscalDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os Nfiscal");
			erro.printStackTrace();
		}
	}


public void excluir(ActionEvent evento) {
	try {
		nfiscal = (Nfiscal) evento.getComponent().getAttributes().get("Nota FiscalSelecionado");

		NfiscalDAO nfiscalDAO = new NfiscalDAO();
		nfiscalDAO.excluir(nfiscal);
		
		nfiscals = nfiscalDAO.listar();

		Messages.addGlobalInfo("Nota Fiscal removido com sucesso");
	} catch (RuntimeException erro) {
		Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o Nota Fiscal");
		erro.printStackTrace();
	}
}

public void editar(ActionEvent evento){
	nfiscal = (Nfiscal) evento.getComponent().getAttributes().get("nfiscalSelecionado");
}

	
	

}
