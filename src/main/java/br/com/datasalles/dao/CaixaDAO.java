package br.com.datasalles.dao;


import org.hibernate.Criteria;
import org.hibernate.Session;
import br.com.datasalles.domain.Caixa;
import br.com.datasalles.util.HibernateUtil;

public class CaixaDAO extends GenericDAO<Caixa>{
	
	public Caixa buscar() {
		Session sessao =  HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta	= sessao.createCriteria(Caixa.class);
			//consulta.add(Restrictions.eq());
			Caixa resultado = (Caixa) consulta.uniqueResult();
			return resultado;
		}catch(RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	
	}
	
}
