package br.jus.tjse.eticket.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.jus.tjse.eticket.bo.UsuarioBO;
import br.jus.tjse.eticket.model.Usuario;

@ManagedBean
@SessionScoped
public class SessaoBean {
	
	private int nrMatriculaLogada;
	private String txLoginLogado;
	private Usuario usuarioLogado;
	
	public SessaoBean() {
		super();
	}

	public Usuario getUsuarioLogado() {
		UsuarioBO ubo = UsuarioBO.getInstance();
		String login = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
		if ((login!=null)&&((txLoginLogado==null)||(!txLoginLogado.equals(login)))) {
			usuarioLogado = ubo.getUsuarioByLogin(login);
			nrMatriculaLogada = usuarioLogado.getNrMatricula();
			txLoginLogado = login;
		}
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	public String logar() {
		UsuarioBO ubo = UsuarioBO.getInstance();
		try {
			String login = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
			usuarioLogado = ubo.getUsuarioByLogin(login);
			nrMatriculaLogada = usuarioLogado.getNrMatricula();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/publico/chamado/listaChamado.tjse";
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
		txLoginLogado = null;
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.invalidate();
		return "/publico/login.tjse";
	}
	
	public String getVisibilidade(){
		String nome = "";
		if (usuarioLogado == null) {
			nome = "hide";
		}
		return nome;
	}
	
	public String getVisibilidadeResponsiva(){
		String nome = "hidden-xs";
		if (usuarioLogado == null) {
			nome = "hide";
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
			nome = "/publico/login.tjse";
		} else {
			nome = "/publico/chamado/listaChamado.tjse";
		}
		return nome;
	}

	public String getTxLoginLogado() {
		return txLoginLogado;
	}

	public void setTxLoginLogado(String txLoginLogado) {
		this.txLoginLogado = txLoginLogado;
	}

}
