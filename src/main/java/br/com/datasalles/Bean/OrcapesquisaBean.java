package br.com.datasalles.Bean;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.omnifaces.util.Messages;
import br.com.datasalles.dao.OrcapesquisaDAO;
import br.com.datasalles.domain.Orcapesquisa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class OrcapesquisaBean implements Serializable{
	private List<Orcapesquisa> Orcapesquisas;
	private Date dataInicio = new Date(System.currentTimeMillis());
	private Date dataFim  = new Date(System.currentTimeMillis());
	private BigDecimal valorTotal;
	
		
	public List<Orcapesquisa> getOrcapesquisas() {
		return Orcapesquisas;
	}
	public void setOrcapesquisas(List<Orcapesquisa> orcapesquisas) {
		Orcapesquisas = orcapesquisas;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	
	public void calculaValorTotal(){
		valorTotal = new BigDecimal("0");
		if(Orcapesquisas.size() > 0){
			for(int i=0; i<Orcapesquisas.size(); i++){
				valorTotal = valorTotal.add(Orcapesquisas.get(i).getValor());
			}
		}		
	}
	
	
	
	@SuppressWarnings("unchecked")
	@PostConstruct  
	public void listar(){
		try{
			valorTotal = new BigDecimal("0");
			OrcapesquisaDAO orcapesquisaDAO = new OrcapesquisaDAO();
			Calendar dataAtual = Calendar.getInstance();
			dataAtual.add(Calendar.DAY_OF_MONTH, -1);
			
			Orcapesquisas= orcapesquisaDAO.listarPorData(dataAtual.getTime(), dataFim);
			calculaValorTotal();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as vendas");
			erro.printStackTrace();
		}
	
	}
	
	@SuppressWarnings("unchecked")
	public void listarPorData(){

		valorTotal = new BigDecimal("0");
		try{
	
			OrcapesquisaDAO orcapesquisaDAO = new OrcapesquisaDAO();
		
			Orcapesquisas = null;
			Orcapesquisas = orcapesquisaDAO.listarPorData(dataInicio, dataFim); 
			calculaValorTotal();
			 
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os estados");
			erro.printStackTrace();
		}
	
	}
	
	
}
