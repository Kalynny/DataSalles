package br.com.datasalles.Bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.component.datatable.DataTable;
import br.com.datasalles.dao.CompraDAO;
import br.com.datasalles.dao.FornecedorDAO;
import br.com.datasalles.dao.FuncionarioDAO;
import br.com.datasalles.dao.OrcamentoCDAO;
import br.com.datasalles.dao.ProdutoDAO;
import br.com.datasalles.dao.TipoPagcDAO;
import br.com.datasalles.domain.Compra;
import br.com.datasalles.domain.Cpagar;
import br.com.datasalles.domain.Fornecedor;
import br.com.datasalles.domain.Funcionario;
import br.com.datasalles.domain.ItemCompra;
import br.com.datasalles.domain.ItemOrcaC;
import br.com.datasalles.domain.OrcamentoC;
import br.com.datasalles.domain.Pagamento;
import br.com.datasalles.domain.Produto;
import br.com.datasalles.domain.TipoPagc;
import br.com.datasalles.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CompraBean implements Serializable {
	private Compra compra;
	private Cpagar cpagar;
	private List<Produto> produtos;
	private List<ItemCompra> itensCompra;
	private List<Fornecedor> fornecedores;
	private List<Funcionario> funcionarios;
	private List<TipoPagc> tipopagcs;
	private List<Compra> compras;
	private Pagamento pagamento;
	private String produtoBusca;

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<ItemCompra> getItensCompra() {
		return itensCompra;
	}

	public void setItensCompra(List<ItemCompra> itensCompra) {
		this.itensCompra = itensCompra;
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<TipoPagc> getTipopagcs() {
		return tipopagcs;
	}

	public void setTipopagcs(List<TipoPagc> tipopagcs) {
		this.tipopagcs = tipopagcs;
	}

	public Cpagar getCpagar() {
		return cpagar;
	}

	public void setCpagar(Cpagar cpagar) {
		this.cpagar = cpagar;
	}
	
	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
		
	public String getProdutoBusca() {
		return produtoBusca;
	}

	public void setProdutoBusca(String produtoBusca) {
		this.produtoBusca = produtoBusca;
	}

	public void importarOrcamentoC(OrcamentoC orcamentoc) {

		try {

			for(ItemOrcaC rs : orcamentoc.getItensOrcaC()) {

				ItemCompra item = new ItemCompra();
				item.setPrecoParcial(rs.getPrecoParcial());
				item.setProduto(rs.getProduto());
				item.setQuantidade(rs.getQuantidade());
				item.setCompra(compra);

				itensCompra.add(item);
			}

			calcular();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar a importação");
			erro.printStackTrace();
		}
	}


	@PostConstruct
	public void novo() {
		try {
			compra = new Compra();
			compra.setPrecoTotal(new BigDecimal("0.00"));

			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar("descricao");

			itensCompra = new ArrayList<>();
			tipopagcs = new ArrayList<>();

			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			String orcamentocCodigo = request.getParameter("orcamentoc");
			if(orcamentocCodigo!=null) {
				try {

					OrcamentoCDAO orcamentocDAO = new OrcamentoCDAO();
					Long cod = Long.parseLong(orcamentocCodigo);

					importarOrcamentoC(orcamentocDAO.buscar(cod));

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar carregar a tela de vendas");
			erro.printStackTrace();
		}
	}

	public void adicionar(ActionEvent evento) {
		Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

		ItemCompra item = null;
		for (ItemCompra rs : itensCompra) {
			if (rs.getProduto().equals(produto)) {
				item = rs;
			}
		}

		if (item == null) {
			ItemCompra itemCompra = new ItemCompra();
			itemCompra.setPrecoParcial(produto.getPreco());
			itemCompra.setProduto(produto);
			itemCompra.setQuantidade(new Short("1"));

			itensCompra.add(itemCompra);
		} else {
			ItemCompra itemCompra = item;
			itemCompra.setQuantidade(new Short(itemCompra.getQuantidade() + 1 + ""));
			itemCompra.setPrecoParcial(produto.getPreco().multiply(new BigDecimal(itemCompra.getQuantidade())));
		}

		calcular();
	}

	public void subtrair(ActionEvent evento) {
		try {
			Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			if (produto.getQuantidade()>0) {

				ItemCompra item = null;
				for (ItemCompra rs : itensCompra) {
					if (rs.getProduto().equals(produto)) {
						item = rs;
					}
				}

				if (item == null) {
					ItemCompra itemCompra = new ItemCompra();
					itemCompra.setPrecoParcial(produto.getPreco());
					itemCompra.setProduto(produto);
					itemCompra.setQuantidade(new Short("0"));

					itensCompra.add(itemCompra);
				} else {
					ItemCompra itemCompra = item;
					if (itemCompra.getQuantidade() != null && itemCompra.getQuantidade()>0) {
						itemCompra.setQuantidade(new Short(itemCompra.getQuantidade() - 1 + ""));
					}
					itemCompra.setPrecoParcial(produto.getPreco().multiply(new BigDecimal(itemCompra.getQuantidade())));
				}

				calcular();
			}

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Digite uma Quatidade Maior que Zero");
			erro.printStackTrace();
		}

	}





	public void remover(ActionEvent evento) {
		ItemCompra itemCompra = (ItemCompra) evento.getComponent().getAttributes().get("itemSelecionado");

		int achou = -1;
		for (int posicao = 0; posicao < itensCompra.size(); posicao++) {
			if (itensCompra.get(posicao).getProduto().equals(itemCompra.getProduto())) {
				achou = posicao;
			}
		}

		if (achou > -1) {
			itensCompra.remove(achou);
		}

		calcular();
	}

	public void calcular() {
		compra.setPrecoTotal(new BigDecimal("0.00"));

		for (int posicao = 0; posicao < itensCompra.size(); posicao++) {
			ItemCompra itemcompra = itensCompra.get(posicao);
			compra.setPrecoTotal(compra.getPrecoTotal().add(itemcompra.getPrecoParcial()));
		}
	}

	public void finalizar() {
		try {
			compra.setAtual(new Date());
			compra.setFornecedor(null);
			compra.setFuncionario(null);
			compra.setTipopagc(null);

			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listarOrdenado();

			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			fornecedores = fornecedorDAO.listar();

			TipoPagcDAO tipopagcDAO = new TipoPagcDAO();
			tipopagcs = tipopagcDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar finalizar a compra");
			erro.printStackTrace();
		}
	}

	public void listar(){
		CompraDAO dao = new CompraDAO();
		compras = dao.listar("codigo");
	}


	public void atualizarPrecoParcial(){
		for(ItemCompra itemcompra : this.itensCompra){
			itemcompra.setPrecoParcial(itemcompra.getProduto().getPreco().multiply(new BigDecimal(itemcompra.getQuantidade())));
		}
		this.calcular();
	}

	
	public void salvar(ActionEvent event) {
		try {

			if(compra.getPrecoTotal().signum() == 0){
				Messages.addGlobalError("Informe pelo menos um item para a compra");
				return;
			}
			
			if(compra.getTipopagc().getCodigo() == (1)) {

				CompraDAO compraDAO = new CompraDAO();			
				compraDAO.salvar(compra,itensCompra, pagamento);

				compra = new Compra();
				compra.setPrecoTotal(new BigDecimal("0.00"));

				ProdutoDAO produtoDAO = new ProdutoDAO();
				produtos = produtoDAO.listar("descricao");

				@SuppressWarnings("unused")
				TipoPagcDAO tipopagcDAO = new TipoPagcDAO();
				tipopagcs = new ArrayList<>();

				itensCompra = new ArrayList<>();

				Messages.addGlobalInfo("Compra realizada com sucesso");
				return;
			}else{

				CompraDAO compraDAO = new CompraDAO();
				compraDAO.salvarBoleto(compra,itensCompra);
				compra = new Compra();
				compra.setPrecoTotal(new BigDecimal("0.00"));

				ProdutoDAO produtoDAO = new ProdutoDAO();
				produtos = produtoDAO.listar("descricao");

				@SuppressWarnings("unused")
				TipoPagcDAO tipopagcDAO = new TipoPagcDAO();
				tipopagcs = new ArrayList<>();

				itensCompra = new ArrayList<>();

			}

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a compra");
			erro.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public void imprimir(){
		try {
			DataTable tabela = (DataTable) Faces.getViewRoot().findComponent ("formListagem:tabela");
			Map<String, Object> filtros = tabela.getFilters();

			String estNome = (String) filtros.get("nome");
			String estSigla = (String) filtros.get("sigla");

			String caminho = Faces.getRealPath("/reports/estado.jasper");
			String banner = Faces.getRealPath("/resources/img/Logo1.png");

			Map<String, Object> parametros = new HashMap<>();

			parametros.put("BANNER",banner);

			if (estNome == null) {
				parametros.put("NOME_ESTADO", "%%");
			} else {
				parametros.put("NOME_ESTADO", "%" + estNome + "%");
			}
			if (estSigla == null) {
				parametros.put("SIGLA_ESTADO", "%%");
			} else {
				parametros.put("SIGLA_ESTADO", "%" + estSigla + "%");
			}

			Connection conexao = HibernateUtil.getConexao();

			JasperPrint relatorio = JasperFillManager.fillReport(caminho,parametros, conexao);

			JasperViewer view = new JasperViewer(relatorio, false);
			view.show();

		} catch (JRException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar o relatório");
			erro.printStackTrace();
		}
	}

	public void pagamentoBoleto() {

		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		org.hibernate.Transaction transacao = null;

		try {

			transacao= sessao.beginTransaction();

			//String dataFormatada = sevico.formatData("yyyy/MM/DD",compra.getVencimento());	
			String padraoData = "%Y-%m-%d";
			String dataFormatada = new SimpleDateFormat("yyyy/MM/dd").format(compra.getVencimento());


			String sql = "insert into cpagar VALUES (null, sysdate(),"+compra.getPrecoTotal()+",DATE_FORMAT("+dataFormatada+","+padraoData+"),"+compra.getFornecedor().getCodigo()+","+compra.getTipopagc().getCodigo()+");";

			SQLQuery query = sessao.createSQLQuery(sql);

			int result = query.executeUpdate();

			transacao.commit();
			System.out.println(result);

		} catch (HibernateException e) {
			if (transacao != null)
				transacao.rollback();
			e.printStackTrace();
		} finally {
			sessao.close();

			Messages.addGlobalInfo("Compra realizada com sucesso!!");
		}

	}

	public void editar(ActionEvent evento){
		compra = (Compra) evento.getComponent().getAttributes().get("compraSelecionado");
	}

	@SuppressWarnings("deprecation")
	public void impestcom(){
		try {

			String caminho = Faces.getRealPath("/reports/compra.jasper");
			String banner = Faces.getRealPath("/resources/img/Logo.png");

			Map<String, Object> parametros = new HashMap<>();

			parametros.put("BANNER",banner);

			Connection conexao = HibernateUtil.getConexao();
			JasperPrint relatorio = JasperFillManager.fillReport(caminho,parametros, conexao);
			JasperViewer view = new JasperViewer(relatorio, false);
			view.show();

		} catch (JRException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar o relatório");
			erro.printStackTrace();
		}
	}
	
	public void pesquisaProduto(){
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtos = produtoDAO.buscar(produtoBusca);
	}




}
