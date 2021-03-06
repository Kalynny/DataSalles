package br.com.datasalles.Bean;


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import br.com.datasalles.dao.FornecedorDAO;
import br.com.datasalles.dao.FuncionarioDAO;
import br.com.datasalles.dao.ProdutoDAO;
import br.com.datasalles.dao.OrcamentoCDAO;
import br.com.datasalles.domain.Fornecedor;
import br.com.datasalles.domain.Funcionario;
import br.com.datasalles.domain.ItemOrcaC;
import br.com.datasalles.domain.Produto;
import br.com.datasalles.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import br.com.datasalles.domain.OrcamentoC;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class orcamentocBean implements Serializable {

	private OrcamentoC orcamentoc;
	private List<Produto> produtos;
	private List<ItemOrcaC> itensOrcaC;
	private List<Fornecedor> fornecedores;
	private List<Funcionario> funcionarios;
	private List<OrcamentoC> orcamentocs;
	private Produto produto;
	private Boolean exibir;

	public OrcamentoC getOrcamentoC() {
		return orcamentoc;
	}

	public void setOrcamentoC(OrcamentoC orcamentoc) {
		this.orcamentoc = orcamentoc;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<ItemOrcaC> getItensOrcaC() {
		return itensOrcaC;
	}

	public void setItensOrcaC(List<ItemOrcaC> itensOrcaC) {
		this.itensOrcaC = itensOrcaC;
	}

	public OrcamentoC getOrcamentoc() {
		return orcamentoc;
	}

	public void setOrcamentoc(OrcamentoC orcamentoc) {
		this.orcamentoc = orcamentoc;
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public List<OrcamentoC> getOrcamentocs() {
		return orcamentocs;
	}

	public void setOrcamentocs(List<OrcamentoC> orcamentocs) {
		this.orcamentocs = orcamentocs;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<OrcamentoC> getOrcamentoCs() {
		return orcamentocs;
	}

	public void setOrcamentoCs(List<OrcamentoC> orcamentocs) {
		this.orcamentocs = orcamentocs;
	}
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public Boolean getExibir() {
		return exibir;
	}

	public void setExibir(Boolean exibir) {
		this.exibir = exibir;
	}

	@PostConstruct
	public void novo() {
		try {
			orcamentoc = new OrcamentoC();
			orcamentoc.setPrecoTotal(new BigDecimal("0.00"));

			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar("descricao");

			itensOrcaC = new ArrayList<>();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar carregar a tela de Orcamentos de Compras");
			erro.printStackTrace();
		}
	}

	public void adicionar(ActionEvent evento) {
		Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

		ItemOrcaC item = null;
		for (ItemOrcaC rs : itensOrcaC) {
			if (rs.getProduto().equals(produto)) {
				item = rs;
			}
		}

		if (item == null) {
			ItemOrcaC itemOrcaC = new ItemOrcaC();
			itemOrcaC.setPrecoParcial(produto.getPreco());
			itemOrcaC.setProduto(produto);
			itemOrcaC.setQuantidade(new Short("1"));

			itensOrcaC.add(itemOrcaC);
		} else {
			ItemOrcaC itemOrcaC = item;
			itemOrcaC.setQuantidade(new Short(itemOrcaC.getQuantidade() + 1 + ""));
			itemOrcaC.setPrecoParcial(produto.getPreco().multiply(new BigDecimal(itemOrcaC.getQuantidade())));
		}

		calcular();
	}

	public void subtrair(ActionEvent evento) {
		Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
		if (produto.getQuantidade()>0) {

			ItemOrcaC item = null;
			for (ItemOrcaC rs : itensOrcaC) {
				if (rs.getProduto().equals(produto)) {
					item = rs;
				}
			}

			if (item == null) {
				ItemOrcaC itemOrcaC = new ItemOrcaC();
				itemOrcaC.setPrecoParcial(produto.getPreco());
				itemOrcaC.setProduto(produto);
				itemOrcaC.setQuantidade(new Short("1"));

				itensOrcaC.add(itemOrcaC);
			} else {
				ItemOrcaC itemOrcaC = item;
				if (itemOrcaC.getQuantidade() != null && itemOrcaC.getQuantidade()>0) {
					itemOrcaC.setQuantidade(new Short(itemOrcaC.getQuantidade() - 1 + ""));
				}
				itemOrcaC.setPrecoParcial(produto.getPreco().multiply(new BigDecimal(itemOrcaC.getQuantidade())));
			}

			calcular();
		}
	}

	public void remover(ActionEvent evento) {
		ItemOrcaC itemOrcaC = (ItemOrcaC) evento.getComponent().getAttributes().get("itemSelecionado");

		int achou = -1;
		for (int posicao = 0; posicao < itensOrcaC.size(); posicao++) {
			if (itensOrcaC.get(posicao).getProduto().equals(itemOrcaC.getProduto())) {
				achou = posicao;
			}
		}

		if (achou > -1) {
			itensOrcaC.remove(achou);
		}

		calcular();
	}

	public void calcular() {
		orcamentoc.setPrecoTotal(new BigDecimal("0.00"));

		for (int posicao = 0; posicao < itensOrcaC.size(); posicao++) {
			ItemOrcaC itemOrcaC = itensOrcaC.get(posicao);
			orcamentoc.setPrecoTotal(orcamentoc.getPrecoTotal().add(itemOrcaC.getPrecoParcial()));
		}
	}

	public void finalizar() {
		try {
			orcamentoc.setHorario(new Date());
			orcamentoc.setFornecedor(null);
			orcamentoc.setFuncionario(null);

			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listarOrdenado();

			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			fornecedores = fornecedorDAO.listarOrdenado();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar finalizar a Orcamento de Comras");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			if(orcamentoc.getPrecoTotal().signum() == 0){
				Messages.addGlobalError("Informe pelo menos um item para o Pedido de Compras");
				return;
			}

			OrcamentoCDAO orcamentocDAO = new OrcamentoCDAO();
			orcamentocDAO.salvar(orcamentoc, itensOrcaC);

			orcamentoc = new OrcamentoC();
			orcamentoc.setPrecoTotal(new BigDecimal("0.00"));

			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar("descricao");

			itensOrcaC = new ArrayList<>();
			
			imprimirRelatorioCompra();

			Messages.addGlobalInfo("Pedido de Compras realizada com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o Pedido de Compras");
			erro.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void imprimirRelatorioCompra(){
		try {
			String codOrcamentoC = buscaCodigoOrcamentoC();
			int codOrcamentoCInt = Integer.parseInt(codOrcamentoC);

			String caminho = Faces.getRealPath("/reports/r_comp_compra.jasper"); // caminho do relatorio quando tiver pronto
			String banner = Faces.getRealPath("/resources/img/Logo.png"); // imagem passada para o relatorio

			Map<String, Object> parametros = new HashMap<>();
			parametros.put("BANNER",banner);
			parametros.put("CODORCAMENTOC", codOrcamentoCInt); //codigo da compra 
			
			Connection conexao = HibernateUtil.getConexao();
			JasperPrint relatorio = JasperFillManager.fillReport(caminho,parametros, conexao);
			JasperViewer view = new JasperViewer(relatorio, false);
			view.show();

		} catch (JRException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar o relatório");
			erro.printStackTrace();
		}
	}

	public void listar(){
		OrcamentoCDAO dao = new OrcamentoCDAO();
		orcamentocs = dao.listar("codigo");
	}
	
	public void atualizarPrecoParcial(){
		for(ItemOrcaC itemOrcac : this.itensOrcaC){
			itemOrcac.setPrecoParcial(itemOrcac.getProduto().getPreco().multiply(new BigDecimal(itemOrcac.getQuantidade())));
		}
		this.calcular();
	}
	
	public void buscarP(){
		try{
			ProdutoDAO produtodao = new ProdutoDAO();
			Produto resultado = produtodao.buscarnome(produto.getDescricao());

			if(resultado == null){
				Messages.addGlobalWarn("Não existe este Produto Cadastrado");
				exibir = false;
			} else{
				exibir = true;
				produto = resultado;
			}

		}catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar buscar o Produto");
			erro.printStackTrace();
		}

	}
	@SuppressWarnings("unused")
	public String buscaCodigoOrcamentoC() {
		String codOrcamentoC = null;
		
		int i = 0;
		String select = null;
				
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		try {
			transacao= sessao.beginTransaction();
			select = " SELECT max(codigo) codigo FROM orcamentoc ";
			SQLQuery query = sessao.createSQLQuery(select);
			
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List data = query.list();

	         for(Object object : data)  {
	            Map row = (Map)object;
	            codOrcamentoC = row.get("codigo").toString();
	            
	            i++; 
	         }
	         transacao.commit();
				
		} catch (HibernateException e) {
			if (transacao != null)
				transacao.rollback();
			e.printStackTrace();
		} finally {
			sessao.close();
		}
		return codOrcamentoC;
	}
	
	@SuppressWarnings("deprecation")
	public void imprimirRelatorioCompra(String id){
		try {
			String codOrcamentoC = id;
			int codOrcamentoCInt = Integer.parseInt(codOrcamentoC);

			String caminho = Faces.getRealPath("/reports/r_comp_compra.jasper"); // caminho do relatorio quando tiver pronto
			String banner = Faces.getRealPath("/resources/img/Logo.png"); // imagem passada para o relatorio

			Map<String, Object> parametros = new HashMap<>();
			parametros.put("BANNER",banner);
			parametros.put("CODORCAMENTOC", codOrcamentoCInt); //codigo da Orcamentoc 
			
			Connection conexao = HibernateUtil.getConexao();
			JasperPrint relatorio = JasperFillManager.fillReport(caminho,parametros, conexao);
			JasperViewer view = new JasperViewer(relatorio, false);
			view.show();

		} catch (JRException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar o relatório");
			erro.printStackTrace();
		}
	}
	
	
	
}