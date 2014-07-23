package br.jus.tjse.eticket.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.jus.tjse.eticket.bo.GrupoBO;
import br.jus.tjse.eticket.bo.UsuarioBO;
import br.jus.tjse.eticket.model.Grupo;
import br.jus.tjse.eticket.model.Usuario;

@ManagedBean
@ViewScoped
public class UsuarioGrupoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String termoPesquisaGrupo;
	private List<Grupo> grupos;
	private int cdGrupoSelecionado;
	private String termoPesquisaUsuario;
	private List<Usuario> usuarios;
	private int nrMatriculaSelecionada;
	private List<Usuario> usuariosGrupoSelecionado;

	public String getTermoPesquisaUsuario() {
		return termoPesquisaUsuario;
	}

	public void setTermoPesquisaUsuario(String termoPesquisaUsuario) {
		this.termoPesquisaUsuario = termoPesquisaUsuario;
	}

	public List<Usuario> getUsuarios() {
		UsuarioBO ubo = UsuarioBO.getInstance();
		try {
			if (termoPesquisaUsuario==null || termoPesquisaUsuario.equals("")) {
				usuarios = ubo.getUsuarios();
			} else {
				usuarios = ubo.pesquisarUsuario(termoPesquisaUsuario);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public int getNrMatriculaSelecionada() {
		return nrMatriculaSelecionada;
	}

	public void setNrMatriculaSelecionada(int nrMatriculaSelecionada) {
		this.nrMatriculaSelecionada = nrMatriculaSelecionada;
	}

	public int getCdGrupoSelecionado() {
		return cdGrupoSelecionado;
	}

	public void setCdGrupoSelecionado(int cdGrupoSelecionado) {
		this.cdGrupoSelecionado = cdGrupoSelecionado;
	}

	public String getTermoPesquisaGrupo() {
		return termoPesquisaGrupo;
	}

	public void setTermoPesquisaGrupo(String termoPesquisaGrupo) {
		this.termoPesquisaGrupo = termoPesquisaGrupo;
	}

	public List<Grupo> getGrupos() {
		GrupoBO gbo = GrupoBO.getInstance();
		try {
			if (termoPesquisaGrupo==null || termoPesquisaGrupo.equals("")) {
				grupos = gbo.getGrupos();
			} else {
				grupos = gbo.pesquisarGrupo(termoPesquisaGrupo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public List<Usuario> getUsuariosGrupoSelecionado() {
		UsuarioBO ubo = UsuarioBO.getInstance();
		usuariosGrupoSelecionado = ubo.getUsuariosByGrupo(cdGrupoSelecionado);
		return usuariosGrupoSelecionado;
	}

	public void setUsuariosGrupoSelecionado(List<Usuario> usuariosGrupoSelecionado) {
		this.usuariosGrupoSelecionado = usuariosGrupoSelecionado;
	}
	
	public void cadastrarUsuario() {
		GrupoBO gbo = GrupoBO.getInstance();
		try {
			gbo.cadastrarUsuario(nrMatriculaSelecionada,cdGrupoSelecionado);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void excluirUsuario(String nrMatricula) {
		try {
			GrupoBO.getInstance().excluirUsuario(Integer.parseInt(nrMatricula),cdGrupoSelecionado);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
