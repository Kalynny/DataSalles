package br.com.datasalles.Bean;


import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.omnifaces.util.Messages;
import br.com.datasalles.dao.FuncionarioDAO;
import br.com.datasalles.dao.SangriaDAO;
import br.com.datasalles.domain.Funcionario;
import br.com.datasalles.domain.Sangria;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class SangriaBean implements Serializable {
	private Sangria sangria;
	private List<Funcionario> funcionarios;
	private List<Sangria> sangrias;
			
	public Sangria getSangria() {
		return sangria;
	}
	public void setSangria(Sangria sangria) {
		this.sangria = sangria;
	}
	public List<Sangria> getSangrias() {
		return sangrias;
	}
	public void setSangrias(List<Sangria> sangrias) {
		this.sangrias = sangrias;
	}
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
			
	public void salvar() {
		try {
						
			SangriaDAO sangriaDAO = new SangriaDAO();
			sangriaDAO.merge(sangria);

			sangria = new Sangria();

			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar();

			sangrias = sangriaDAO.listar();

			Messages.addGlobalInfo("Sangria do Caixa salvo com sucesso");
		}catch (RuntimeException erro) {
			Messages.addFlashGlobalError(" erro ao fazer a Sangria do caixa");
			erro.printStackTrace();
		}
	}
	
				
}
