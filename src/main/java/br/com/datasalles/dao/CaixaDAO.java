package br.com.datasalles.dao;


import org.hibernate.Session;
import org.hibernate.Transaction;
import br.com.datasalles.domain.Caixa;
import br.com.datasalles.util.HibernateUtil;


public class CaixaDAO extends GenericDAO<Caixa> {
	
	public void salvar(Caixa caixa){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
					transacao = sessao.beginTransaction();
					sessao.save(caixa);
					transacao.commit();	}
					catch (RuntimeException erro) {
					if (transacao != null) {
						transacao.rollback();
					}
					throw erro;
					} finally {
					sessao.close();
					}
		
				}
	
}
