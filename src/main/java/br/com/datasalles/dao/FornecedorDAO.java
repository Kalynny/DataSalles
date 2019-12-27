package br.com.datasalles.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import br.com.datasalles.domain.Fornecedor;
import br.com.datasalles.util.HibernateUtil;

public class FornecedorDAO extends GenericDAO<Fornecedor> {
	
	@SuppressWarnings("unchecked")
	public List<Fornecedor> listarOrdenado() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Fornecedor.class);
			consulta.addOrder(Order.asc("descricao"));
			List<Fornecedor> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	

}
