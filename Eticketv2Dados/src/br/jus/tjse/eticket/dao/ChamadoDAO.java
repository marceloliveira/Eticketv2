package br.jus.tjse.eticket.dao;

import java.sql.Connection;
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
		
		Connection con = Conexao.getInstance().getConexao();
		
		String sql = "select * from chamado";
		
		Statement stm = con.createStatement();
		
		ResultSet rset = stm.executeQuery(sql);
		
		while (rset.next()) {
			ChamadoTO c = new ChamadoTO();
			c.setNrChamado(rset.getLong("nr_chamado"));
			c.setTxTitulo(rset.getString("tx_titulo"));
			c.setTxDescricao(rset.getString("tx_descricao"));
			c.setDhCriacao(rset.getTimestamp("dh_criacao"));
			c.setFlStatus(rset.getString("fl_status").charAt(0));
			c.setCdGrupoAtual(rset.getInt("cd_grupo_atual"));
			c.setNrMatriculaCriador(rset.getInt("nr_matricula_criador"));
			chamados.add(c);
		}
		
		return chamados;
	}

}
