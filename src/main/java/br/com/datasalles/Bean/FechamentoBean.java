package br.com.datasalles.Bean;


import java.io.Serializable;
import java.math.BigDecimal;
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
import br.com.datasalles.dao.FechamentoDAO;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.omnifaces.util.Messages;
import br.com.datasalles.dao.FuncionarioDAO;
import br.com.datasalles.domain.Fechamento;
import br.com.datasalles.domain.Funcionario;
import br.com.datasalles.util.HibernateUtil;


@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FechamentoBean implements Serializable {
	private ScheduleModel fechamentos;
	private Fechamento fechamento;
	private List<Funcionario> funcionarios;
	private List<Fechamento> fechaments;


	public ScheduleModel getFechamentos() {
		return fechamentos;
	}
	public void setFechamentos(ScheduleModel fechamentos) {
		this.fechamentos = fechamentos;
	}

	public Fechamento getFechamento() {
		return fechamento;
	}
	public void setFechamento(Fechamento fechamento) {
		this.fechamento = fechamento;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<Fechamento> getFechaments() {
		return fechaments;
	}
	public void setFechaments(List<Fechamento>fechaments) {
		this.fechaments = fechaments;
	}


	@PostConstruct
	public void listar() {
		fechamentos = new DefaultScheduleModel();

	}

	public void novoAbert(SelectEvent evento) {

		fechamento = new Fechamento();
		fechamento.setDataFechamento((Date) evento.getObject());

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarios = funcionarioDAO.listar();

	}

	public void novo() {
		fechamento = new Fechamento();
	}


	public void salvar() {
		try {
			FechamentoDAO fechamentoDAO = new FechamentoDAO();
			fechamentoDAO.merge(fechamento);

			fechamento = new Fechamento();

			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar();

			fechaments = fechamentoDAO.listar();

			Messages.addGlobalInfo("Fechamento do Caixa salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Já existe um Fechamento de Caixa Efetuado no Sistema");
			erro.printStackTrace();
		}
	}

	public void parametros(ActionEvent event){

		Date fechamento = (Date) event.getComponent().getAttributes().get("dataFechamento");
		BigDecimal valor = (BigDecimal) event.getComponent().getAttributes().get("valorFechamento");
		Long funcionario = (Long) event.getComponent().getAttributes().get("funcionario");


		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		org.hibernate.Transaction transacao = null;

		try {
			transacao= sessao.beginTransaction();

			String insert = "INSERT INTO datasalles.fechamento VALUES (null,'"+fechamento+", '"+valor+",'"+funcionario+", );";

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
			Messages.addGlobalInfo("Fechamento do Caixa salvo com sucesso");
		}
	}


}
