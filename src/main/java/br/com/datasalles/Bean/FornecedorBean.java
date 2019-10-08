package br.com.datasalles.Bean;

import java.io.Serializable;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.component.datatable.DataTable;

import br.com.datasalles.dao.FornecedorDAO;
import br.com.datasalles.domain.Fornecedor;
import br.com.datasalles.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FornecedorBean implements Serializable {
	private Fornecedor fornecedor;
	private List<Fornecedor>fornecedores;
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}
	
	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}
	

	public void novo() {
		fornecedor = new Fornecedor();
	}
	
	public void fornecedor() {
		fornecedor = new Fornecedor();
	}

	public void salvar() {
		try {
			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			fornecedorDAO.merge(fornecedor);

			novo();
			fornecedores = fornecedorDAO.listar();
			
			Messages.addGlobalInfo("Fornecedor salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o estado");
			erro.printStackTrace();
		}
		}
	
		@PostConstruct
		public void listar(){
			try{
				FornecedorDAO fornecedorDAO = new FornecedorDAO();
				fornecedores = fornecedorDAO.listar();
			} catch (RuntimeException erro) {
				Messages.addGlobalError("Ocorreu um erro ao tentar listar os fornecedores");
				erro.printStackTrace();
			}
		}


		public void excluir(ActionEvent evento) {
			try {
				fornecedor = (Fornecedor) evento.getComponent().getAttributes().get("fornecedorSelecionado");
		
				FornecedorDAO fornecedorDAO = new FornecedorDAO();
				fornecedorDAO.excluir(fornecedor);
				
				fornecedores = fornecedorDAO.listar();
		
				Messages.addGlobalInfo("Fornecedor removido com sucesso");
			} catch (RuntimeException erro) {
				Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o Fornecedor");
				erro.printStackTrace();
			}
		}
		
		public void editar(ActionEvent evento){
			fornecedor = (Fornecedor) evento.getComponent().getAttributes().get("fornecedorSelecionado");
		}
		
		@SuppressWarnings("deprecation")
		public void imprimir(){
			try {
				DataTable tabela = (DataTable) Faces.getViewRoot().findComponent ("formListagem:tabela");
				Map<String, Object> filtros = tabela.getFilters();

				String estNome = (String) filtros.get("nome");
				String estSigla = (String) filtros.get("sigla");

				String caminho = Faces.getRealPath("/reports/fornecedor.jasper");
				String banner = Faces.getRealPath("/resources/img/Logo1.png");
				
				Map<String, Object> parametros = new HashMap<>();
				
				parametros.put("BANNER",banner);
				
				if (estNome == null) {
					parametros.put("NOME_ESTADO", "%%");
				} else {
					parametros.put("NOME_ESTADO", "%" + estNome + "%");
				}
				if (estSigla == null) {
					parametros.put("SIGLA_ESTADO", "%%");
				} else {
					parametros.put("SIGLA_ESTADO", "%" + estSigla + "%");
				}

				Connection conexao = HibernateUtil.getConexao();

				JasperPrint relatorio = JasperFillManager.fillReport(caminho,parametros, conexao);

				JasperViewer view = new JasperViewer(relatorio, false);
				 view.show();

				} catch (JRException erro) {
						Messages.addGlobalError("Ocorreu um erro ao tentar gerar o relatório");
						erro.printStackTrace();
					}
				}


	
	

}
