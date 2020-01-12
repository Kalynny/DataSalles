package br.com.datasalles.Bean;


import java.io.Serializable;
import java.math.BigDecimal;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.hibernate.Session;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;
import br.com.datasalles.dao.AberturaDAO;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.omnifaces.util.Messages;
import br.com.datasalles.dao.FuncionarioDAO;
import br.com.datasalles.domain.Abertura;
import br.com.datasalles.domain.Funcionario;
import br.com.datasalles.util.HibernateUtil;


@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class AberturaBean implements Serializable {
	private ScheduleModel aberturas;
	private Abertura abertura;
	private List<Funcionario> funcionarios;
	private List<Abertura> aberturs;


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

	public List<Abertura> getAberturs() {
		return aberturs;
	}
	public void setAberturs(List<Abertura> aberturs) {
		this.aberturs = aberturs;
	}


	@PostConstruct
	public void listar() {
		aberturas = new DefaultScheduleModel();

	}

	public void novoAbert(SelectEvent evento) {

		abertura = new Abertura();
		abertura.setDataAbertura((Date) evento.getObject());

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarios = funcionarioDAO.listar();

	}

	public void novo() {
		abertura = new Abertura();
	}


	public void salvar() {
		try {

			AberturaDAO aberturaDAO = new AberturaDAO();
			aberturaDAO.merge(abertura);

			abertura = new Abertura();

			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar();

			aberturs = aberturaDAO.listar();

			Messages.addGlobalInfo("Abertura do Caixa salvo com sucesso");
		}catch (RuntimeException erro) {
			Messages.addFlashGlobalError(" Já existe um Abertura de Caixa Efetuado no Sistema");
			erro.printStackTrace();
		}
	}

	public void parametros(ActionEvent event){

		Date abertura = (Date) event.getComponent().getAttributes().get("dataAbertura");
		BigDecimal valor = (BigDecimal) event.getComponent().getAttributes().get("valorAbertura");
		Long funcionario = (Long) event.getComponent().getAttributes().get("funcionario");


		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		org.hibernate.Transaction transacao = null;

		try {
			transacao= sessao.beginTransaction();

			String insert = "INSERT INTO datasalles.abertura VALUES (null,'"+abertura+", '"+valor+",'"+funcionario+", );";

			SQLQuery query = sessao.createSQLQuery(insert);

			int result = query.executeUpdate();

			transacao.commit();
			System.out.println(result);

		} catch (HibernateException e) {
			if (transacao != null)
				transacao.rollback();
			e.printStackTrace();
		} finally {
			sessao.close();
			Messages.addGlobalInfo("Abertura do Caixa salvo com sucesso");
		}
	}


}
