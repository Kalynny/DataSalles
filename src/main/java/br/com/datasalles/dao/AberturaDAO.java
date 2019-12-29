package br.com.datasalles.dao;

import java.util.Date;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import br.com.datasalles.domain.Abertura;
import br.com.datasalles.util.HibernateUtil;

public class AberturaDAO extends GenericDAO<Abertura> {
	
	public Abertura buscar(Date dataAbertura) {
		Session sessao =  HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta	= sessao.createCriteria(Abertura.class);
			consulta.add(Restrictions.eq("dataAbertura", dataAbertura));
			Abertura resultado = (Abertura) consulta.uniqueResult();
			return resultado;
		}catch(RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
		
		
	}
	
	
}
