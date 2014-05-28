package br.jus.tjse.eticket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.jus.tjse.eticket.conexao.Conexao;
import br.jus.tjse.eticket.to.ChamadoTO;

public class ChamadoDAO {
	
	private static final ChamadoDAO INSTANCE = new ChamadoDAO();
	
	public static synchronized ChamadoDAO getInstance() {
		return INSTANCE;
	}
	
	private ChamadoDAO(){
		
	}
	
	public List<ChamadoTO> getChamados() throws SQLException {
		ArrayList<ChamadoTO> chamados = new ArrayList<ChamadoTO>();
		
		GrupoDAO gdo = GrupoDAO.getInstance();
		ResponsavelChamadoDAO rcdo = ResponsavelChamadoDAO.getInstance();
		UsuarioDAO udo = UsuarioDAO.getInstance();
		MensagemDAO mdo = MensagemDAO.getInstance();
		
		Connection con = Conexao.getInstance().getConexao();
		
		String sql = "select * from chamado order by nr_chamado desc";
		
		Statement stm = con.createStatement();
		
		ResultSet rset = stm.executeQuery(sql);
		
		while (rset.next()) {
			ChamadoTO c = new ChamadoTO();
			c.setNrChamado(rset.getLong("nr_chamado"));
			c.setTxTitulo(rset.getString("tx_titulo"));
			c.setTxDescricao(rset.getString("tx_descricao"));
			c.setDhCriacao(rset.getTimestamp("dh_criacao"));
			c.setFlStatus(rset.getString("fl_status").charAt(0));
			c.setGrupoAtual(gdo.getGrupoByCodigo(rset.getInt("cd_grupo_atual")));
			c.setUsuarioCriador(udo.getUsuarioByMatricula(rset.getInt("nr_matricula_criador")));
			c.setResponsaveis(rcdo.getResponsaveisByChamado(rset.getLong("nr_chamado")));
			c.setMensagens(mdo.getMensagensByNrChamado(rset.getLong("nr_chamado")));
			chamados.add(c);
		}
		
		return chamados;
	}
	
	public ChamadoTO getChamadoByNrChamado(long nrChamado) throws SQLException {
		ChamadoTO chamado = new ChamadoTO();

		GrupoDAO gdo = GrupoDAO.getInstance();
		ResponsavelChamadoDAO rcdo = ResponsavelChamadoDAO.getInstance();
		UsuarioDAO udo = UsuarioDAO.getInstance();
		MensagemDAO mdo = MensagemDAO.getInstance();
		
		Connection con = Conexao.getInstance().getConexao();

		String sql = "select * from chamado where nr_chamado = ?";

		PreparedStatement stm = con.prepareStatement(sql);
		stm.setLong(1, nrChamado);
		ResultSet rset = stm.executeQuery();

		while (rset.next()) {

			chamado.setNrChamado(rset.getLong("nr_chamado"));
			chamado.setTxTitulo(rset.getString("tx_titulo"));
			chamado.setTxDescricao(rset.getString("tx_descricao"));
			chamado.setDhCriacao(rset.getTimestamp("dh_criacao"));
			chamado.setFlStatus(rset.getString("fl_status").charAt(0));
			chamado.setGrupoAtual(gdo.getGrupoByCodigo(rset.getInt("cd_grupo_atual")));
			chamado.setUsuarioCriador(udo.getUsuarioByMatricula(rset.getInt("nr_matricula_criador")));
			chamado.setResponsaveis(rcdo.getResponsaveisByChamado(rset.getLong("nr_chamado")));
			chamado.setMensagens(mdo.getMensagensByNrChamado(rset.getLong("nr_chamado")));

		}

		rset.close();

		stm.close();

		return chamado;
	}

	public void addChamado(ChamadoTO chamado) throws SQLException {
		Connection con = Conexao.getInstance().getConexao();

		String sql = "insert into chamado (tx_titulo,tx_descricao,dh_criacao,fl_status, "
				+ "nr_matricula_criador,cd_grupo_atual) "
				+ "values (?,?,now(),?,?,?)";

		PreparedStatement stm = con.prepareStatement(sql);
		stm.setString(1, chamado.getTxTitulo());
		stm.setString(2, chamado.getTxDescricao());
		stm.setString(3, "" + chamado.getFlStatus());
		stm.setInt(4, chamado.getUsuarioCriador().getNrMatricula());
		stm.setInt(5, chamado.getGrupoAtual().getCdGrupo());

		stm.executeUpdate();

		stm.close();
	}
	
	public void updateChamado(ChamadoTO chamado) throws SQLException {
		Connection con = Conexao.getInstance().getConexao();

		String sql = "update chamado set tx_titulo = ?, tx_descricao = ?, fl_status = ?, "
				+ "cd_grupo_atual = ? where nr_chamado = ?";

		PreparedStatement stm = con.prepareStatement(sql);
		stm.setString(1, chamado.getTxTitulo());
		stm.setString(2, chamado.getTxDescricao());
		stm.setString(3, "" + chamado.getFlStatus());
		stm.setInt(4, chamado.getGrupoAtual().getCdGrupo());
		stm.setLong(5, chamado.getNrChamado());
		stm.executeUpdate();

		stm.close();
	}

}
