package br.jus.tjse.eticket.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;

import br.jus.tjse.eticket.bo.UsuarioBO;
import br.jus.tjse.eticket.model.Usuario;

@ManagedBean
public class LoginBean {
	
	private List<Usuario> usuarios;
	private int nrMatriculaUsuarioSelecionado;
	
	public int getNrMatriculaUsuarioSelecionado() {
		return nrMatriculaUsuarioSelecionado;
	}

	public void setNrMatriculaUsuarioSelecionado(int nrMatriculaUsuarioSelecionado) {
		this.nrMatriculaUsuarioSelecionado = nrMatriculaUsuarioSelecionado;
	}

	public List<Usuario> getUsuarios() {
		UsuarioBO ubo = UsuarioBO.getInstance();
		try {
			usuarios = ubo.getUsuarios();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
