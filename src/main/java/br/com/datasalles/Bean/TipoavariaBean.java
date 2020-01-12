package br.com.datasalles.Bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;
import br.com.datasalles.dao.TipoavariaDAO;
import br.com.datasalles.domain.TipoAvaria;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class TipoavariaBean implements Serializable {
	private TipoAvaria tipoavaria;
	private List<TipoAvaria>tipoavarias;

	public TipoAvaria getTipoavaria() {
		return tipoavaria;
	}

	public void setTipoavaria(TipoAvaria tipoavaria) {
		this.tipoavaria = tipoavaria;
	}

	public List<TipoAvaria> getTipoavarias() {
		return tipoavarias;
	}

	public void setTipoavarias(List<TipoAvaria> tipoavarias) {
		this.tipoavarias = tipoavarias;
	}


	public void novo() {
		tipoavaria = new TipoAvaria();
	}

	public void salvar() {
		try {
			TipoavariaDAO tipoavariaDAO = new TipoavariaDAO();
			tipoavariaDAO.merge(tipoavaria);

			novo();
			tipoavarias = tipoavariaDAO.listar();

			Messages.addGlobalInfo("Tipo de Avaria salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o Tipo de Avaria");
			erro.printStackTrace();
		}
	}


	@PostConstruct
	public void listar(){
		try{
			TipoavariaDAO tipoavariaDAO = new TipoavariaDAO();
			tipoavarias = tipoavariaDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar o Tipo de Avaria");
			erro.printStackTrace();
		}
	}


	public void excluir(ActionEvent evento) {
		try {
			tipoavaria = (TipoAvaria) evento.getComponent().getAttributes().get("tipoavariaSelecionado");

			TipoavariaDAO tavariaDAO = new TipoavariaDAO();
			tavariaDAO.excluir(tipoavaria);

			tipoavarias = tavariaDAO.listar();

			Messages.addGlobalInfo("Tipo de Avaria removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o Tipo de Avaria");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento){
		tipoavaria = (TipoAvaria) evento.getComponent().getAttributes().get("tipoavariaSelecionado");
	}


}
