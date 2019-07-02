package br.com.datasalles.Bean;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.datasalles.dao.HistoricoDAO;
import br.com.datasalles.dao.ProdutoDAO;
import br.com.datasalles.domain.Historico;
import br.com.datasalles.domain.Produto;


@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProdutoPesquisaBean implements Serializable  {

	private Produto produto;
	private Boolean exibir;
	private Historico historico;
	
	
	public Historico getHistorico() {
		return historico;
	}
	
	public void setHistorico(Historico historico) {
		this.historico = historico;
	}
	
	public Boolean getExibir() {
		return exibir;
	}
	
	public void setExibir(Boolean exibir) {
		this.exibir = exibir;
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
			@PostConstruct
			public void novo(){
				historico = new Historico();
				produto = new Produto();
				exibir = false;
			}
			
			
			public void buscar(){
				try{
				ProdutoDAO produtodao = new ProdutoDAO();
				Produto resultado = produtodao.buscar(produto.getCodigo());
				
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
	
	    	public void salvar() {
					try {
						historico.setHorario(new Date());
						historico.setProduto(produto);
						
						HistoricoDAO historicoDAO = new HistoricoDAO();
						historicoDAO.salvar(historico);
						
						Messages.addGlobalInfo("Histórico salvo com sucesso");
					} catch (RuntimeException erro) {
						Messages.addGlobalError("Ocorreu um erro ao tentar salvar o histórico");
						erro.printStackTrace();
					}
				}
	
}
