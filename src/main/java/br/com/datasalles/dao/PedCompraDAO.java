package br.com.datasalles.dao;


import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import br.com.datasalles.domain.ItemPedCompra;
import br.com.datasalles.domain.PedCompra;
import br.com.datasalles.util.HibernateUtil;

public class PedCompraDAO extends GenericDAO<PedCompra> {
	
	public void salvar(PedCompra pedcompra, List<ItemPedCompra> itensPedcompra){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
		
			sessao.save(pedcompra);
			
			for(int posicao = 0; posicao < itensPedcompra.size(); posicao++){
				ItemPedCompra itemPedCompra = itensPedcompra.get(posicao);
				itemPedCompra.setPedcompra(pedcompra);
				
				sessao.save(itemPedCompra);
				
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
	public List<PedCompra> listarPorData(Date dataInicio, Date dataFim){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();		
		try{
			Criteria consulta = sessao.createCriteria(PedCompra.class);
			consulta.add(Restrictions.between("horario", dataInicio, dataFim));
			return consulta.list();	
		}catch(RuntimeException erro){
			throw erro;
		}finally {
			sessao.close();
		}
	}
}

