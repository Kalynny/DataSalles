package br.com.datasalles.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Avaria extends GenericDomain {
	@Column(length = 80, nullable = false)
	private String descricao;
	
	@Column(nullable = false)
	private Short quantidade;
			
	@ManyToOne
	@JoinColumn(nullable = false)
	private Fornecedor fornecedor;
	
	@Column(nullable = false)
	private Character tipo;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Short getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Short quantidade) {
		this.quantidade = quantidade;
	}

		public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public Character getTipo() {
		return tipo;
	}
	
	public void setTipo(Character tipo) {
		this.tipo = tipo;
	}
}
