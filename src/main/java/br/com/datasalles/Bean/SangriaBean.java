package br.com.datasalles.Bean;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;
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
	private BigDecimal valorInformado;

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

	public BigDecimal getValorInformado() {
		return valorInformado;
	}
	public void setValorInformado(BigDecimal valorInformado) {
		this.valorInformado = valorInformado;
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

	@PostConstruct
	public void listar() {
		try {
			SangriaDAO sangriaDAO = new SangriaDAO();
			sangrias = sangriaDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar as sangrias");
			erro.printStackTrace();
		}
	}

	public void sacarSangria(){

		sangria = null;
		try{
			SangriaDAO sangriaDAO = new SangriaDAO();
			sangria = sangriaDAO.buscar(1l);

			if(valorInformado.doubleValue() < sangria.getValorInformado().doubleValue()){

				sangria.setValorInformado(sangria.getValorInformado().subtract(valorInformado)); 
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
