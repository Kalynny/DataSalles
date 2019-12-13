package br.com.datasalles.dao;


import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import br.com.datasalles.domain.PesquisaVenda;
import br.com.datasalles.util.HibernateUtil;

public class PesquisaVendaDAO  extends GenericDAO<PesquisaVenda>{
	
	@SuppressWarnings("rawtypes")
	public List listarPorData(Date dataInicio, Date dataFim){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();		
				try{
					
					Criteria consulta = sessao.createCriteria(PesquisaVenda.class);
										
					consulta.add(Restrictions.ge("dataDoRecebimento",dataInicio));
				     
				    consulta.add(Restrictions.le("dataDoRecebimento",dataFim));
					
					List resultado = consulta.list();
					
					return resultado;	
				}catch(RuntimeException erro){
					throw erro;
				}finally {
					sessao.close();
				}
	}

}

