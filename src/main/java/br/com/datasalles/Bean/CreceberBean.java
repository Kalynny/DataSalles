package br.com.datasalles.Bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;
import br.com.datasalles.dao.CreceberDAO;
import br.com.datasalles.dao.ClienteDAO;
import br.com.datasalles.domain.Cliente;
import br.com.datasalles.domain.Creceber;


@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CreceberBean implements Serializable {
	private Creceber creceber;
	private Cliente cliente;
	private List<Creceber>creceberes;
	private List<Cliente>clientes;
	private String tipo;
	
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Creceber getCreceber() {
		return creceber;
	}

	public void setCreceber(Creceber creceber) {
		this.creceber = creceber;
	}
	
	public List<Creceber> getCreceberes() {
		return creceberes;
	}

	public void setCreceberes(List<Creceber> creceberes) {
		this.creceberes = creceberes;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void novo() {
		try {
		creceber = new Creceber();
		
		ClienteDAO clientesDAO = new ClienteDAO();
		clientes = clientesDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar gerar um novo produto");
			erro.printStackTrace();
		}
		
	}

	
	public void salvar() {
		try {
			CreceberDAO creceberDAO = new CreceberDAO();
			System.out.println(creceberDAO);
			creceberDAO.merge(creceber);
			
						
			novo();
			creceberes = creceberDAO.listar();
			
			
			Messages.addGlobalInfo("Contas a Pagar salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o Contas a Pagar");
			erro.printStackTrace();
		}
	}
	
	

@PostConstruct
	public void listar(){
		try{
			CreceberDAO creceberDAO = new CreceberDAO();
			creceberes = creceberDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar o Contas a Pagar");
			erro.printStackTrace();
		}
	}


	public void excluir(ActionEvent evento) {
		try {
			creceber = (Creceber) evento.getComponent().getAttributes().get("creceberSelecionado");
	
			CreceberDAO creceberDAO = new CreceberDAO();
			creceberDAO.excluir(creceber);
			
			creceberes = creceberDAO.listar();
	
			Messages.addGlobalInfo("Cliente removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o Contas a Pagar");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		creceber = (Creceber) evento.getComponent().getAttributes().get("creceberSelecionado");
	}

}