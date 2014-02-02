package br.jus.tjse.eticket.bean;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.jus.tjse.eticket.bo.UsuarioBO;
import br.jus.tjse.eticket.to.UsuarioTO;

@ManagedBean
@SessionScoped
public class SessaoBean {
	
	private int nrMatriculaLogada;
	private UsuarioTO usuarioLogado;

	public UsuarioTO getUsuarioLogado() {
		try {
			usuarioLogado = UsuarioBO.getInstance().getUsuarioByMatricula(nrMatriculaLogada);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuarioLogado;
	}

	public void setUsuarioLogado(UsuarioTO usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	public String logar(String nrMatricula) {
		UsuarioBO ubo = UsuarioBO.getInstance();
		try {
			nrMatriculaLogada = Integer.parseInt(nrMatricula);
			usuarioLogado = ubo.getUsuarioByMatricula(nrMatriculaLogada);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/chamado/listaChamado.tjse";
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
		nrMatriculaLogada = 0;
		usuarioLogado = null;
		return "/login.tjse";
	}
	
	public String getVisibilidade(){
		String nome = "";
		if (usuarioLogado == null) {
			nome = "hide";
		} else {
			nome = "show";
		}
		return nome;
	}
	
	public String deleteLogadoDisable(String nrMatricula) {
		String nome = "";
		if (nrMatriculaLogada == Integer.parseInt(nrMatricula)) {
			nome = "disabled";
		}
		return nome;
	}
	
	public String getBrand() {
		String nome = "";
		if (usuarioLogado == null) {
			nome = "/login.tjse";
		} else {
			nome = "/chamado/listaChamado.tjse";
		}
		return nome;
	}

}
