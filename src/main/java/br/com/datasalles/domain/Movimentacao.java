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
public class Movimentacao extends GenericDomain {
	
	@Column (nullable = false)
	@Temporal(TemporalType.DATE)
	private Date horario;
	
	@Column (nullable = false, precision= 7, scale=2 )
	private BigDecimal valor;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Abertura abertura;

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Abertura getAbertura() {
		return abertura;
	}

	public void setAbertura(Abertura abertura) {
		this.abertura = abertura;
	}
	
	

}
