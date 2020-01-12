package br.com.datasalles.Bean;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.omnifaces.util.Messages;
//import org.primefaces.component.schedule.Schedule;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;
import br.com.datasalles.dao.CaixaDAO;
import br.com.datasalles.dao.FuncionarioDAO;
import br.com.datasalles.domain.Caixa;
import br.com.datasalles.domain.Funcionario;


@ManagedBean
@ViewScoped
public class CaixaBean {
	private ScheduleModel caixas;
	private Caixa caixa;
	private List<Funcionario>funcionarios;

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}

	public ScheduleModel getCaixas() {
		return caixas;
	}

	public void setCaixas(ScheduleModel caixas) {
		this.caixas = caixas;
	}

	@PostConstruct
	public void listar(){
		caixas = new DefaultScheduleModel();
	}


	public void novo(SelectEvent evento){
		caixa = new Caixa();
		caixa.setDataAbertura((Date)evento.getObject());
		FuncionarioDAO dao = new FuncionarioDAO();
		funcionarios = dao.listar();
	}


	@SuppressWarnings("static-access")
	public void salvar(){

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(caixa.getDataAbertura());
		calendar.add(calendar.DATE, 1 );
		caixa.setDataAbertura(calendar.getTime());

		CaixaDAO dao = new CaixaDAO();
		dao.merge(caixa);
		Messages.addGlobalInfo("Caixa aberto com sucesso");
	}


}
