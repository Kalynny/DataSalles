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
import br.com.datasalles.domain.Abertura;
import br.com.datasalles.domain.Funcionario;


@ManagedBean
@ViewScoped
public class AberturaBean {
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

		
}
