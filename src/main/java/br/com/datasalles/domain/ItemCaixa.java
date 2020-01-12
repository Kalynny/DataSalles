package br.com.datasalles.domain;


import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class ItemCaixa extends GenericDomain {

	@ManyToOne
	@JoinColumn(nullable = false)
	private Caixa caixa;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Recebimento recebimento;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Venda venda;

	@Column(nullable = false, precision = 7, scale = 2)
	private BigDecimal totalRecebimento;

	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}

	public Recebimento getRecebimento() {
		return recebimento;
	}

	public void setRecebimento(Recebimento recebimento) {
		this.recebimento = recebimento;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public BigDecimal getTotalRecebimento() {
		return totalRecebimento;
	}

	public void setTotalRecebimento(BigDecimal totalRecebimento) {
		this.totalRecebimento = totalRecebimento;
	}


}


