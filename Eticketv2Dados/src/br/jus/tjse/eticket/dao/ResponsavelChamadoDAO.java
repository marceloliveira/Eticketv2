package br.jus.tjse.eticket.dao;

public class ResponsavelChamadoDAO {
	
	private static final ResponsavelChamadoDAO INSTANCE = new ResponsavelChamadoDAO();

	public static synchronized ResponsavelChamadoDAO getInstance() {
		return INSTANCE;
	}

	private ResponsavelChamadoDAO() {
	}

}
