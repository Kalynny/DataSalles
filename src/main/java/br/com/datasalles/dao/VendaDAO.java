package br.com.datasalles.dao;


import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.omnifaces.util.Messages;
import br.com.datasalles.domain.Venda;
import br.com.datasalles.domain.Creceber;
import br.com.datasalles.domain.ItemVenda;
import br.com.datasalles.domain.Produto;
import br.com.datasalles.util.HibernateUtil;

public class VendaDAO extends GenericDAO<Venda> {
	
	public void salvar(Venda venda, List<ItemVenda> itensVenda){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
		
			sessao.save(venda);
			
			for(int posicao = 0; posicao < itensVenda.size(); posicao++){
				ItemVenda itemVenda = itensVenda.get(posicao);
				itemVenda.setVenda(venda);
				
				sessao.save(itemVenda);
				
				Produto produto = itemVenda.getProduto();
				int qtde = produto.getQuantidade() - itemVenda.getQuantidade();
				
				if(qtde >= 0){
				produto.setQuantidade(new Short((qtde) + ""));
				
				sessao.update(produto);
								
				}else{
				throw new RuntimeException("Erro ao atualizar estoque");
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
	
	public void salvarBoleto(Venda venda, List<ItemVenda> itensvenda){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			
			sessao.save(venda);
			
			
			for(int posicao = 0; posicao < itensvenda.size(); posicao++){
				ItemVenda itemVenda = itensvenda.get(posicao);
				itemVenda.setVenda(venda);
				
				itemVenda.getVenda();
				itemVenda.getVenda().getCodigo();
				itemVenda.getVenda().getFuncionario().getCodigo();
				
				sessao.save(itemVenda);
				pagamentoBoleto(itemVenda);
								
				Produto produto = itemVenda.getProduto();
				int qtde = produto.getQuantidade() - itemVenda.getQuantidade();
				
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
	
	
	
	public void pagamentoBoleto(ItemVenda itemVenda) {
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		org.hibernate.Transaction transacao = null;

		try  {
		
		transacao= sessao.beginTransaction(); 
		
		Creceber creceber = new Creceber();
		creceber.setVencimento(itemVenda.getVenda().getVencimento());
		creceber.setHorario(new Date());
		creceber.setCliente(itemVenda.getVenda().getCliente());
		creceber.setTipo(itemVenda.getVenda().getTipopag().getDescricao());
		creceber.setPrecoTotal(itemVenda.getVenda().getPrecoTotal());
		
		sessao.save(creceber);
		
		transacao.commit();
		

	} catch (HibernateException e) {
		if (transacao != null)
			transacao.rollback();
		e.printStackTrace();
	} finally {
		sessao.close();

		Messages.addGlobalInfo("Venda realizada com sucesso!!");
	}
		
	}
	
	public void excluir(Venda venda, List<ItemVenda> itensVenda){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
		
			sessao.delete(venda);
			
			for(int posicao = 0; posicao < itensVenda.size(); posicao++){
				ItemVenda itemVenda = itensVenda.get(posicao);
				itemVenda.setVenda(venda);
				
				sessao.delete(itemVenda);
				
				Produto produto = itemVenda.getProduto();
				int qtde = produto.getQuantidade() - itemVenda.getQuantidade();
				
				if(qtde >= 0){
				produto.setQuantidade(new Short((qtde) + ""));
				
				sessao.update(produto);
								
				}else{
				throw new RuntimeException("Erro ao atualizar estoque");
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
	
}

