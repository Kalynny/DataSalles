package br.com.datasalles.dao;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import br.com.datasalles.domain.ItemCompra;
import br.com.datasalles.domain.Produto;
import br.com.datasalles.domain.Compra;
import br.com.datasalles.util.HibernateUtil;

public class CompraDAO extends GenericDAO<Compra> {
	public void salvar(Compra compra, List<ItemCompra> itensCompra){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
		
			sessao.save(compra);
					
			for(int posicao = 0; posicao < itensCompra.size(); posicao++){
				ItemCompra itemCompra = itensCompra.get(posicao);
				itemCompra.setCompra(compra);
				
				sessao.save(itemCompra);
				
				Produto produto = itemCompra.getProduto();
				int qtde = produto.getQuantidade() + itemCompra.getQuantidade();
				
				if(qtde >= 0){
				produto.setQuantidade(new Short((qtde) + ""));
				
				sessao.update(produto);
				
				}else{
				throw new RuntimeException("Erro ao atualizar estoque");
				}
				
				
			}
			
			transacao.commit();
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

