package br.com.datasalles.Bean;



import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;
import br.com.datasalles.dao.FuncionarioDAO;
import br.com.datasalles.dao.PessoaDAO;
import br.com.datasalles.domain.Funcionario;
import br.com.datasalles.domain.Pessoa;


@SuppressWarnings("serial")
@ManagedBean
@ViewScoped

public class FuncionarioBean implements Serializable {
	private Funcionario funcionario;

	private List<Funcionario> funcionarios;
	private List<Pessoa> pessoas;

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	@PostConstruct
	public void listar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar("dataAdmissao");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os funcionarios");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			funcionario = new Funcionario();

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar criar um novo funcionario");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.merge(funcionario);

			funcionario = new Funcionario();
			
			funcionarios = funcionarioDAO.listar("dataAdmissao");
			System.out.println(funcionarios);

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");
			
			Messages.addGlobalInfo("Funcionario salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o funcionario");
			erro.printStackTrace();
		}
	}
	
	
	public void excluir(ActionEvent evento) {
		try {
			funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");

			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.excluir(funcionario);
			
			funcionarios = funcionarioDAO.listar();
					
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();

			Messages.addGlobalInfo("Funcionario removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o Funcionario");
			erro.printStackTrace();
		}
	}
	
	
	public void editar(ActionEvent evento){
		try {
			funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um produto");
			erro.printStackTrace();
		}	
		
		
	}
	
}
