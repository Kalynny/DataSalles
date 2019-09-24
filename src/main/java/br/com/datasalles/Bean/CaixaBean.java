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
import br.com.datasalles.domain.Caixa;
import br.com.datasalles.domain.Venda;
import br.com.datasalles.util.HibernateUtil;


@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CaixaBean implements Serializable{
	private List<Venda> vendas;
	private Caixa caixa;
	private Venda venda;
	private BigDecimal valorInformado;

	
	
	public BigDecimal getValorInformado() {
		return valorInformado;
	}

	public void setValorInformado(BigDecimal valorInformado) {
		this.valorInformado = valorInformado;
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
	
	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

		//preenche uma lista com registro de estados
		@PostConstruct // essa anotation diz que o metodo tem que disparar no momento em que a tela é criada 
		public void listar(){
			valorInformado = new BigDecimal("0");
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
		
		public void sacarCaixa(){
			
			caixa = null;
			try{
					CaixaDAO caixaDAO = new CaixaDAO();
					caixa = caixaDAO.buscar(1l);
					
					if(valorInformado.doubleValue() < caixa.getCaixa().doubleValue()){
						
						caixa.setCaixa(caixa.getCaixa().subtract(valorInformado)); 
						caixaDAO.editar(caixa);
					}else{
						Messages.addGlobalError("Ocorreu um erro ao tentar verificar o Caixa");
						Messages.addGlobalError("Ocorreu um erro ao tentar sacar o Dinheiro no Caixa");
						Messages.addGlobalError("Tente refazer a transação, 'valor a ser sacado não pode ser superior ao informado pelo sistema!'");
					}
			
			} catch (RuntimeException erro) {
				Messages.addGlobalError("Ocorreu um erro ao tentar verificar o Caixa");
				erro.printStackTrace();
			}
		}
		
		public void somarCaixa(){
			
			caixa = null;
			try{
					CaixaDAO caixaDAO = new CaixaDAO();
					caixa = caixaDAO.buscar(1l);
					
					if(valorInformado.doubleValue() < caixa.getCaixa().doubleValue()){
						
						caixa.setCaixa(caixa.getCaixa().subtract(valorInformado)); 
						caixaDAO.editar(caixa);
					}else{
						Messages.addGlobalError("Ocorreu um erro ao tentar verificar o Caixa");
						Messages.addGlobalError("Ocorreu um erro ao tentar sacar o Dinheiro no Caixa");
						Messages.addGlobalError("Tente refazer a transação, 'valor a ser sacado não pode ser superior ao informado pelo sistema!'");
					}
			
			} catch (RuntimeException erro) {
				Messages.addGlobalError("Ocorreu um erro ao tentar verificar o Caixa");
				erro.printStackTrace();
			}
		}
		
		
		public void pegaValorInicial() {
			carregaValor();
		//	valorInformado = valorInformado;
			
		//	valorInformado = new BigDecimal("200.00");
			
		}

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
}
