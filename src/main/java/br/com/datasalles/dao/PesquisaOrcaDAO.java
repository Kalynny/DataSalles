package br.com.datasalles.dao;


import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import br.com.datasalles.domain.Orcapesquisa;
import br.com.datasalles.util.HibernateUtil;

public class OrcapesquisaDAO  extends GenericDAO<Orcapesquisa>{
	
	Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

	@SuppressWarnings("rawtypes")
	public List listarPorData(Date dataInicio, Date dataFim){
			
				try{
					
					Criteria consulta = sessao.createCriteria(Orcapesquisa.class);
										
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

