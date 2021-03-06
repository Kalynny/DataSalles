package br.com.datasalles.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import br.com.datasalles.domain.Produto;
import br.com.datasalles.util.HibernateUtil;

public class ProdutoDAO extends GenericDAO<Produto> {

	public Produto buscarnome(String descricao) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Produto.class);
			consulta.add(Restrictions.eq("descricao", descricao));
			consulta.addOrder(Order.asc("descricao"));
			Produto resultado = (Produto) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Produto> buscar(String descricao) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Produto.class);
			if(descricao!=null && !"".equals(descricao)){
				consulta.add(Restrictions.like("descricao", "%"+descricao+"%").ignoreCase());
			}
			
			consulta.addOrder(Order.asc("descricao"));
			return consulta.list();
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	



}
