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
import br.com.datasalles.dao.OrcamentoDAO;
import br.com.datasalles.domain.Orcamento;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PesquisaOrcaBean implements Serializable {

	private List<Orcamento> orcamentos;
	private Date dataInicio = new Date(System.currentTimeMillis());
	private Date dataFim  = new Date(System.currentTimeMillis());
	private BigDecimal valorTotal;

	public List<Orcamento> getOrcamentos() {
		return orcamentos;
	}
	public void setOrcamentos(List<Orcamento> orcamentos) {
		this.orcamentos = orcamentos;
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
		if(orcamentos.size() > 0){
			for(int i=0; i<orcamentos.size(); i++){
				valorTotal = valorTotal.add(orcamentos.get(i).getPrecoTotal());
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
			OrcamentoDAO orcamentoDAO = new OrcamentoDAO();			
			orcamentos = orcamentoDAO.listarPorData(dataInicio, dataFim);
			calculaValorTotal();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Estoque Insuficiente");
			erro.printStackTrace();
		}

	}

	public String importarOrcamento(Long codigo) {
		return "vendas.xhtml?orcamento="+codigo+"&faces-redirect=true";
	}

}
