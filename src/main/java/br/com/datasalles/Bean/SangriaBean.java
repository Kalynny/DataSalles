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
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.omnifaces.util.Messages;
import br.com.datasalles.dao.FuncionarioDAO;
import br.com.datasalles.dao.SangriaDAO;
import br.com.datasalles.domain.Funcionario;
import br.com.datasalles.domain.Sangria;
import br.com.datasalles.util.HibernateUtil;


@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class SangriaBean implements Serializable {
	private Sangria sangria;
	private Funcionario funcionario;
	private List<Funcionario> funcionarios;
	private List<Sangria> sangrias;

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
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
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	@PostConstruct
	public void listar() {
		try {
			SangriaDAO sangriaDAO = new SangriaDAO();
			sangrias = sangriaDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar a Sangria");
			erro.printStackTrace();
		}
	}
	public void novoSangria() {
		try {
			sangria = new Sangria();

			FuncionarioDAO funcionariosDAO = new FuncionarioDAO();
			funcionarios = funcionariosDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar gerar uma nova Sangria");
			erro.printStackTrace();
		}
	}

	public void novo() {
		sangria = new Sangria();
		
		
	}


	public void salvar() {
		try {

			SangriaDAO sangriaDAO = new SangriaDAO();
			sangriaDAO.merge(sangria);

			sangria = new Sangria();

			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar();

			sangrias = sangriaDAO.listar();

			Messages.addGlobalInfo("sangria do Caixa salvo com sucesso");
		}catch (RuntimeException erro) {
			Messages.addFlashGlobalError(" Já existe um sangria de Caixa Efetuado no Sistema");
			erro.printStackTrace();
		}
	}

	public void parametros(ActionEvent event){

		Date sangria = (Date) event.getComponent().getAttributes().get("dataSangria");
		BigDecimal valor = (BigDecimal) event.getComponent().getAttributes().get("valorSangria");
		Long funcionario = (Long) event.getComponent().getAttributes().get("funcionario");


		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		org.hibernate.Transaction transacao = null;

		try {
			transacao= sessao.beginTransaction();

			String insert = "INSERT INTO datasalles.sangria VALUES (null,'"+sangria+", '"+valor+",'"+funcionario+", );";

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
			Messages.addGlobalInfo("Sangria do Caixa salvo com sucesso");
		}
	}
	
	public void editar(ActionEvent evento){
		try {
			sangria = (Sangria) evento.getComponent().getAttributes().get("sangriaSelecionado");

			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar o caixa");
			erro.printStackTrace();
		}	
	}


}
