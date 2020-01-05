package br.com.datasalles.domain;


import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;




@Entity
public class PesquisaOrcaC extends GenericDomain{
		
	@JoinColumn(nullable = false)
	private Compra compra;
	
	@Column(nullable = false)
	private String parcela;
	
	@Column(nullable = false)
	private BigDecimal valor;
	
	@Column(nullable = false)
	private Date dataDoPagamento;

	@Column(nullable = false)
	private String formaDePagamento;

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public String getParcela() {
		return parcela;
	}

	public void setParcela(String parcela) {
		this.parcela = parcela;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getDataDoPagamento() {
		return dataDoPagamento;
	}

	public void setDataDoPagamento(Date dataDoPagamento) {
		this.dataDoPagamento = dataDoPagamento;
	}

	public String getFormaDePagamento() {
		return formaDePagamento;
	}

	public void setFormaDePagamento(String formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}

			
}