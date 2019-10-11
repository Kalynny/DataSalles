package br.com.datasalles.dao;

//import java.math.BigDecimal;
//import java.util.List;
//import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
//import org.hibernate.criterion.Restrictions;
import br.com.datasalles.domain.Caixa;
import br.com.datasalles.util.HibernateUtil;

public class CaixaDAO extends GenericDAO<Caixa>{
	Caixa caixa = new Caixa();

	public void salvar(Caixa caixa){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
		
			sessao.save(caixa);
						
				}	catch (RuntimeException erro) {
					if (transacao != null) {
						transacao.rollback();
					}
					throw erro;
					} finally {
					sessao.close();
				}
	}

}
