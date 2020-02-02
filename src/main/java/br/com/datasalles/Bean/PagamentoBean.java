package br.com.datasalles.Bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;
import br.com.datasalles.dao.AberturaDAO;
import br.com.datasalles.dao.FuncionarioDAO;
import br.com.datasalles.dao.PagamentoDAO;
import br.com.datasalles.dao.RecebimentoDAO;
import br.com.datasalles.dao.SangriaDAO;
import br.com.datasalles.dao.CompraDAO;
import br.com.datasalles.dao.FornecedorDAO;
import br.com.datasalles.domain.Caixa;
import br.com.datasalles.domain.Funcionario;
import br.com.datasalles.domain.Produto;
import br.com.datasalles.domain.Pagamento;
import br.com.datasalles.domain.TipoPagc;
import br.com.datasalles.domain.Compra;
import br.com.datasalles.domain.Fornecedor;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PagamentoBean implements Serializable{

	private List<Compra> compras;
	private List<Funcionario> funcionarios;
	private List<Fornecedor> fornecedores;
	private Caixa caixa;
	private List<Produto> produtos;
	private List<Caixa> caixas;
	private Fornecedor fornecedor;
	private TipoPagc tipopagc;
	private List<TipoPagc> tipopagcs;
	private Compra compra;
	private Pagamento pagamento;
	private BigDecimal valorAbertura;
	private BigDecimal valorRecebido;
	private BigDecimal valorPagamento;
	private BigDecimal valorSangria;
	private Double totalGeral;


	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public TipoPagc getTipopagc() {
		return tipopagc;
	}

	public List<TipoPagc> getTipopagcs() {
		return tipopagcs;
	}

	public TipoPagc getTipoPagc() {
		return tipopagc;
	}

	public void setTipopagc(TipoPagc tipopagc) {
		this.tipopagc = tipopagc;
	}

	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public List<Caixa> getCaixas() {
		return caixas;
	}

	public void setCaixas(List<Caixa> caixas) {
		this.caixas = caixas;
	}

	public List<TipoPagc> getTipoPagcs() {
		return tipopagcs;
	}

	public void setTipopagcs(List<TipoPagc> tipopagcs) {
		this.tipopagcs = tipopagcs;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public BigDecimal getValorAbertura() {
		return valorAbertura;
	}

	public void setValorAbertura(BigDecimal valorAbertura) {
		this.valorAbertura = valorAbertura;
	}

	public BigDecimal getValorRecebido() {
		return valorRecebido;
	}

	public void setValorRecebido(BigDecimal valorRecebido) {
		this.valorRecebido = valorRecebido;
	}

	public BigDecimal getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(BigDecimal valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

	public BigDecimal getValorSangria() {
		return valorSangria;
	}

	public void setValorSangria(BigDecimal valorSangria) {
		this.valorSangria = valorSangria;
	}

	public Double getTotalGeral() {
		return totalGeral;
	}

	public void setTotalGeral(Double totalGeral) {
		this.totalGeral = totalGeral;
	}

	@PostConstruct
	public void listar(){
		valorAbertura = new BigDecimal("0");
		valorPagamento = new BigDecimal("0");
		try{
			compra = null;
			CompraDAO compraDAO = new CompraDAO();
			compras = compraDAO.listarAvista();

			initValores();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar verificar o Caixa");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento){
		try {
			compra = (Compra) evento.getComponent().getAttributes().get("CompraSelecionado");

			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			fornecedores = fornecedorDAO.listar();

			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar o caixa");
			erro.printStackTrace();
		}	
	}

	public void pagar() {}


	public void salvarPagar() {

		try {

			Pagamento pagamento = new Pagamento();
			pagamento.setFornecedor(compra.getFornecedor());
			pagamento.setFuncionario(compra.getFuncionario());
			pagamento.setHorario(new Date());
			pagamento.setPrecoTotal(compra.getPrecoTotal());
			pagamento.setTipopagc(compra.getTipopagc());
			pagamento.setCompra(compra);

			PagamentoDAO pagamentoDAO = new PagamentoDAO();
			pagamentoDAO.salvar(pagamento);

			compra.setStatus(Compra.STATUS_PAGO);
			CompraDAO compraDAO = new CompraDAO();
			compraDAO.salvar(compra);

			Messages.addGlobalInfo("Pagamento do Caixa salvo com sucesso");

			listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar o Receimento do Caixa");
			erro.printStackTrace();
		}
	}


	public void initValores() {
		valorAbertura = new AberturaDAO().valorAbertura();
		valorRecebido = new RecebimentoDAO().valorRecebido();
		valorPagamento = new PagamentoDAO().valorPagamento();
		valorSangria = new SangriaDAO().valorSangria();
		totalGeral = 0d;
		if(valorAbertura != null) {
			totalGeral += valorAbertura.doubleValue();
		}

		if(valorPagamento != null) {
			totalGeral -= valorPagamento.doubleValue();
		}

		if(valorSangria != null) {
			totalGeral -= valorSangria.doubleValue();
		}

		if(valorRecebido != null) {
			totalGeral += valorRecebido.doubleValue();
		}

	}

}