package br.com.datasalles.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.datasalles.domain.Usuario;
import br.com.datasalles.util.HibernateUtil;

public class UsuarioDAO extends GenericDAO<Usuario> {
	
	Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
	
	public Usuario autenticar(String cpf, String senha){
		
		try{
			Criteria consulta = sessao.createCriteria(Usuario.class);
			consulta.createAlias("pessoa", "p");
			consulta.add(Restrictions.eq("p.cpf", cpf));
			SimpleHash hash = new SimpleHash("md5", senha);
			consulta.add(Restrictions.eq("senha", hash.toHex()));
			Usuario resultado = (Usuario) consulta.uniqueResult(); 

			return resultado;

		}catch(RuntimeException erro){
			throw erro;
		}finally{
			sessao.close();
		}

	}


}
