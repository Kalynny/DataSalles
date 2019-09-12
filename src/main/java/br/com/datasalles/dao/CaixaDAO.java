package br.com.datasalles.dao;

import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import br.com.datasalles.domain.Caixa;
import br.com.datasalles.util.HibernateUtil;

public class CaixaDAO extends GenericDAO<Caixa>{
	Caixa caixa = new Caixa();

	Transaction transacao = null;//inicia dado to tipo transação
	List<Caixa> auxList;
	Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
	@SuppressWarnings("unchecked")
	public void atualizar(BigDecimal valor){
		
			System.out.println("entrou aqui");
			
			try{
				//captura os critérios de consulta
				Criteria consulta = sessao.createCriteria(Caixa.class);
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
				caixa = new Caixa();
				caixa.setCaixa(new BigDecimal("0"));
				caixa.setCodigo(1L);
			
				System.out.println("esta vazio");
				// abre uma secao da fabrica de seções  e a captura
				Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
				Transaction transacao = null;//inicia dado do tipo transação
				
				try{
					
						
						caixa.setCaixa(caixa.getCaixa().add(valor));
						System.out.println(caixa.getCaixa());
						// esse transação é o pivor a ser usado para ter certeza que a transação terá sucesso
						transacao = sessao.beginTransaction();//captura a transação pela sessao aberta
						sessao.merge(caixa);//atualiza entidade
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
				
						caixa =  auxList.get(0);
					
						System.out.println("esta vazio");
						// abre uma secao da fabrica de seções  e a captura
						Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
						Transaction transacao = null;//inicia dado do tipo transação
						
						try{
							
								
								caixa.setCaixa(caixa.getCaixa().add(valor));
								System.out.println(caixa.getCaixa());
								// esse transação é o pivor a ser usado para ter certeza que a transação terá sucesso
								transacao = sessao.beginTransaction();//captura a transação pela sessao aberta
								sessao.merge(caixa);//atualiza entidade
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
