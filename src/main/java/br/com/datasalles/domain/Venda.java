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
public class Venda extends GenericDomain {

	public static long TIPOPAGTO_AVISTA = 1;
	public static int STATUS_ABERTO = 0;
	public static int STATUS_PAGO = 1;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date horario;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date vencimento;

	@Column(nullable = false, precision = 7, scale = 2)
	private BigDecimal precoTotal;

	@ManyToOne
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Funcionario funcionario;

	@ManyToOne
	@JoinColumn(nullable = false)
	private TipoPag tipopag;
	
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "venda")
	private List<ItemVenda> itensVenda;
	
	@Column(nullable = false)
	private int status;

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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public TipoPag getTipopag() {
		return tipopag;
	}

	public void setTipopag(TipoPag tipopag) {
		this.tipopag = tipopag;
	}

	public List<ItemVenda> getItensVenda() {
		return itensVenda;
	}

	public void setItensVenda(List<ItemVenda> itensVenda) {
		this.itensVenda = itensVenda;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
