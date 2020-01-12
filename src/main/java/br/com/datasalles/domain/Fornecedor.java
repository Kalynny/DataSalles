package br.com.datasalles.domain;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class Fornecedor extends GenericDomain {

	@Column(length = 50, nullable = false)
	private String descricao;

	@Column(length=50, nullable=false )
	private String nfantasia;

	@Column(length = 18, nullable = false, unique = true )
	private String cnpj;

	@Column(length=50, nullable=false )
	private String ie;

	@Column(length=50, nullable=false )
	private String fone;

	@Column(length=50, nullable=false )
	private String email;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNfantasia() {
		return nfantasia;
	}

	public void setNfantasia(String nfantasia) {
		this.nfantasia = nfantasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getIe() {
		return ie;
	}

	public void setIe(String ie) {
		this.ie = ie;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}