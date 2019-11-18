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
import br.com.datasalles.dao.ClienteDAO;
import br.com.datasalles.dao.FuncionarioDAO;
import br.com.datasalles.dao.TipoPagDAO;
import br.com.datasalles.dao.VendaDAO;
import br.com.datasalles.domain.Caixa;
import br.com.datasalles.domain.Cliente;
import br.com.datasalles.domain.Funcionario;
import br.com.datasalles.domain.TipoPag;
import br.com.datasalles.domain.Venda;
import br.com.datasalles.util.HibernateUtil;


@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class RecebimentoBean implements Serializable{
	private List<Venda> vendas;
	private List<Funcionario> funcionarios;
	private List<Cliente> clientes;
	private Caixa caixa;
	private List<Caixa> caixas;
	private Cliente cliente;
	private TipoPag tipopag;
	private List<TipoPag> tipopags;
	private Venda venda;
	private BigDecimal valorInformado;
	private BigDecimal valorRecebido;
			
	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public TipoPag getTipopag() {
		return tipopag;
	}

	public void setTipopag(TipoPag tipopag) {
		this.tipopag = tipopag;
	}

	public BigDecimal getValorInformado() {
		return valorInformado;
	}

	public void setValorInformado(BigDecimal valorInformado) {
		this.valorInformado = valorInformado;
	}
	
	public BigDecimal getValorRecebido() {
		return valorRecebido;
	}

	public void setValorRecebido(BigDecimal valorRecebido) {
		this.valorRecebido = valorRecebido;
	}


	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}
		
	public List<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}
	
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
	public List<Caixa> getCaixas() {
		return caixas;
	}

	public void setCaixas(List<Caixa> caixas) {
		this.caixas = caixas;
	}
	
	public List<TipoPag> getTipopags() {
		return tipopags;
	}

	public void setTipopags(List<TipoPag> tipopags) {
		this.tipopags = tipopags;
	}
	
	
		//preenche uma lista com registro de estados
		@PostConstruct // essa anotation diz que o metodo tem que disparar no momento em que a tela é criada 
		public void listar(){
			valorInformado = new BigDecimal("0");
			valorRecebido = new BigDecimal("0");
			try{
				caixa = null;
				CaixaDAO caixaDAO = new CaixaDAO();
				caixa = caixaDAO.buscar(1l);
				
				pegaValorInicial();
				
			} catch (RuntimeException erro) {
				Messages.addGlobalError("Ocorreu um erro ao tentar verificar o Caixa");
				erro.printStackTrace();
			}
		}
		
		
		public void editar(ActionEvent evento){
			try {
				venda = (Venda) evento.getComponent().getAttributes().get("vendaSelecionado");
				
				
				ClienteDAO clienteDAO = new ClienteDAO();
				clientes = clienteDAO.listar();
				
				/*VendaDAO vendaDao = new VendaDAO();
				venda = vendaDao.buscar(venda.getCodigo()); */
				
				//tem q arrumar uma forma de passar o codigo do caixa aq em buscar ... ve se tem n
				//CaixaDAO caixaDao = new CaixaDAO();
				//caixaDao./();
				
				FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
				funcionarios = funcionarioDAO.listar();
				
			} catch (RuntimeException erro) {
				Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar o caixa");
				erro.printStackTrace();
			}	
		}
		
		
		public void receber() {
			try {
					CaixaDAO caixaDAO = new CaixaDAO();
					caixaDAO.salvar(caixa);

					caixa = new Caixa();
										
					FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
					funcionarios = funcionarioDAO.listar();
					
					Messages.addGlobalInfo("Recebimento do Caixa salvo com sucesso");
				} catch (RuntimeException erro) {
					Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar o Recebimento do Caixa");
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
		
		//@SuppressWarnings({ "rawtypes", "unused" })
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
				
				ClienteDAO clienteDAO = new ClienteDAO();
				clientes = clienteDAO.listar("nome");
				
				TipoPagDAO tipopagDAO = new TipoPagDAO();
				tipopags = tipopagDAO.listar("descricao");

				caixas = caixaDAO.listar();

				Messages.addGlobalInfo("Recebimento do Caixa salvo com sucesso");
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
					
			String sql = "insert into datasalles.caixa VALUES (null, sysdate(),"+venda.getPrecoTotal()+","+venda.getCliente().getCodigo()+","+venda.getFuncionario().getCodigo()+","+venda.getTipopag().getCodigo()+","+venda.getCodigo()+");";

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

			Messages.addGlobalInfo("Recebimento do Caixa salvo com sucesso");
		}
	}
	
		public void excluir(ActionEvent evento) {
			try {
				venda = (Venda) evento.getComponent().getAttributes().get("vendaSelecionado");

				VendaDAO vendaDAO = new VendaDAO();
				vendaDAO.excluir(venda);
				
				vendas = vendaDAO.listar();

				Messages.addGlobalInfo("Estado removido com sucesso");
			} catch (RuntimeException erro) {
				Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o estado");
				erro.printStackTrace();
			}
		}
	
		
}