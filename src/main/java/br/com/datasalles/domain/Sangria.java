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
public class Sangria extends GenericDomain {

	@Column (nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataSangria;

	@Column (nullable = false, precision= 7, scale=2 )
	private BigDecimal valorInformado;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Funcionario funcionario;

	public Date getDataSangria() {
		return dataSangria;
	}

	public void setDataSangria(Date dataSangria) {
		this.dataSangria = dataSangria;
	}

	public BigDecimal getValorInformado() {
		return valorInformado;
	}

	public void setValorInformado(BigDecimal valorInformado) {
		this.valorInformado = valorInformado;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}


}
