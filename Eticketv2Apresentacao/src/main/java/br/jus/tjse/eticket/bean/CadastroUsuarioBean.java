package br.jus.tjse.eticket.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.jus.tjse.eticket.bo.UsuarioBO;
import br.jus.tjse.eticket.model.Usuario;
import br.jus.tjse.eticket.tipo.TipoMensagem;

@ManagedBean
public class CadastroUsuarioBean {
	
	private int nrMatricula;
	
	private Usuario usuario = new Usuario();
	
	private boolean alteracao;

	public boolean isAlteracao() {
		return alteracao;
	}

	public int getNrMatricula()  {
		String nrMatriculaTxt = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("nrMatricula");
		if (nrMatriculaTxt!=null && !nrMatriculaTxt.equals("") && !nrMatriculaTxt.equals("0")){
			try {
				nrMatricula = Integer.parseInt(nrMatriculaTxt);
				usuario = UsuarioBO.getInstance().getUsuarioByMatricula(nrMatricula);
				alteracao = true;
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		} else {
			alteracao = false;
		}
		
		return nrMatricula;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setNrMatricula(int nrMatricula) {
		this.nrMatricula = nrMatricula;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String cadastrar(){
		UsuarioBO ubo = UsuarioBO.getInstance();
		ubo.cadastrarUsuario(usuario);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("tipoMensagem", TipoMensagem.SUCESSO);
		if (isAlteracao()){
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("mensagem", "Usuário alterado com sucesso.");
		} else {
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("mensagem", "Usuário cadastrado com sucesso.");
		}
		return "listaUsuario?faces-redirect=true";
	}
	
	public String getTextoOperacao() {
		return alteracao?"Alterar":"Cadastrar";
	}

}
