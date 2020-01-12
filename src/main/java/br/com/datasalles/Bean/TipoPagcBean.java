package br.com.datasalles.Bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;
import br.com.datasalles.dao.TipoPagcDAO;
import br.com.datasalles.domain.TipoPagc;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class TipoPagcBean implements Serializable {
	private TipoPagc tipopagc;
	private List<TipoPagc>tipopagcs;

	public TipoPagc getTipopagc() {
		return tipopagc;
	}

	public void setTipopag(TipoPagc tipopagc) {
		this.tipopagc = tipopagc;
	}

	public List<TipoPagc> getTipopagcs() {
		return tipopagcs;
	}

	public void setTipopagcs(List<TipoPagc> tipopagcs) {
		this.tipopagcs = tipopagcs;
	}


	public void novo() {
		tipopagc = new TipoPagc();
	}

	public void salvar() {
		try {
			TipoPagcDAO tipopagcDAO = new TipoPagcDAO();
			tipopagcDAO.merge(tipopagc);

			novo();
			tipopagcs = tipopagcDAO.listar();

			Messages.addGlobalInfo("Tipo de Pagamento salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o Tipo de Pagamento");
			erro.printStackTrace();
		}
	}


	@PostConstruct
	public void listar(){
		try{
			TipoPagcDAO tipopagcDAO = new TipoPagcDAO();
			tipopagcs = tipopagcDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar o Tipo de Avaria");
			erro.printStackTrace();
		}
	}


	public void excluir(ActionEvent evento) {
		try {
			tipopagc = (TipoPagc) evento.getComponent().getAttributes().get("tipopagcSelecionado");

			TipoPagcDAO tipopagcDAO = new TipoPagcDAO();
			tipopagcDAO.excluir(tipopagc);

			tipopagcs = tipopagcDAO.listar();

			Messages.addGlobalInfo("Tipo de Pagamento removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o Tipo de Pagamento");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento){
		tipopagc = (TipoPagc) evento.getComponent().getAttributes().get("tipopagcSelecionado");
	}


}
