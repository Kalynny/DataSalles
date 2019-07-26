package br.com.datasalles.Bean;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.omnifaces.util.Messages;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;
import br.com.datasalles.dao.AberturaDAO;
import br.com.datasalles.dao.FuncionarioDAO;
import br.com.datasalles.domain.Abertura;
import br.com.datasalles.domain.Funcionario;


@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class AberturaBean implements Serializable {
	private ScheduleModel aberturas;
	private Abertura abertura;
	private List<Funcionario> funcionarios;
		
	public ScheduleModel getAberturas() {
		return aberturas;
	}
	public void setAberturas(ScheduleModel aberturas) {
		this.aberturas = aberturas;
	}
	public Abertura getAbertura() {
		return abertura;
	}
	public void setAbertura(Abertura abertura) {
		this.abertura = abertura;
	}
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
		
	
	@PostConstruct
	public void listar() {
		aberturas = new DefaultScheduleModel();
	}
	
	public void novo(SelectEvent evento) {
		
		abertura = new Abertura();
		abertura.setDataAbertura((Date) evento.getObject());
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarios = funcionarioDAO.listar();
		
	}
	
	public void salvar() {
		try {
			AberturaDAO aberturaDAO = new AberturaDAO();
			aberturaDAO.merge(abertura);

			abertura = new Abertura();
			
			aberturas = (ScheduleModel) aberturaDAO.listar();
			System.out.println(aberturas);

			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar();
			
			Messages.addGlobalInfo("Abertura de Caixa salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a Abertura de caixa");
			erro.printStackTrace();
		}
	}

		
}
