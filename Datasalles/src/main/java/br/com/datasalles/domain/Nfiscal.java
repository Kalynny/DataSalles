package br.com.datasalles.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Nfiscal extends GenericDomain {
	
	@Column(length = 50, nullable = false)
	private String nfiscal;
	
	@Column(nullable = false, precision = 7, scale = 2)
	private BigDecimal totalNota;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataEntrada;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	
	@ManyToOne
	private Fornecedor fornecedor;
	
	@Column(nullable = false)
	private Short quantidade;
	
	@Column(nullable = false, precision = 7, scale = 2)
	private BigDecimal precoParcial;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Produto produto;

	public String getNfiscal() {
		return nfiscal;
	}

	public void setNfiscal(String nfiscal) {
		this.nfiscal = nfiscal;
	}

	public BigDecimal getTotalNota() {
		return totalNota;
	}

	public void setTotalNota(BigDecimal totalNota) {
		this.totalNota = totalNota;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Short getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Short quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPrecoParcial() {
		return precoParcial;
	}

	public void setPrecoParcial(BigDecimal precoParcial) {
		this.precoParcial = precoParcial;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
		
}