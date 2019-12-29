package br.com.datasalles.domain;


import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class ItemOrcaC extends GenericDomain {
	@Column(nullable = false)
	private Short quantidade;
	
	@Column(nullable = false, precision = 7, scale = 2)
	private BigDecimal precoParcial;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private OrcamentoC orcamentoc;

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

	public OrcamentoC getOrcamentoc() {
		return orcamentoc;
	}

	public void setOrcamentoc(OrcamentoC orcamentoc) {
		this.orcamentoc = orcamentoc;
	}

		
}


