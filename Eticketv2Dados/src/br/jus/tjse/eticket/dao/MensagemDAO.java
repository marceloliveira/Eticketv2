package br.jus.tjse.eticket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.jus.tjse.eticket.conexao.Conexao;
import br.jus.tjse.eticket.to.MensagemTO;

public class MensagemDAO {

	private static final MensagemDAO INSTANCE = new MensagemDAO();

	public static synchronized MensagemDAO getInstance() {
		return INSTANCE;
	}

	private MensagemDAO() {
	}

	public List<MensagemTO> getMensagensByNrChamado(long nrChamado)
			throws SQLException {
		ArrayList<MensagemTO> mensagens = new ArrayList<MensagemTO>();
		
		UsuarioDAO udo = UsuarioDAO.getInstance();

		Connection con = Conexao.getInstance().getConexao();

		String sql = "select * from mensagem where nr_chamado = ?";

		PreparedStatement stm = con.prepareStatement(sql);
		stm.setLong(1, nrChamado);
		ResultSet rset = stm.executeQuery();

		while (rset.next()) {
			MensagemTO mensagem = new MensagemTO();
			mensagem.setNrChamado(rset.getLong("nr_chamado"));
			mensagem.setSqMensagem(rset.getInt("sq_mensagem"));
			mensagem.setUsuario(udo.getUsuarioByMatricula(rset.getInt("nr_matricula")));
			mensagem.setTxMensagem(rset.getString("tx_mensagem"));
			mensagem.setDhMensagem(rset.getTimestamp("dh_mensagem"));
			mensagens.add(mensagem);
		}

		rset.close();

		stm.close();

		return mensagens;
	}

	public void addMensagem(MensagemTO mensagem) throws SQLException {
		Connection con = Conexao.getInstance().getConexao();

		String sql = "insert into mensagem (nr_chamado,nr_matricula,tx_mensagem,dh_mensagem) "
				+ "values (?,?,?,now())";

		PreparedStatement stm = con.prepareStatement(sql);
		stm.setLong(1, mensagem.getNrChamado());
		stm.setInt(2, mensagem.getUsuario().getNrMatricula());
		stm.setString(3, mensagem.getTxMensagem());

		stm.executeUpdate();

		stm.close();
	}

}
