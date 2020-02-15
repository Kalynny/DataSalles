package br.com.datasalles.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.datasalles.domain.Cpagar;
import br.com.datasalles.util.HibernateUtil;

public class CpagarDAO extends GenericDAO<Cpagar> {
	
	public void salvar(Cpagar cpagar){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {

			transacao = sessao.beginTransaction();
			cpagar = (Cpagar) sessao.merge(cpagar);

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
