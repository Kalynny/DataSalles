package br.com.datasalles.Bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.omnifaces.util.Messages;
import br.com.datasalles.dao.CaixaDAO;
import br.com.datasalles.domain.Caixa;
import br.com.datasalles.domain.Venda;


@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CaixaBean implements Serializable{
	private List<Venda> vendas;
	private Caixa caixa;
	private Venda venda;
	private BigDecimal valorInformado;

	
	
	public BigDecimal getValorInformado() {
		return valorInformado;
	}

	public void setValorInformado(BigDecimal valorInformado) {
		this.valorInformado = valorInformado;
	}

	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}
		
	public List<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}
	
	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

		//preenche uma lista com registro de estados
		@PostConstruct // essa anotation diz que o metodo tem que disparar no momento em que a tela é criada 
		public void listar(){
			valorInformado = new BigDecimal("0");
			try{
				caixa = null;
				CaixaDAO caixaDAO = new CaixaDAO();
				caixa = caixaDAO.buscar(1l);
			} catch (RuntimeException erro) {
				Messages.addGlobalError("Ocorreu um erro ao tentar verificar o Caixa");
				erro.printStackTrace();
			}
		}
		
		public void sacarCaixa(){
			
			caixa = null;
			try{
					CaixaDAO caixaDAO = new CaixaDAO();
					caixa = caixaDAO.buscar(1l);
					
					if(valorInformado.doubleValue() < caixa.getCaixa().doubleValue()){
						
						caixa.setCaixa(caixa.getCaixa().subtract(valorInformado)); 
						caixaDAO.editar(caixa);
					}else{
						Messages.addGlobalError("Ocorreu um erro ao tentar verificar o Caixa");
						Messages.addGlobalError("Ocorreu um erro ao tentar sacar o Dinheiro no Caixa");
						Messages.addGlobalError("Tente refazer a transação, 'valor a ser sacado não pode ser superior ao informado pelo sistema!'");
					}
			
			} catch (RuntimeException erro) {
				Messages.addGlobalError("Ocorreu um erro ao tentar verificar o Caixa");
				erro.printStackTrace();
			}
		}
		
		public void somarCaixa(){
			
			caixa = null;
			try{
					CaixaDAO caixaDAO = new CaixaDAO();
					caixa = caixaDAO.buscar(1l);
					
					if(valorInformado.doubleValue() < caixa.getCaixa().doubleValue()){
						
						caixa.setCaixa(caixa.getCaixa().subtract(valorInformado)); 
						caixaDAO.editar(caixa);
					}else{
						Messages.addGlobalError("Ocorreu um erro ao tentar verificar o Caixa");
						Messages.addGlobalError("Ocorreu um erro ao tentar sacar o Dinheiro no Caixa");
						Messages.addGlobalError("Tente refazer a transação, 'valor a ser sacado não pode ser superior ao informado pelo sistema!'");
					}
			
			} catch (RuntimeException erro) {
				Messages.addGlobalError("Ocorreu um erro ao tentar verificar o Caixa");
				erro.printStackTrace();
			}
		}
		
		
		
		
}
