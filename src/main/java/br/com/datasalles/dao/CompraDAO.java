package br.com.datasalles.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.omnifaces.util.Messages;
import br.com.datasalles.domain.ItemCompra;
import br.com.datasalles.domain.Pagamento;
import br.com.datasalles.domain.Produto;
import br.com.datasalles.domain.Compra;
import br.com.datasalles.domain.Cpagar;
import br.com.datasalles.util.HibernateUtil;

public class CompraDAO extends GenericDAO<Compra> {

	public void salvar(Compra compra, List<ItemCompra> itensCompra, Pagamento pagamento){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			
			transacao = sessao.beginTransaction();
			
			compra = (Compra) sessao.merge(compra);
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
					throw new RuntimeException("Quatidade do Estoque menor que o solicitado");
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

		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		org.hibernate.Transaction transacao = null;

		try {

			transacao = sessao.beginTransaction(); 

			Cpagar cpagar = new Cpagar();
			cpagar.setVencimento(itemCompra.getCompra().getVencimento());
			cpagar.setAtual(new Date());
			cpagar.setFornecedor(itemCompra.getCompra().getFornecedor());
			cpagar.setTipo(itemCompra.getCompra().getTipopagc().getDescricao());
			cpagar.setPrecoTotal(itemCompra.getCompra().getPrecoTotal());

			sessao.save(cpagar);

			transacao.commit();

		} catch (HibernateException e) {
			if (transacao != null)
				transacao.rollback();
			e.printStackTrace();
		} finally {
			sessao.close();

			Messages.addGlobalInfo("Compra realizada com sucesso!!");
		}

	}

	@SuppressWarnings("unchecked")
	public List<Compra> listarPorData(Date dataInicio, Date dataFim){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();		
		try{
			Criteria consulta = sessao.createCriteria(Compra.class);
			consulta.add(Restrictions.between("atual", dataInicio, dataFim));
			return consulta.list();	
		}catch(RuntimeException erro){
			throw erro;
		}finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Compra> listar() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Compra.class);
			consulta.addOrder(Order.asc("codigo"));
			List<Compra> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Compra> listarAvista() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Compra.class, "c");
			consulta.add(Restrictions.eq("c.tipopagc.id", Compra.TIPOPAGTO_AVISTA));
			consulta.add(Restrictions.eq("c.status", Compra.STATUS_ABERTO));
			consulta.addOrder(Order.asc("c.codigo"));
			List<Compra> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	public void salvar(Compra compra, List<ItemCompra> itens) {


	}


}

