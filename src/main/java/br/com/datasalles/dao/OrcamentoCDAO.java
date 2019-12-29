package br.com.datasalles.dao;


import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import br.com.datasalles.domain.ItemOrcaC;
import br.com.datasalles.domain.OrcamentoC;
import br.com.datasalles.util.HibernateUtil;

public class OrcamentoCDAO extends GenericDAO<OrcamentoC> {
	
	public void salvar(OrcamentoC orcamentoc, List<ItemOrcaC> itensOrcaC){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
		
			sessao.save(orcamentoc);
			
			for(int posicao = 0; posicao < itensOrcaC.size(); posicao++){
				ItemOrcaC itemOrcaC = itensOrcaC.get(posicao);
				itemOrcaC.setOrcamentoc(orcamentoc);
				
				sessao.save(itemOrcaC);
				
				}
			
				transacao.commit();
				}catch (RuntimeException erro) {
				if (transacao != null) {
					transacao.rollback();
				}
				throw erro;
				} finally {
				sessao.close();
			}
		}

	
	@SuppressWarnings("unchecked")
	public List<OrcamentoC> listarPorData(Date dataInicio, Date dataFim){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();		
		try{
			Criteria consulta = sessao.createCriteria(OrcamentoC.class);
			consulta.add(Restrictions.between("horario", dataInicio, dataFim));
			return consulta.list();	
		}catch(RuntimeException erro){
			throw erro;
		}finally {
			sessao.close();
		}
	}
}

