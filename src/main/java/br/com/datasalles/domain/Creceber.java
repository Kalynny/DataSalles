package br.com.datasalles.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Creceber extends GenericDomain {
	
	@ManyToOne
	private Cliente cliente;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date horario;
	
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
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