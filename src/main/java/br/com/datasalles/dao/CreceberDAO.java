package br.com.datasalles.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import br.com.datasalles.domain.Creceber;
import br.com.datasalles.util.HibernateUtil;

public class CreceberDAO extends GenericDAO<Creceber> {

	public void salvar(Creceber creceber){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {

			transacao = sessao.beginTransaction();
			creceber = (Creceber) sessao.merge(creceber);

		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
