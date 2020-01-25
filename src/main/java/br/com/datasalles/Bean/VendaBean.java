package br.com.datasalles.Bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import br.com.datasalles.dao.ClienteDAO;
import br.com.datasalles.dao.FuncionarioDAO;
import br.com.datasalles.dao.OrcamentoDAO;
import br.com.datasalles.dao.ProdutoDAO;
import br.com.datasalles.dao.TipoPagDAO;
import br.com.datasalles.dao.VendaDAO;
import br.com.datasalles.domain.Cliente;
import br.com.datasalles.domain.Creceber;
import br.com.datasalles.domain.Funcionario;
import br.com.datasalles.domain.ItemOrca;
import br.com.datasalles.domain.ItemVenda;
import br.com.datasalles.domain.Orcamento;
import br.com.datasalles.domain.Produto;
import br.com.datasalles.domain.Recebimento;
import br.com.datasalles.domain.TipoPag;
import br.com.datasalles.domain.Venda;
import br.com.datasalles.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class VendaBean implements Serializable {
	private Venda venda;
	private Recebimento recebimento;
	private Creceber receber;
	private List<Produto> produtos;
	private List<ItemVenda> itensVenda;
	private List<Cliente> clientes;
	private List<Funcionario> funcionarios;
	private List<TipoPag> tipopags;
	private List<Venda> vendas;

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Recebimento getRecebimento() {
		return recebimento;
	}

	public void setRecebimento(Recebimento recebimento) {
		this.recebimento = recebimento;
	}

	public Creceber getReceber() {
		return receber;
	}

	public void setReceber(Creceber receber) {
		this.receber = receber;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<ItemVenda> getItensVenda() {
		return itensVenda;
	}

	public void setItensVenda(List<ItemVenda> itensVenda) {
		this.itensVenda = itensVenda;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<TipoPag> getTipopags() {
		return tipopags;
	}

	public void setTipopags(List<TipoPag> tipopags) {
		this.tipopags = tipopags;
	}

	public List<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

	public void importarOrcamento(Orcamento orcamento) {

		try {

			for(ItemOrca rs : orcamento.getItensOrca()) {

				ItemVenda item = new ItemVenda();
				item.setPrecoParcial(rs.getPrecoParcial());
				item.setProduto(rs.getProduto());
				item.setQuantidade(rs.getQuantidade());
				item.setVenda(venda);

				itensVenda.add(item);
			}

			calcular();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar a importação");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			venda = new Venda();
			venda.setPrecoTotal(new BigDecimal("0.00"));

			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar("descricao");

			itensVenda = new ArrayList<>();
			tipopags = new ArrayList<>();

			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			String orcamentoCodigo = request.getParameter("orcamento");
			if(orcamentoCodigo!=null) {
				try {

					OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
					Long cod = Long.parseLong(orcamentoCodigo);

					importarOrcamento(orcamentoDAO.buscar(cod));

				} catch (Exception e) {
					e.printStackTrace();
				}

			}


		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar carregar a tela de vendas");
			erro.printStackTrace();
		}
	}

	public void listar(){
		VendaDAO dao = new VendaDAO();
		vendas = dao.listar("codigo");
	}

	public void adicionar(ActionEvent evento) {
		Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
		if (produto.getQuantidade()>0) {

			ItemVenda item = null;
			for (ItemVenda rs : itensVenda) {
				if (rs.getProduto().equals(produto)) {
					item = rs;
				}
			}

			if (item == null) {
				ItemVenda itemVenda = new ItemVenda();
				itemVenda.setPrecoParcial(produto.getPreco());
				itemVenda.setProduto(produto);
				itemVenda.setQuantidade(new Short("1"));

				itensVenda.add(itemVenda);
			} else {
				ItemVenda itemVenda = item;
				itemVenda.setQuantidade(new Short(itemVenda.getQuantidade() + 1 + ""));
				itemVenda.setPrecoParcial(produto.getPreco().multiply(new BigDecimal(itemVenda.getQuantidade())));
			}

			calcular();

		}
	}

	public void subtrair(ActionEvent evento) {
		try { 
			Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			if (produto.getQuantidade()>0) {

				ItemVenda item = null;
				for (ItemVenda rs : itensVenda) {
					if (rs.getProduto().equals(produto)) {
						item = rs;
					}
				}

				if (item == null) {
					ItemVenda itemVenda = new ItemVenda();
					itemVenda.setPrecoParcial(produto.getPreco());
					itemVenda.setProduto(produto);
					itemVenda.setQuantidade(new Short("0"));
					
					itensVenda.add(itemVenda);
 				} else {
					ItemVenda itemVenda = item;
					if (itemVenda.getQuantidade() != null && itemVenda.getQuantidade()>0) {
						itemVenda.setQuantidade(new Short(itemVenda.getQuantidade() - 1 + ""));
						Messages.addGlobalError("Digite uma Quatidade Maior que Zero");
					}
					itemVenda.setPrecoParcial(produto.getPreco().multiply(new BigDecimal(itemVenda.getQuantidade())));
				}

				calcular();
				Messages.addGlobalError("Digite uma Quatidade Maior que Zero");
								
			}

		}catch (RuntimeException erro) {
			Messages.addGlobalError("Digite uma Quatidade Maior que Zero");
			erro.printStackTrace();
		}
		
	}

	public void remover(ActionEvent evento) {
		ItemVenda itemVenda = (ItemVenda) evento.getComponent().getAttributes().get("itemSelecionado");

		int achou = -1;
		for (int posicao = 0; posicao < itensVenda.size(); posicao++) {
			if (itensVenda.get(posicao).getProduto().equals(itemVenda.getProduto())) {
				achou = posicao;
			}
		}

		if (achou > -1) {
			itensVenda.remove(achou);
		}

		calcular();
	}

	public void calcular() {
		venda.setPrecoTotal(new BigDecimal("0.00"));

		for (int posicao = 0; posicao < itensVenda.size(); posicao++) {
			ItemVenda itemVenda = itensVenda.get(posicao);
			venda.setPrecoTotal(venda.getPrecoTotal().add(itemVenda.getPrecoParcial()));
		}
	}

	public void finalizar() {
		try {
			venda.setHorario(new Date());
			venda.setCliente(null);
			venda.setTipopag(null);
			venda.setFuncionario(null);

			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listarOrdenado();

			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listarOrdenado();

			TipoPagDAO tipopagDAO = new TipoPagDAO();
			tipopags = tipopagDAO.listar();


		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar finalizar a venda");
			erro.printStackTrace();
		}
	}


	public void salvar(ActionEvent event) {
		try { 

			if(venda.getPrecoTotal().signum() == 0){
				Messages.addGlobalError("Informe pelo menos um item para a venda");
				return;
			}

			if(venda.getTipopag().getCodigo() == (1)) {
				VendaDAO vendaDAO = new VendaDAO();			
				vendaDAO.salvar(venda, itensVenda, recebimento);

				venda = new Venda();
				venda.setPrecoTotal(new BigDecimal("0.00"));

				ProdutoDAO produtoDAO = new ProdutoDAO();
				produtos = produtoDAO.listar("descricao");

				@SuppressWarnings("unused")
				TipoPagDAO tipopagDAO = new TipoPagDAO();
				tipopags = new ArrayList<>();

				itensVenda = new ArrayList<>();

				Messages.addGlobalInfo("Venda realizada com sucesso");
			}else{

				VendaDAO vendaDAO = new VendaDAO();
				vendaDAO.salvarBoleto(venda, itensVenda);
				//venda = new Venda();
				venda.setPrecoTotal(new BigDecimal("0.00"));

				ProdutoDAO produtoDAO = new ProdutoDAO();
				produtos = produtoDAO.listar("descricao");

				@SuppressWarnings("unused")
				TipoPagDAO tipopagDAO = new TipoPagDAO();
				tipopags = new ArrayList<>();

				itensVenda = new ArrayList<>();	
				Messages.addGlobalInfo("Venda realizada com sucesso");
			}

		}catch (RuntimeException erro) {
			Messages.addGlobalError("Quantidade do Estoque menor que o solicitado na Venda");
			erro.printStackTrace();
		}
	}

	public void atualizarPrecoParcial(){
		for(ItemVenda itemVenda : this.itensVenda){
			itemVenda.setPrecoParcial(itemVenda.getProduto().getPreco().multiply(new BigDecimal(itemVenda.getQuantidade())));
		}
		this.calcular();
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


	public void pagamentoBoleto()  {

		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		org.hibernate.Transaction transacao = null;

		try {

			transacao= sessao.beginTransaction();

			//String dataFormatada = sevico.formatData("yyyy/MM/DD",venda.getVencimento());	
			//String padraoData = " %Y-%m-%d ";
			//String dataFormatada = new SimpleDateFormat("yyyy-MM-dd").format(venda.getVencimento());


			String sql = "insert into creceber VALUES (null,"+venda.getVencimento()+","+venda.getPrecoTotal()+","+venda.getVencimento()+","+venda.getCliente().getCodigo()+","+venda.getTipopag().getCodigo()+");";

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

			Messages.addGlobalInfo("Venda realizada com sucesso!!");
		}

	}	

}