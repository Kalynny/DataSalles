package br.com.datasalles.Bean;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.datasalles.dao.UsuarioDAO;
import br.com.datasalles.domain.Pessoa;
import br.com.datasalles.domain.Usuario;

@ManagedBean
@SessionScoped
public class AutenticacaoBean {
	private Usuario usuario;
	private Usuario usuarioLogado;

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@PostConstruct
	public void iniciar(){
		usuario = new Usuario();
		usuario.setPessoa(new Pessoa());
	}


	public void autenticar(){
		try{

			UsuarioDAO usuariodao = new UsuarioDAO();
			usuarioLogado = usuariodao.autenticar(usuario.getPessoa().getCpf(), usuario.getSenha());

			if(usuarioLogado == null){
				Messages.addGlobalError("CPF ou senha incorretos");
				return;
			}

			Faces.redirect("./pages/principal.xhtml");
		}catch(IOException erro){
			erro.printStackTrace();
		}
	}

	public boolean temPermissoes(List<String> permissoes){		
		for(String permissao : permissoes){
			if(usuarioLogado.getTipo() == permissao.charAt(0)){
				return true;
			}
		}

		return false;
	}

	public void sair() {
		try {
			System.out.println("entrou");
			usuario = new Usuario();
			usuario.setPessoa(new Pessoa());
			
			usuarioLogado = usuario;
			Faces.setSessionAttribute("autenticacaoBean", null);
			Faces.redirect("./pages/autenticacao.xhtml");

		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError(erro.getMessage());
		}
	}

}
