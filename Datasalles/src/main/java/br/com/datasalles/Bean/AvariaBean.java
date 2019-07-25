package br.com.datasalles.Bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.xml.bind.JAXBElement.GlobalScope;
import javax.xml.bind.annotation.XmlElementDecl.GLOBAL;

import org.hibernate.Session;
import org.hibernate.Transaction;
//import javax.transaction.Transaction;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;

import org.omnifaces.util.Messages;
import br.com.datasalles.dao.AvariaDAO;
import br.com.datasalles.dao.FornecedorDAO;
import br.com.datasalles.dao.ProdutoDAO;
import br.com.datasalles.domain.Avaria;
import br.com.datasalles.domain.Fornecedor;
import br.com.datasalles.domain.Produto;
import br.com.datasalles.util.HibernateUtil;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class AvariaBean implements Serializable {
	private Avaria avaria;
	private List<Avaria> avarias;
	private Produto  produto;
	private List<Produto> produtos;
	private List<Fornecedor> fornecedor;
	private String tipo;
	private String quantidade;
	
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Fornecedor> getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(List<Fornecedor> fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public Avaria getAvaria() {
		return avaria;
	}
	
	public void setAvaria(Avaria avaria) {
		this.avaria = avaria;
	}
	
	public List<Avaria> getAvarias() {
		return avarias;
	}
	
	public void setAvarias(List<Avaria> avarias) {
		this.avarias = avarias;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	@PostConstruct
	public void listar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os produtos");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			fornecedor = fornecedorDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um produto");
			erro.printStackTrace();
		}	
	}
	
	public void salvar() {
		try {
			AvariaDAO avariaDAO = new AvariaDAO();
			avariaDAO.merge(avaria);

			avaria = new Avaria();

			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			fornecedor = fornecedorDAO.listar();

			avarias = avariaDAO.listar();

			Messages.addGlobalInfo("Avaria salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar o avaria");
			erro.printStackTrace();
		}
	}
	
	public void parametros(ActionEvent event){

			 String produtoDesc = (String) event.getComponent().getAttributes().get("produto");
			 Long fornecedor = (Long) event.getComponent().getAttributes().get("fornecedor");
		 	 
			 String quantidade = getQuantidade();
			 String tipo = getTipo() ;
			 
			 Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
			 Transaction transacao = null;
			try {
				transacao= sessao.beginTransaction();
				
				String insert = "INSERT INTO datasalles.avaria VALUES (null,'"+produtoDesc+"', "+quantidade+", '"+tipo+"', "+fornecedor+");";
				
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
				Messages.addGlobalInfo("Avaria salvo com sucesso");
			}
		}
	
	public int buscaFornecedor(String nome) {
		int codFornecedor = 0;
				
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		
		try {
			transacao= sessao.beginTransaction();
			
			String select = " select codigo from fornecedor where descricao like '%'"+nome+"'%' ";
			
			SQLQuery query = sessao.createSQLQuery(select);
			
			int result = query.executeUpdate();
			
			transacao.commit();
			System.out.println(result);
			codFornecedor = result;

		} catch (HibernateException e) {
			if (transacao != null)
				transacao.rollback();
			e.printStackTrace();
		} finally {
			sessao.close();
		}
		
		return codFornecedor;
		
	}
	
}