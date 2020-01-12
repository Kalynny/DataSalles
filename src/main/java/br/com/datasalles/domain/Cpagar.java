package br.com.datasalles.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Cpagar extends GenericDomain {

	@ManyToOne
	private Fornecedor fornecedor;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date atual;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date vencimento;

	@Column(nullable = false, precision = 6, scale = 2)
	private BigDecimal precoTotal;

	@Column(nullable = false)
	private String tipo;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Date getAtual() {
		return atual;
	}

	public void setAtual(Date atual) {
		this.atual = atual;
	}

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public BigDecimal getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(BigDecimal precoTotal) {
		this.precoTotal = precoTotal;
	}



}