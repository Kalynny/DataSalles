package br.com.datasalles.Bean;

import java.io.Serializable;
import java.sql.Connection;
//import java.util.HashMap;
import java.util.List;
//mport java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
//import org.primefaces.component.datatable.DataTable;
import br.com.datasalles.dao.EstadoDAO;
import br.com.datasalles.domain.Estado;
import br.com.datasalles.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.view.JasperViewer;



@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EstadoBean implements Serializable {
	private Estado estado;
	private List<Estado>estados;
	
	public List<Estado> getEstados() {
		return estados;
	}
	
	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public void novo() {
		estado = new Estado();
	}

	public void salvar() {
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			estadoDAO.merge(estado);

			novo();
			estados = estadoDAO.listar();
			
			Messages.addGlobalInfo("Estado salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o estado");
			erro.printStackTrace();
		}
	}
	
	

@PostConstruct
	public void listar(){
		try{
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os estados");
			erro.printStackTrace();
		}
	}


public void excluir(ActionEvent evento) {
	try {
		estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");

		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.excluir(estado);
		
		estados = estadoDAO.listar();

		Messages.addGlobalInfo("Estado removido com sucesso");
	} catch (RuntimeException erro) {
		Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o estado");
		erro.printStackTrace();
	}
}

public void editar(ActionEvent evento){
	estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
}

@SuppressWarnings("deprecation")
public void imprimir(){
	 
	 try {
	 
	 String caminho = Faces.getRealPath("/reports/estado.jasper");
	  
	 
	 Connection conexao = HibernateUtil.getConexao();
	 		 
	 JasperPrint relatorio = JasperFillManager.fillReport(caminho, null, conexao);
	 JasperViewer view = new JasperViewer(relatorio, false);
	 view.show();
	 	
	 
	 } catch ( JRException erro) {
		 Messages.addGlobalError("Ocorreu um erro ao tentar gerar o relatório");
		 	erro.printStackTrace();
		 	}
	      }


	
	
}