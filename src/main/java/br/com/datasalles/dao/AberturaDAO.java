package br.com.datasalles.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import br.com.datasalles.domain.Abertura;
import br.com.datasalles.domain.Funcionario;
import br.com.datasalles.util.HibernateUtil;

public class AberturaDAO extends GenericDAO<Abertura> {
	
	public List<Abertura> listarOrdenado() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Funcionario.class);
			@SuppressWarnings("unchecked")
			List<Abertura> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
