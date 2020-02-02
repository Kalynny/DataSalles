package br.com.datasalles.Bean;

//import java.awt.Color;
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
import br.com.datasalles.dao.ClienteDAO;
import br.com.datasalles.dao.FuncionarioDAO;
import br.com.datasalles.dao.PagamentoDAO;
import br.com.datasalles.dao.RecebimentoDAO;
import br.com.datasalles.dao.SangriaDAO;
import br.com.datasalles.dao.VendaDAO;
import br.com.datasalles.domain.Caixa;
import br.com.datasalles.domain.Cliente;
import br.com.datasalles.domain.Funcionario;
import br.com.datasalles.domain.Produto;
import br.com.datasalles.domain.Recebimento;
import br.com.datasalles.domain.TipoPag;
import br.com.datasalles.domain.Venda;


@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class RecebimentoBean implements Serializable{

	private List<Venda> vendas;
	private List<Funcionario> funcionarios;
	private List<Cliente> clientes;
	private Caixa caixa;
	private List<Produto> produtos;
	private List<Caixa> caixas;
	private Cliente cliente;
	private TipoPag tipopag;
	private List<TipoPag> tipopags;
	private Venda venda;
	private Recebimento recebimento;
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

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public TipoPag getTipopag() {
		return tipopag;
	}

	public void setTipopag(TipoPag tipopag) {
		this.tipopag = tipopag;
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


	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}

	public List<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public List<Caixa> getCaixas() {
		return caixas;
	}

	public void setCaixas(List<Caixa> caixas) {
		this.caixas = caixas;
	}

	public List<TipoPag> getTipopags() {
		return tipopags;
	}

	public void setTipopags(List<TipoPag> tipopags) {
		this.tipopags = tipopags;
	}

	public Recebimento getRecebimento() {
		return recebimento;
	}

	public void setRecebimento(Recebimento recebimento) {
		this.recebimento = recebimento;
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
		valorRecebido = new BigDecimal("0");
		valorSangria = new BigDecimal("0");
		try{
			venda = null;
			VendaDAO vendaDAO = new VendaDAO();
			vendas = vendaDAO.listarAvista();

			initValores();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar verificar o Caixa");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento){
		try {
			venda = (Venda) evento.getComponent().getAttributes().get("vendaSelecionado");

			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listar();

			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar o caixa");
			erro.printStackTrace();
		}	
	}

	public void receber() {}

	public void salvarReceber() {
		try {
			
			Recebimento recebimento = new Recebimento();
			recebimento.setCliente(venda.getCliente());
			recebimento.setFuncionario(venda.getFuncionario());
			recebimento.setHorario(new Date());
			recebimento.setPrecoTotal(venda.getPrecoTotal());
			recebimento.setTipopag(venda.getTipopag());
			recebimento.setVenda(venda);
			
			RecebimentoDAO recebimentoDAO = new RecebimentoDAO();
			recebimentoDAO.salvar(recebimento);
			
			venda.setStatus(Venda.STATUS_PAGO);
			VendaDAO vendaDAO = new VendaDAO();
			vendaDAO.salvar(venda);
									
			Messages.addGlobalInfo("Recebimento do Caixa salvo com sucesso");
			
			listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar o Recebimento do Caixa");
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