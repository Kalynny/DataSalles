package br.com.datasalles.Bean;


import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;
import br.com.datasalles.dao.FuncionarioDAO;
import br.com.datasalles.domain.Caixa;
import br.com.datasalles.domain.Funcionario;


@ManagedBean
@ViewScoped
public class CaixaBean {
	private ScheduleModel caixas;
	private Caixa caixa;
	private List<Funcionario> funcionarios;

	public ScheduleModel getCaixas() {
		return caixas;
	}
	public void setCaixas(ScheduleModel caixas) {
		this.caixas = caixas;
	}
	
	public Caixa getCaixa() {
		return caixa;
	}
	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}
		
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	
	
	@PostConstruct
	public void listar() {
		caixas = new DefaultScheduleModel();
	
	}
	
	public void novo(SelectEvent evento) {
		
		caixa = new Caixa();
		caixa.setDataAbertura((Date) evento.getObject());
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarios = funcionarioDAO.listar();
		
	}

		
}
