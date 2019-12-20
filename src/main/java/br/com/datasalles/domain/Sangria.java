package br.com.datasalles.domain;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Sangria extends GenericDomain{

	@Column(nullable = false)
	private BigDecimal sangria;

	public BigDecimal getSangria() {
		return sangria;
	}

	public void setSangria(BigDecimal sangria) {
		this.sangria = sangria;
	}

}
