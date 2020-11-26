package br.com.datasalles.Bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.omnifaces.util.Messages;
import br.com.datasalles.dao.OrcamentoCDAO;
import br.com.datasalles.domain.OrcamentoC;
import br.com.datasalles.util.HibernateUtil;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PesquisaOrcaCBean implements Serializable {

	private List<OrcamentoC> orcamentos;
	private Date dataInicio = new Date(System.currentTimeMillis());
	private Date dataFim  = new Date(System.currentTimeMillis());
	private BigDecimal valorTotal;
	

	public List<OrcamentoC> getOrcamentos() {
		return orcamentos;
	}
	public void setOrcamentos(List<OrcamentoC> orcamentos) {
		this.orcamentos = orcamentos;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	public void calculaValorTotal(){
		valorTotal = new BigDecimal("0");
		if(orcamentos.size() > 0){
			for(int i=0; i<orcamentos.size(); i++){
				valorTotal = valorTotal.add(orcamentos.get(i).getPrecoTotal());
			}
		}		
	}

	@PostConstruct
	public void init() {
		startDatas();
		listar();
	}

	public void startDatas(){
		Calendar c1 = Calendar.getInstance();
		c1.set(Calendar.DAY_OF_MONTH, 1);
		c1.set(Calendar.HOUR, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);

		dataInicio = c1.getTime();
		dataFim = new Date();
	}


	@PostConstruct  
	public void listar(){
		try{

			if(dataInicio==null || dataFim==null){
				startDatas();
			}

			valorTotal = new BigDecimal("0");
			OrcamentoCDAO orcamentocDAO = new OrcamentoCDAO();			
			orcamentos = orcamentocDAO.listarPorData(dataInicio, dataFim);
			calculaValorTotal();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Estoque Insuficiente");
			erro.printStackTrace();
		}

	}

	public String importarOrcamentoC(Long codigo) {
		if(importIsTrue(codigo)==false) {			
			return "compras.xhtml?orcamentoc="+codigo+"&faces-redirect=true";
		}else {
			Messages.addGlobalError("Pedido Já Importado");
			return "pesquisaOC.xhtm&faces-redirect=true";
		}	
	}
	
	public Boolean importIsTrue(Long code) {
			Boolean retorno = false;
			String select = null;
					
			Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
			Transaction transacao = null;
			try {
				transacao= sessao.beginTransaction();
				select = " SELECT * FROM compra where nfcompra is not  null and pedido ="+code;
				SQLQuery query = sessao.createSQLQuery(select);
				
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);				
				List data = query.list();

		         for(Object object : data)  {
		        	 retorno = true;
		         }
		         transacao.commit();
					
			} catch (HibernateException e) {
				if (transacao != null)
					transacao.rollback();
				e.printStackTrace();
			} finally {
				sessao.close();
			}
		
		return retorno;
	}

}
