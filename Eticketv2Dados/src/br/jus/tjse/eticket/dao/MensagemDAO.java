package br.jus.tjse.eticket.dao;

public class MensagemDAO {
	
	private static final MensagemDAO INSTANCE = new MensagemDAO();

	public static synchronized MensagemDAO getInstance() {
		return INSTANCE;
	}

	private MensagemDAO() {
	}

}
