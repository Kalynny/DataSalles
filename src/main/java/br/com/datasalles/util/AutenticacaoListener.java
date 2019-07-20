package br.com.datasalles.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

import br.com.datasalles.Bean.AutenticacaoBean;
import br.com.datasalles.domain.Usuario;

@SuppressWarnings("serial")
public class AutenticacaoListener implements PhaseListener{

	@Override
	public void afterPhase(PhaseEvent arg0) {
		
		String paginaAtual = Faces.getViewId();
		boolean pagAut = paginaAtual.contains("autenticacao.xhtml");
		
		if(!pagAut){
			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
		
			if(autenticacaoBean == null){
				Faces.navigate("/pages/autenticacao.xhtml");
				return;
			}
			
			Usuario usuario = autenticacaoBean.getUsuarioLogado();
					
			if(usuario == null){
				Faces.navigate("/pages/autenticacao.xhtml");
				return;
			}
		
		}
		
		
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
	
	}

	@Override
	public PhaseId getPhaseId() {
	
		return PhaseId.RESTORE_VIEW;
	}

}
