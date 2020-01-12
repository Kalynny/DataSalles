package br.com.datasalles.dao;


import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.datasalles.domain.ItemOrca;
import br.com.datasalles.domain.Orcamento;
import br.com.datasalles.util.HibernateUtil;

public class OrcamentoDAO extends GenericDAO<Orcamento> {

	public void salvar(Orcamento orcamento, List<ItemOrca> itensOrca){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();

			sessao.save(orcamento);

			for(int posicao = 0; posicao < itensOrca.size(); posicao++){
				ItemOrca itemOrca = itensOrca.get(posicao);
				itemOrca.setOrcamento(orcamento);

				sessao.save(itemOrca);

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
	public List<Orcamento> listarPorData(Date dataInicio, Date dataFim){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();		
		try{
			Criteria consulta = sessao.createCriteria(Orcamento.class);
			consulta.add(Restrictions.between("horario", dataInicio, dataFim));
			return consulta.list();	
		}catch(RuntimeException erro){
			throw erro;
		}finally {
			sessao.close();
		}
	}
}

