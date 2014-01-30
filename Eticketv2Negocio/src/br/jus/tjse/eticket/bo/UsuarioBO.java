package br.jus.tjse.eticket.bo;

import java.sql.SQLException;
import java.util.List;

import br.jus.tjse.eticket.dao.UsuarioDAO;
import br.jus.tjse.eticket.to.UsuarioTO;

public class UsuarioBO {
	
	private static final UsuarioBO INSTANCE = new UsuarioBO();
	
	public static synchronized UsuarioBO getInstance() {
		return INSTANCE;
	}
	
	private UsuarioBO(){}
	
	public List<UsuarioTO> getUsuarios() throws SQLException {
		return UsuarioDAO.getInstance().getUsuarios();
	}
	
	public UsuarioTO getUsuarioByMatricula(int nrMatricula) throws SQLException {
		UsuarioDAO ud = UsuarioDAO.getInstance();
		UsuarioTO u = ud.getUsuarioByMatricula(nrMatricula);
		if (u.getNrMatricula() == 0) {
			return null;
		}
		return u;
	}

	public void cadastrarUsuario(UsuarioTO usuario) throws SQLException {
		UsuarioDAO ud = UsuarioDAO.getInstance();
		if (usuario.getNrMatricula() == 0) {
			return;
		}
		UsuarioTO u = getUsuarioByMatricula(usuario.getNrMatricula());
		if (u==null) {
			ud.addUsuario(usuario);
		} else {
			ud.updateUsuario(usuario);
		}
	}

	public void excluirUsuario(int nrMatricula) throws SQLException {
		if (nrMatricula == 0) {
			return;
		}
		UsuarioDAO.getInstance().deleteUsuario(nrMatricula);
		
	}
	
	public List<UsuarioTO> pesquisarUsuarioPorNome(String termoPesquisa) throws SQLException {
		if (termoPesquisa==null || termoPesquisa.equals("")) {
			return null;
		}
		return UsuarioDAO.getInstance().pesqUsuarioByNome(termoPesquisa);
	}

}
