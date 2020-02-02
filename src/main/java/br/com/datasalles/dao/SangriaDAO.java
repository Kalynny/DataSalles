package br.com.datasalles.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
	
	@SuppressWarnings({ "rawtypes" })
	public BigDecimal valorSangria() {
		BigDecimal valor = null;
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction tx = null;
		try{
			tx = sessao.beginTransaction();

			String sql = "select valorSangria from sangria where dataSangria = CURRENT_DATE order by dataSangria desc limit 1";

			SQLQuery query = sessao.createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List data = query.list();

			for(Object object : data)  {
				Map row = (Map)object;
				valor = new BigDecimal(row.get("valorSangria").toString());;
			}
			tx.commit();

		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			sessao.close();
		}
		
		return valor;
	}


}
