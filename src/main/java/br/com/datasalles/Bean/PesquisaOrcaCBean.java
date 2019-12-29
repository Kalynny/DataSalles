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
import br.com.datasalles.dao.OrcamentoCDAO;
import br.com.datasalles.domain.OrcamentoC;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PesquisaOrcaCBean implements Serializable {
	
	private List<OrcamentoC> orcamentosc;
	private Date dataInicio = new Date(System.currentTimeMillis());
	private Date dataFim  = new Date(System.currentTimeMillis());
	private BigDecimal valorTotal;
		
	
	public List<OrcamentoC> getOrcamentosc() {
		return orcamentosc;
	}
	public void setOrcamentosc(List<OrcamentoC> orcamentosc) {
		this.orcamentosc = orcamentosc;
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
		if(orcamentosc.size() > 0){
			for(int i=0; i<orcamentosc.size(); i++){
				valorTotal = valorTotal.add(orcamentosc.get(i).getPrecoTotal());
			}
		}		
	}
	
	@PostConstruct
	public void init() {
		startDatas();
		listar();
	}
	
	public void startDatas(){
		Calendar c1 = Calendar.getInstance();
		c1.set(Calendar.DAY_OF_MONTH, 1);
		c1.set(Calendar.HOUR, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		
		dataInicio = c1.getTime();
		dataFim = new Date();
	}
	
	
	@PostConstruct  
	public void listar(){
		try{
			
			if(dataInicio==null || dataFim==null){
				startDatas();
			}
			
			valorTotal = new BigDecimal("0");
			OrcamentoCDAO orcamentocDAO = new OrcamentoCDAO();			
			orcamentosc = orcamentocDAO.listarPorData(dataInicio, dataFim);
			calculaValorTotal();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Estoque Insuficiente");
			erro.printStackTrace();
		}
	
	}
	
	public String importarOrcamentoC(Long codigo) {
		return "compras.xhtml?orcamentoc="+codigo+"&faces-redirect=true";
	}
	
}
