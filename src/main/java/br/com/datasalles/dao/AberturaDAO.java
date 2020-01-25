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
	
	@SuppressWarnings({ "rawtypes" })
	public BigDecimal valorAbertura() {
		BigDecimal valor = null;
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction tx = null;
		try{
			tx = sessao.beginTransaction();

			String sql = "select valorAbertura from abertura where dataAbertura = CURRENT_DATE order by dataAbertura desc limit 1";

			SQLQuery query = sessao.createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List data = query.list();

			for(Object object : data)  {
				Map row = (Map)object;
				valor = new BigDecimal(row.get("valorAbertura").toString());;
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
