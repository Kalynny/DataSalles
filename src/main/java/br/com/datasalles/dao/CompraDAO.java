package br.com.datasalles.dao;


import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.omnifaces.util.Messages;
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
	
	
	public void salvarBoleto(Compra compra, List<ItemCompra> itensCompra){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			
			sessao.save(compra);
					
			for(int posicao = 0; posicao < itensCompra.size(); posicao++){
				ItemCompra itemCompra = itensCompra.get(posicao);
				itemCompra.setCompra(compra);
				
				itemCompra.getCompra();
				itemCompra.getCompra().getCodigo();
				itemCompra.getCompra().getFuncionario().getCodigo();
				
				sessao.save(itemCompra);
				
				pagamentoBoleto(itemCompra);
			
				
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
	
	public void pagamentoBoleto(ItemCompra itemCompra) {
		
		//DatasallesService sevico = new DatasallesService();
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		org.hibernate.Transaction transacao = null;

		try {
		
		transacao= sessao.beginTransaction(); 

		
		String sql = "insert into cpagar VALUES (null, sysdate(),"+itemCompra.getCompra().getPrecoTotal()+",sysdate(),"+itemCompra.getCompra().getFornecedor().getCodigo()+","+itemCompra.getCompra().getTipopagc().getCodigo()+");";

		SQLQuery query = sessao.createSQLQuery(sql);
				
		int result = query.executeUpdate();
		
		transacao.commit();
		System.out.println(result);

	} catch (HibernateException e) {
		if (transacao != null)
			transacao.rollback();
		e.printStackTrace();
	} finally {
		sessao.close();

		Messages.addGlobalInfo("Compra realizada com sucesso!!");
	}
		
	}
}

