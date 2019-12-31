package br.com.datasalles.dao;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import br.com.datasalles.domain.Fechamento;
import br.com.datasalles.util.HibernateUtil;

public class FechamentoDAO extends GenericDAO<Fechamento> {
	
	public Fechamento buscar(Date dataFechamento) {
		Session sessao =  HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta	= sessao.createCriteria(Fechamento.class);
			consulta.add(Restrictions.eq("dataFechamento", dataFechamento));
			Fechamento resultado = (Fechamento) consulta.uniqueResult();
			return resultado;
		}catch(RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
		
		
	}
	
	
}
