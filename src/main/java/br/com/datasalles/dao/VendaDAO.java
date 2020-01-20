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
import br.com.datasalles.domain.Venda;
import br.com.datasalles.impl.GenericImpl;
import br.com.datasalles.domain.Creceber;
import br.com.datasalles.domain.ItemVenda;
import br.com.datasalles.domain.Produto;
import br.com.datasalles.domain.Recebimento;
import br.com.datasalles.util.HibernateUtil;

public class VendaDAO extends GenericDAO<Venda> implements GenericImpl {

	public void salvar(Venda venda, List<ItemVenda> itensVenda, Recebimento recebimento){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {

			transacao = sessao.beginTransaction();

			venda = (Venda) sessao.merge(venda);
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


				Produto produto = itemVenda.getProduto();
				int qtde = produto.getQuantidade() - itemVenda.getQuantidade();

				if(qtde >= 0){
					produto.setQuantidade(new Short((qtde) + ""));

					sessao.update(produto);


				}else{
					throw new RuntimeException("Erro ao atualizar estoque");
				}

			}

			pagamentoBoleto(venda);

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



	public void pagamentoBoleto(Venda venda) {

		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		org.hibernate.Transaction transacao = null;

		try  {

			transacao= sessao.beginTransaction(); 

			Creceber creceber = new Creceber();
			creceber.setVencimento(venda.getVencimento());
			creceber.setHorario(venda.getHorario());
			creceber.setCliente(venda.getCliente());
			creceber.setTipo(venda.getTipopag().getDescricao());
			creceber.setPrecoTotal(venda.getPrecoTotal());

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

	@SuppressWarnings("unchecked")
	public List<Venda> listarPorData(Date dataInicio, Date dataFim){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();		
		try{
			Criteria consulta = sessao.createCriteria(Venda.class);
			consulta.add(Restrictions.between("horario", dataInicio, dataFim));
			return consulta.list();	
		}catch(RuntimeException erro){
			throw erro;
		}finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Venda> listar() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Venda.class);
			consulta.addOrder(Order.asc("codigo"));
			List<Venda> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Venda> listarAvista() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Venda.class, "v");
			consulta.add(Restrictions.eq("v.tipopag.id", Venda.TIPOPAGTO_AVISTA));
			consulta.addOrder(Order.asc("v.codigo"));
			List<Venda> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@Override
	public void salvar(Venda venda, List<ItemVenda> itens) {


	}

}

