package br.com.datasalles.dao;


import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import br.com.datasalles.domain.PesquisaOrcaC;
import br.com.datasalles.util.HibernateUtil;

public class PesquisaOrcaCDAO extends GenericDAO<PesquisaOrcaC>{
	
	@SuppressWarnings("rawtypes")
	public List listarPorData(Date dataInicio, Date dataFim){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();		
				try{
					
					Criteria consulta = sessao.createCriteria(PesquisaOrcaC.class);
										
					consulta.add(Restrictions.ge("dataDoPagamento",dataInicio));
				     
				    consulta.add(Restrictions.le("dataDoPagamento",dataFim));
					
					List resultado = consulta.list();
					
					return resultado;	
				}catch(RuntimeException erro){
					throw erro;
				}finally {
					sessao.close();
				}
	}

}

