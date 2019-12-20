package br.com.datasalles.Bean;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.omnifaces.util.Messages;
import br.com.datasalles.dao.SangriaDAO;
import br.com.datasalles.domain.Sangria;



@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class SangriaBean implements Serializable{
	Sangria sangria;
	BigDecimal valorInformado;

	
	
	public BigDecimal getValorInformado() {
		return valorInformado;
	}

	public void setValorInformado(BigDecimal valorInformado) {
		this.valorInformado = valorInformado;
	}

	public Sangria getSangria() {
		return sangria;
	}

	public void setSangria(Sangria sangria) {
		this.sangria = sangria;
	}
	
	
	
		@PostConstruct 
		public void listar(){
			valorInformado = new BigDecimal("0");
			try{
				sangria = null;
				SangriaDAO sangriaDAO = new SangriaDAO();
				sangria = sangriaDAO.busca;
			} catch (RuntimeException erro) {
				Messages.addGlobalError("Ocorreu um erro ao tentar verificar o sangria");
				erro.printStackTrace();
			}
		}
		
		public void sacarsangria(){
			
			sangria = null;
			try{
					SangriaDAO sangriaDAO = new SangriaDAO();
					sangria = sangriaDAO.buscar(1l);
					
					if(valorInformado.doubleValue() < sangria.getSangria().doubleValue()){
						
						sangria.setSangria(sangria.getSangria().subtract(valorInformado)); 
						sangriaDAO.editar(sangria);
					}else{
						Messages.addGlobalError("Ocorreu um erro ao tentar verificar o sangria");
						Messages.addGlobalError("Ocorreu um erro ao tentar sacar o sangria no sistema");
						Messages.addGlobalError("Tente refazer a transação, 'valor a ser sacado não pode ser superior ao informado pelo sistema!'");
					}
			
			} catch (RuntimeException erro) {
				Messages.addGlobalError("Ocorreu um erro ao tentar verificar o sangria");
				erro.printStackTrace();
			}
		}
			
		

}
