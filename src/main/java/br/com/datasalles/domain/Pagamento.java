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
public class Pagamento extends GenericDomain{
	
	@ManyToOne
	private Compra compra;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date horario;
	
	@Column(nullable = false, precision = 7, scale = 2)
	private BigDecimal precoTotal;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private TipoPagc tipopagc;
			
	@ManyToOne
	private Fornecedor fornecedor;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Funcionario funcionario;	
	
	

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public BigDecimal getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(BigDecimal precoTotal) {
		this.precoTotal = precoTotal;
	}

	public TipoPagc getTipopagc() {
		return tipopagc;
	}

	public void setTipopagc(TipoPagc tipopagc) {
		this.tipopagc = tipopagc;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}

	
	