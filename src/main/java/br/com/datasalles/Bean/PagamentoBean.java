package br.com.datasalles.Bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.omnifaces.util.Messages;
import br.com.datasalles.dao.CaixaDAO;
import br.com.datasalles.dao.FuncionarioDAO;
import br.com.datasalles.dao.PagamentoDAO;
import br.com.datasalles.dao.TipoPagcDAO;
import br.com.datasalles.dao.CompraDAO;
import br.com.datasalles.dao.FornecedorDAO;
import br.com.datasalles.domain.Caixa;
import br.com.datasalles.domain.Funcionario;
import br.com.datasalles.domain.Produto;
import br.com.datasalles.domain.Pagamento;
import br.com.datasalles.domain.TipoPagc;
import br.com.datasalles.domain.Compra;
import br.com.datasalles.domain.Fornecedor;
import br.com.datasalles.util.HibernateUtil;


@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PagamentoBean implements Serializable{
	
	private List<Compra> compras;
	private List<Funcionario> funcionarios;
	private List<Fornecedor> fornecedores;
	private Caixa caixa;
	private List<Produto> produtos;
	private List<Caixa> caixas;
	private Fornecedor fornecedor;
	private TipoPagc tipopagc;
	private List<TipoPagc> tipopagcs;
	private Compra compra;
	private Pagamento pagamento;
	private BigDecimal valorInformado;
	private BigDecimal valorPago;
			
		
	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public TipoPagc getTipopagc() {
		return tipopagc;
	}

	public List<TipoPagc> getTipopagcs() {
		return tipopagcs;
	}

	public TipoPagc getTipoPagc() {
		return tipopagc;
	}

	public void setTipopagc(TipoPagc tipopagc) {
		this.tipopagc = tipopagc;
	}

	public BigDecimal getValorInformado() {
		return valorInformado;
	}

	public void setValorInformado(BigDecimal valorInformado) {
		this.valorInformado = valorInformado;
	}
	
	public BigDecimal getvalorPago() {
		return valorPago;
	}

	public void setvalorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}


	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}
		
	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}
	
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}
	
	public List<Caixa> getCaixas() {
		return caixas;
	}

	public void setCaixas(List<Caixa> caixas) {
		this.caixas = caixas;
	}
	
	public List<TipoPagc> getTipoPagcs() {
		return tipopagcs;
	}

	public void setTipopagcs(List<TipoPagc> tipopagcs) {
		this.tipopagcs = tipopagcs;
	}
	
	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

		@PostConstruct
		public void listar(){
			valorInformado = new BigDecimal("0");
			valorPago = new BigDecimal("0");
			try{
				compra = null;
				CompraDAO compraDAO = new CompraDAO();
				compras = compraDAO.listar();
				
				pegaValorInicial();
				
			} catch (RuntimeException erro) {
				Messages.addGlobalError("Ocorreu um erro ao tentar verificar o Caixa");
				erro.printStackTrace();
			}
		}
		
		public void editar(ActionEvent evento){
			try {
				compra = (Compra) evento.getComponent().getAttributes().get("CompraSelecionado");
								
				FornecedorDAO fornecedorDAO = new FornecedorDAO();
				fornecedores = fornecedorDAO.listar();
												
				FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
				funcionarios = funcionarioDAO.listar();
				
			} catch (RuntimeException erro) {
				Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar o caixa");
				erro.printStackTrace();
			}	
		}
		
		
		public void pagar() {
			try {
				@SuppressWarnings("unused")
				PagamentoDAO pagamentoDAO = new PagamentoDAO();
												
				FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
				funcionarios = funcionarioDAO.listarOrdenado();

				FornecedorDAO fornecedorDAO = new FornecedorDAO();
				fornecedores = fornecedorDAO.listarOrdenado();
				
				TipoPagcDAO tipopagcDAO = new TipoPagcDAO();
				tipopagcs = tipopagcDAO.listar();
							
					Messages.addGlobalInfo("Pagamento do Caixa salvo com sucesso");
				} catch (RuntimeException erro) {
					Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar o Pagamento do Caixa");
					erro.printStackTrace();
				}
			}
		
		
		public void pegaValorInicial() {
			carregaValor();
		//	valorInformado = valorInformado;
			
		//	valorInformado = new BigDecimal("200.00");
			
		}
		
		@SuppressWarnings({ "rawtypes" })
		private void carregaValor() {
			BigDecimal valor = null;
			Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
			Transaction tx = null;
		      try{
		         tx = sessao.beginTransaction();
		         
		         String sql = "select valorAbertura from abertura order by dataAbertura desc limit 1";
		         
		         SQLQuery query = sessao.createSQLQuery(sql);
		         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		         List data = query.list();

		         for(Object object : data)  {
		            Map row = (Map)object;
		            
		           // BigDecimal valor;
		            
		            valor = new BigDecimal(row.get("valorAbertura").toString());
		           ;
		         }
		         tx.commit();
		        
		      }catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      }finally {
		         sessao.close();
		         valorInformado = valor;
		         
		      }
		}
		
		
		@SuppressWarnings({ "rawtypes", "unused" })
		private BigDecimal carregaValors() {
			BigDecimal valor = null;
			Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
			Transaction tx = null;
		      try{
		         tx = sessao.beginTransaction();
		         
		         String sql = "select valorAbertura from abertura order by dataAbertura desc limit 1";
		         
		         SQLQuery query = sessao.createSQLQuery(sql);
		         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		         List data = query.list();

		         for(Object object : data)  {
		            Map row = (Map)object;
		            
		           // BigDecimal valor;
		            
		            valor = new BigDecimal(row.get("valorAbertura").toString());

		         }
		         tx.commit();
		        
		      }catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      }finally {
		         sessao.close();
		         valorInformado = valor;
		         
		      }
		      return valor;
		}
		
	//	@SuppressWarnings({ "rawtypes" })
	/*	private BigDecimal carregaValors() {
			BigDecimal valor = null;
			Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
			Transaction tx = null;
		      try{
		         tx = sessao.beginTransaction();
		         
		         String sql = "select valorAbertura from abertura order by dataAbertura desc limit 1";
		         
		         SQLQuery query = sessao.createSQLQuery(sql);
		         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		         List data = query.list();

		         for(Object object : data)  {
		            Map row = (Map)object;
		            
		           // BigDecimal valor;
		            
		            valor = new BigDecimal(row.get("valorAbertura").toString());

		         }
		         tx.commit();
		        
		      }catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      }finally {
		         sessao.close();
		         valorInformado = valor;
		         
		      }
		      return valor;
		} */
		
		public void salvar() {
			try {
				CaixaDAO caixaDAO = new CaixaDAO();
				caixaDAO.salvar(caixa);
				
				caixa = new Caixa();

				FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
				funcionarios = funcionarioDAO.listar("descricao");
				
				FornecedorDAO fornecedorDAO = new FornecedorDAO();
				fornecedores = fornecedorDAO.listar("nome");
				
				TipoPagcDAO tipopagcDAO = new TipoPagcDAO();
				tipopagcs = tipopagcDAO.listar("descricao");

				caixas = caixaDAO.listar();

				Messages.addGlobalInfo("Pagamento do Caixa salvo com sucesso");
			} catch (RuntimeException erro) {
				Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar o Receimento do Caixa");
				erro.printStackTrace();
			}
		}
		
		@SuppressWarnings("unused")
		public void finalizarCaixa() {
			 
			Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
			org.hibernate.Transaction transacao = null;

			try {
			
			transacao= sessao.beginTransaction();
					
			String sql = "insert into datasalles.caixa VALUES (null, sysdate(),"+compra.getPrecoTotal()+","+compra.getFornecedor().getCodigo()+","+compra.getFuncionario().getCodigo()+","+compra.getTipopagc().getCodigo()+","+compra.getCodigo()+");";

			SQLQuery query = sessao.createSQLQuery(sql);
					
			int result = query.executeUpdate();
			
			transacao.commit();
		//	System.out.println(result);

		} catch (HibernateException e) {
			if (transacao != null)
				transacao.rollback();
			e.printStackTrace();
		} finally {
			sessao.close();

			Messages.addGlobalInfo("Pagamento do Caixa salvo com sucesso");
		}
	}
	
		public void excluir(ActionEvent evento) {
			try {
				compra = (Compra) evento.getComponent().getAttributes().get("CompraSelecionado");

				CompraDAO compraDAO = new CompraDAO();
				compraDAO.excluir(compra);
				
				compras = compraDAO.listar();

				Messages.addGlobalInfo("Estado removido com sucesso");
			} catch (RuntimeException erro) {
				Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o estado");
				erro.printStackTrace();
			}
		}
	
		
}