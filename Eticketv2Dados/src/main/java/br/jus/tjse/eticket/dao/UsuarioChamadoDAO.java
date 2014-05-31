package br.jus.tjse.eticket.dao;

public class UsuarioChamadoDAO {
	
	private static final UsuarioChamadoDAO INSTANCE = new UsuarioChamadoDAO();

	public static synchronized UsuarioChamadoDAO getInstance() {
		return INSTANCE;
	}

	private UsuarioChamadoDAO() {
	}

}
