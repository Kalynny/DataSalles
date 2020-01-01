package br.com.datasalles.dao;

import java.util.Date;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import br.com.datasalles.domain.Sangria;
import br.com.datasalles.util.HibernateUtil;

public class SangriaDAO extends GenericDAO<Sangria> {
	
	public Sangria buscar(Date dataSangria) {
		Session sessao =  HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta	= sessao.createCriteria(Sangria.class);
			consulta.add(Restrictions.eq("dataSangria", dataSangria));
			Sangria resultado = (Sangria) consulta.uniqueResult();
			return resultado;
		}catch(RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
		
		
	}
	
	
}
