package br.com.datasalles.Bean;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;
import br.com.datasalles.dao.ClienteDAO;
import br.com.datasalles.dao.FuncionarioDAO;
import br.com.datasalles.dao.ProdutoDAO;
import br.com.datasalles.dao.AvariaDAO;
import br.com.datasalles.domain.Cliente;
import br.com.datasalles.domain.Funcionario;
import br.com.datasalles.domain.ItemAvaria;
import br.com.datasalles.domain.Produto;
import br.com.datasalles.domain.Avaria;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class AvariaBean implements Serializable {
	private Avaria avaria;

	private List<Produto> produtos;
	private List<ItemAvaria> itensAvaria;
	private List<Cliente> clientes;
	private List<Funcionario> funcionarios;

	public Avaria getAvaria() {
		return avaria;
	}

	public void setAvaria(Avaria avaria) {
		this.avaria = avaria;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<ItemAvaria> getItensAvaria() {
		return itensAvaria;
	}

	public void setItensAvaria(List<ItemAvaria> itensAvaria) {
		this.itensAvaria = itensAvaria;
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

	@PostConstruct
	public void novo() {
		try {
			avaria = new Avaria();
			avaria.setPrecoTotal(new BigDecimal("0.00"));

			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar("descricao");

			itensAvaria = new ArrayList<>();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar carregar a tela de avarias");
			erro.printStackTrace();
		}
	}

	public void adicionar(ActionEvent evento) {
		Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

		int achou = -1;
		for (int posicao = 0; posicao < itensAvaria.size(); posicao++) {
			if (itensAvaria.get(posicao).getProduto().equals(produto)) {
				achou = posicao;
			}
		}

		if (achou < 0) {
			ItemAvaria itemAvaria = new ItemAvaria();
			itemAvaria.setPrecoParcial(produto.getPreco());
			itemAvaria.setProduto(produto);
			itemAvaria.setQuantidade(new Short("1"));

			itensAvaria.add(itemAvaria);
		} else {
			ItemAvaria itemAvaria = itensAvaria.get(achou);
			itemAvaria.setQuantidade(new Short(itemAvaria.getQuantidade() + 1 + ""));
			itemAvaria.setPrecoParcial(produto.getPreco().multiply(new BigDecimal(itemAvaria.getQuantidade())));
		}

		calcular();
	}
	
	public void subtrair(ActionEvent evento) {
		Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

		int achou = -1;
		for (int posicao = 0; posicao < itensAvaria.size(); posicao++) {
			if (itensAvaria.get(posicao).getProduto().equals(produto)) {
				achou = posicao;
			}
		}

		if (achou < 0) {
			ItemAvaria itemAvaria = new ItemAvaria();
			itemAvaria.setPrecoParcial(produto.getPreco());
			itemAvaria.setProduto(produto);
			itemAvaria.setQuantidade(new Short("1"));

			itensAvaria.add(itemAvaria);
		} else {
			ItemAvaria itemAvaria = itensAvaria.get(achou);
			itemAvaria.setQuantidade(new Short(itemAvaria.getQuantidade() - 1 + ""));
			itemAvaria.setPrecoParcial(produto.getPreco().multiply(new BigDecimal(itemAvaria.getQuantidade())));
		}

		calcular();
	}

	public void remover(ActionEvent evento) {
		ItemAvaria itemAvaria = (ItemAvaria) evento.getComponent().getAttributes().get("itemSelecionado");

		int achou = -1;
		for (int posicao = 0; posicao < itensAvaria.size(); posicao++) {
			if (itensAvaria.get(posicao).getProduto().equals(itemAvaria.getProduto())) {
				achou = posicao;
			}
		}

		if (achou > -1) {
			itensAvaria.remove(achou);
		}

		calcular();
	}

	public void calcular() {
		avaria.setPrecoTotal(new BigDecimal("0.00"));

		for (int posicao = 0; posicao < itensAvaria.size(); posicao++) {
			ItemAvaria itemAvaria = itensAvaria.get(posicao);
			avaria.setPrecoTotal(avaria.getPrecoTotal().add(itemAvaria.getPrecoParcial()));
		}
	}

	public void finalizar() {
		try {
			avaria.setHorario(new Date());
			avaria.setCliente(null);
			avaria.setFuncionario(null);
			
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listarOrdenado();

			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listarOrdenado();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar finalizar a avaria");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			if(avaria.getPrecoTotal().signum() == 0){
				Messages.addGlobalError("Informe pelo menos um item para a avaria");
				return;
			}
			
			AvariaDAO avariaDAO = new AvariaDAO();
			avariaDAO.salvar(avaria, itensAvaria);
			
			avaria = new Avaria();
			avaria.setPrecoTotal(new BigDecimal("0.00"));

			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar("descricao");

			itensAvaria = new ArrayList<>();
			
			Messages.addGlobalInfo("Avaria realizada com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a avaria");
			erro.printStackTrace();
		}
	}
}