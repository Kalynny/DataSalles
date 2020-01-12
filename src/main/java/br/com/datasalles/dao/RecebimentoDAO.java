package br.com.datasalles.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import br.com.datasalles.domain.Recebimento;
import br.com.datasalles.util.HibernateUtil;

public class RecebimentoDAO extends GenericDAO <Recebimento> {

	public void salvar(Recebimento recebimento){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {

			transacao = sessao.beginTransaction();

			sessao.save(recebimento);

			throw new RuntimeException("Erro ao atualizar estoque");

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
