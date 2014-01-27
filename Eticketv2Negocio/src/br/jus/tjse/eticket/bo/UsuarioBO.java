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
		UsuarioDAO ud = UsuarioDAO.getInstance();
		return ud.getUsuarios();
	}

}
