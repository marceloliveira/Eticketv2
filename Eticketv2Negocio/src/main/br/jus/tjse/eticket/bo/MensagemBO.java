package br.jus.tjse.eticket.bo;

import java.sql.SQLException;
import java.util.List;

import br.jus.tjse.eticket.dao.MensagemDAO;
import br.jus.tjse.eticket.to.MensagemTO;

public class MensagemBO {

	private static final MensagemBO INSTANCE = new MensagemBO();

	public static synchronized MensagemBO getInstance() {
		return INSTANCE;
	}

	private MensagemBO() {
	}

	public List<MensagemTO> getMensagensByNrChamado(long nrChamado) throws SQLException {
		MensagemDAO msg = MensagemDAO.getInstance();
		List<MensagemTO> m = msg.getMensagensByNrChamado(nrChamado);
		return m;
	}

	public void cadastrarMensagem(MensagemTO mensagem) throws SQLException {
		MensagemDAO msg = MensagemDAO.getInstance();
		msg.addMensagem(mensagem);
	}

}
