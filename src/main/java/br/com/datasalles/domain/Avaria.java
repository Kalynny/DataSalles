package br.com.datasalles.domain;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
public class Avaria extends GenericDomain {
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date horario;
	
	@Column(nullable = false, precision = 7, scale = 2)
	private BigDecimal precoTotal;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Fornecedor fornecedor;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Funcionario funcionario;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private TipoAvaria tipoavaria;
	
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "avaria")
	private List<ItemAvaria>itensAvaria;
	
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

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public TipoAvaria getTipoavaria() {
		return tipoavaria;
	}

	public void setTipoavaria(TipoAvaria tipoavaria) {
		this.tipoavaria = tipoavaria;
	}

	public List<ItemAvaria> getItensAvaria() {
		return itensAvaria;
	}

	public void setItensAvaria(List<ItemAvaria> itensAvaria) {
		this.itensAvaria = itensAvaria;
	}
	
	
	
	
}