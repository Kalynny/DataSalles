
package br.com.datasalles.dao;


import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import br.com.datasalles.domain.ItemAvaria;
import br.com.datasalles.domain.Produto;
import br.com.datasalles.domain.Avaria;
import br.com.datasalles.util.HibernateUtil;

public class AvariaDAO extends GenericDAO<Avaria> {
	public void salvar(Avaria avaria, List<ItemAvaria> itensAvaria){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
		
			sessao.save(avaria);
			
			for(int posicao = 0; posicao < itensAvaria.size(); posicao++){
				ItemAvaria itemAvaria = itensAvaria.get(posicao);
				itemAvaria.setAvaria(avaria);
				
				sessao.save(itemAvaria);
				
				Produto produto = itemAvaria.getProduto();
				int qtde = produto.getQuantidade() - itemAvaria.getQuantidade();
				
				if(qtde >= 0){
				produto.setQuantidade(new Short((qtde) + ""));
				
				sessao.update(produto);
				
				}else{
				throw new RuntimeException("Quantidade insuficiente em estoque");
				}
				
			}
				transacao.commit();
			}	catch (RuntimeException erro) {
				if (transacao != null) {
					transacao.rollback();
				}
				throw erro;
				} finally {
				sessao.close();
			}
	}
	
	@SuppressWarnings("unchecked")
	public List<Avaria> listarPorData(Date dataInicio, Date dataFim){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();		
		try{
			Criteria consulta = sessao.createCriteria(Avaria.class);
			consulta.add(Restrictions.between("horario", dataInicio, dataFim));
			return consulta.list();	
		}catch(RuntimeException erro){
			throw erro;
		}finally {
			sessao.close();
		}
	}
}

