package br.jus.tjse.eticket.bean;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.jus.tjse.eticket.bo.UsuarioBO;
import br.jus.tjse.eticket.to.UsuarioTO;

@ManagedBean
@SessionScoped
public class SessaoBean {
	
	private UsuarioTO usuarioLogado;

	public UsuarioTO getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(UsuarioTO usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	public void logar(String nrMatricula) {
		UsuarioBO ubo = UsuarioBO.getInstance();
		try {
			this.usuarioLogado = ubo.getUsuariosByMatricula(Integer.parseInt(nrMatricula));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getNomeUsuario() {
		String nome = "";
		if (usuarioLogado == null) {
			nome = "Convidado";
		} else {
			nome = usuarioLogado.getTxNome();
		}
		return nome;
	}
	
	public String logout(){
		this.usuarioLogado = null;
		return "/login";
	}

}
