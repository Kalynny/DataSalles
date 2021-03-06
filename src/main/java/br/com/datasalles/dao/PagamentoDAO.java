package br.com.datasalles.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import br.com.datasalles.domain.Pagamento;
import br.com.datasalles.util.HibernateUtil;

public class PagamentoDAO extends GenericDAO <Pagamento> {

	public void salvar(Pagamento pagamento){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {

			transacao = sessao.beginTransaction();

			sessao.saveOrUpdate(pagamento);
			
			transacao.commit();

		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings({ "rawtypes" })
	public BigDecimal valorPagamento() {
		BigDecimal valor = null;
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction tx = null;
		try{
			tx = sessao.beginTransaction();

			String sql = "select sum(precoTotal) as total from pagamento where horario = CURRENT_DATE group by horario ";

			SQLQuery query = sessao.createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List data = query.list();

			for(Object object : data)  {
				Map row = (Map)object;
				valor = new BigDecimal(row.get("total").toString());;
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
