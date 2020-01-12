package br.com.datasalles.dao;

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

			sessao.save(pagamento);

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
