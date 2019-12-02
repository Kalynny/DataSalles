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

	Transaction transacao = null;//inicia dado to tipo transação
	List<Sangria> auxList;
	Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
	@SuppressWarnings("unchecked")
	public void atualizar(BigDecimal valor){
		
			System.out.println("entrou aqui");
			
			try{
				//captura os critérios de consulta
				Criteria consulta = sessao.createCriteria(Sangria.class);
				consulta.add(Restrictions.idEq(1l));// coloca restrições de busca
				 
				auxList = consulta.list();
				
				
				System.out.println("busca");
				
	
			}catch(RuntimeException erro){
				throw erro;//display error message
			}finally {
				sessao.close();//libera recursos 
			}	
			
			
			
			
			System.out.println("saiu da busca");
			
					
			
			if(auxList.isEmpty()){
				sangria = new Sangria();
				sangria.setSangria(new BigDecimal("0"));
				sangria.setCodigo(1L);
			
				System.out.println("esta vazio");
				// abre uma secao da fabrica de seções  e a captura
				Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
				Transaction transacao = null;//inicia dado do tipo transação
				
				try{
					
						
						sangria.setSangria(sangria.getSangria().add(valor));
						System.out.println(sangria.getSangria());
						// esse transação é o pivor a ser usado para ter certeza que a transação terá sucesso
						transacao = sessao.beginTransaction();//captura a transação pela sessao aberta
						sessao.merge(sangria);//atualiza entidade
						transacao.commit();//confirma transação
						
				}catch(RuntimeException erro){
					if(transacao != null){
						transacao.rollback(); // desfaz auterações
					}
					throw erro;//display error message
				}finally {
					sessao.close();//libera recursos
				}
				
				
				
				
				
			}else{
				
						sangria =  auxList.get(0);
					
						System.out.println("esta vazio");
						// abre uma secao da fabrica de seções  e a captura
						Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
						Transaction transacao = null;//inicia dado do tipo transação
						
						try{
							
								
								sangria.setSangria(sangria.getSangria().add(valor));
								System.out.println(sangria.getSangria());
								// esse transação é o pivor a ser usado para ter certeza que a transação terá sucesso
								transacao = sessao.beginTransaction();//captura a transação pela sessao aberta
								sessao.merge(sangria);//atualiza entidade
								transacao.commit();//confirma transação
								
						}catch(RuntimeException erro){
							if(transacao != null){
								transacao.rollback(); // desfaz auterações
							}
							throw erro;//display error message
						}finally {
							sessao.close();//libera recursos
						}
				
				
			}	

				
			

	}
	
	

	

}
