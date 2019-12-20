package br.com.datasalles.dao;

import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
//import org.omnifaces.util.Messages;
import br.com.datasalles.domain.Sangria;
import br.com.datasalles.util.HibernateUtil;


public class SangriaDAO extends GenericDAO<Sangria>{
	Sangria sangria = new Sangria();
	Transaction transacao = null;
	List<Sangria> auxList;
	Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

	@SuppressWarnings("unchecked")
	public void atualizar(BigDecimal valor){
		System.out.println("entrou aqui");

		try{
			Criteria consulta = sessao.createCriteria(Sangria.class);
			consulta.add(Restrictions.idEq( ));
			auxList = consulta.list();
			System.out.println("busca");

		}catch(RuntimeException erro){
			throw erro;
		}finally {
			sessao.close();
		}	
		System.out.println("saiu da busca");

		if(auxList.isEmpty()){
			sangria = new Sangria();
			sangria.setSangria(new BigDecimal("0"));
			sangria.setCodigo( );
			
			System.out.println("esta vazio");
			Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
			Transaction transacao = null;

			try{
				sangria.setSangria(sangria.getSangria().add(valor));
				System.out.println(sangria.getSangria());
				transacao = sessao.beginTransaction();
				sessao.merge(sangria);
				transacao.commit();

			}catch(RuntimeException erro){
				if(transacao != null){
					transacao.rollback();
					throw erro;
				}finally {
					sessao.close();
				}

			}else{

				sangria =  auxList.get(0);
				System.out.println("esta vazio");
				Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
				Transaction transacao = null;
				try{
					sangria.setSangria(sangria.getSangria().add(valor));
					System.out.println(sangria.getSangria());
					transacao = sessao.beginTransaction();a
					sessao.merge(sangria);
					transacao.commit();

				}catch(RuntimeException erro){
					if(transacao != null){
						transacao.rollback(); 
					}
					throw erro;
				}finally {
					sessao.close();
				}


			}	




		}

