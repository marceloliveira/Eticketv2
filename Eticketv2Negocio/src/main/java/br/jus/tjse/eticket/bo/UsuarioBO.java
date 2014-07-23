package br.jus.tjse.eticket.bo;

import java.util.List;

import br.jus.tjse.eticket.dao.UsuarioDAO;
import br.jus.tjse.eticket.model.Usuario;

public class UsuarioBO {
	
	private static final UsuarioBO INSTANCE = new UsuarioBO();
	
	public static synchronized UsuarioBO getInstance() {
		return INSTANCE;
	}
	
	private UsuarioBO(){}
	
	public List<Usuario> getUsuarios() {
		return UsuarioDAO.getInstance().getUsuarios();
	}
	
	public Usuario getUsuarioByMatricula(int nrMatricula) {
		UsuarioDAO ud = UsuarioDAO.getInstance();
		Usuario u = ud.getUsuarioByMatricula(nrMatricula);
		if (u.getNrMatricula() == 0) {
			return null;
		}
		return u;
	}

	public void cadastrarUsuario(Usuario usuario) {
		UsuarioDAO ud = UsuarioDAO.getInstance();
		if (usuario.getNrMatricula() == 0) {
			return;
		}
		Usuario u = getUsuarioByMatricula(usuario.getNrMatricula());
		if (u==null) {
			ud.addUsuario(usuario);
		} else {
			ud.updateUsuario(usuario);
		}
	}

	public void excluirUsuario(int nrMatricula) {
		if (nrMatricula == 0) {
			return;
		}
		UsuarioDAO.getInstance().deleteUsuario(nrMatricula);
		
	}
	
	public List<Usuario> pesquisarUsuario(String termoPesquisa) {
		if (termoPesquisa==null || termoPesquisa.equals("")) {
			return null;
		}
		return UsuarioDAO.getInstance().pesqUsuario(termoPesquisa);
	}
	
	public List<Usuario> getUsuariosByGrupo(int cdGrupo) {
		UsuarioDAO ud = UsuarioDAO.getInstance();
		List<Usuario> usuarios = ud.getUsuariosByGrupo(cdGrupo);
		return usuarios;
	}

}
