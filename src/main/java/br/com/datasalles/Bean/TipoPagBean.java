package br.com.datasalles.Bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;
import br.com.datasalles.dao.TipoPagDAO;
import br.com.datasalles.domain.TipoPag;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class TipoPagBean implements Serializable {
	private TipoPag tipopag;
	private List<TipoPag>tipopags;
	
	public TipoPag getTipopag() {
		return tipopag;
	}

	public void setTipopag(TipoPag tipopag) {
		this.tipopag = tipopag;
	}

	public List<TipoPag> getTipopags() {
		return tipopags;
	}

	public void setTipopags(List<TipoPag> tipopags) {
		this.tipopags = tipopags;
	}
	

	public void novo() {
		tipopag = new TipoPag();
	}

	public void salvar() {
		try {
			TipoPagDAO tipopagDAO = new TipoPagDAO();
			tipopagDAO.merge(tipopag);

			novo();
			tipopags = tipopagDAO.listar();
			
			Messages.addGlobalInfo("Tipo de Pagamento salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o Tipo de Pagamento");
			erro.printStackTrace();
		}
	}
		

	@PostConstruct
	public void listar(){
		try{
			TipoPagDAO tipopagDAO = new TipoPagDAO();
			tipopags = tipopagDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar o Tipo de Avaria");
			erro.printStackTrace();
		}
	}


	public void excluir(ActionEvent evento) {
		try {
			tipopag = (TipoPag) evento.getComponent().getAttributes().get("tipopagSelecionado");
	
			TipoPagDAO tipopagDAO = new TipoPagDAO();
			tipopagDAO.excluir(tipopag);
			
			tipopags = tipopagDAO.listar();
	
			Messages.addGlobalInfo("Tipo de Pagamento removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o Tipo de Pagamento");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		tipopag = (TipoPag) evento.getComponent().getAttributes().get("tipopagSelecionado");
	}


}
