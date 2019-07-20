package br.com.datasalles.test;

import org.hibernate.Session;
import org.junit.Test;

import br.com.datasalles.util.HibernateUtil;

public class HibernateUtilTest {
	@Test
	
	public void conectar(){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		sessao.close();
		HibernateUtil.getFabricaDeSessoes().close();
	}
}

