package br.jus.tjse.eticket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.jus.tjse.eticket.conexao.Conexao;
import br.jus.tjse.eticket.to.GrupoTO;

public class GrupoDAO {
	
	private static final GrupoDAO INSTANCE = new GrupoDAO();
	
	public static synchronized GrupoDAO getInstance() {
		return INSTANCE;
	}
	private GrupoDAO(){}
	
	public List<GrupoTO> getGrupos() throws SQLException {
		ArrayList<GrupoTO> grupos = new ArrayList<GrupoTO>();
		
		Connection con = Conexao.getInstance().getConexao();
		
		String sql = "select * from grupo";
		
		Statement stm = con.createStatement();
		
		ResultSet rset = stm.executeQuery(sql);
		
		while (rset.next()){
			GrupoTO g = new GrupoTO();
			g.setCdGrupo(rset.getInt("cd_grupo"));
			g.setTxNome(rset.getString("tx_nome"));
			grupos.add(g);
		}
		
		rset.close();
		
		stm.close();
		
		return grupos;
	}
	public GrupoTO getGrupoByCodigo(int cdGrupo) throws SQLException {
		GrupoTO g = new GrupoTO();
		
		Connection con = Conexao.getInstance().getConexao();
		
		String sql = "select * from grupo where cd_grupo = ?";
		
		PreparedStatement stm = con.prepareStatement(sql);
		stm.setInt(1, cdGrupo);
		ResultSet rset = stm.executeQuery();
		
		while (rset.next()){
			g.setCdGrupo(rset.getInt("cd_grupo"));
			g.setTxNome(rset.getString("tx_nome"));
		}
		
		rset.close();
		
		stm.close();
		return g;
	}
	public void addGrupo(GrupoTO grupo) throws SQLException {
		Connection con = Conexao.getInstance().getConexao();
		
		String sql = "insert into grupo (cd_grupo,tx_nome) values (?,?)";
		
		PreparedStatement stm = con.prepareStatement(sql);
		stm.setInt(1, grupo.getCdGrupo());
		stm.setString(2, grupo.getTxNome());
		stm.executeUpdate();
		
		stm.close();
	}
	public void updateGrupo(GrupoTO grupo) throws SQLException {
		Connection con = Conexao.getInstance().getConexao();
		
		String sql = "update grupo set tx_nome = ? where cd_grupo = ?";
		
		PreparedStatement stm = con.prepareStatement(sql);
		stm.setString(1, grupo.getTxNome());
		stm.setInt(2, grupo.getCdGrupo());
		stm.executeUpdate();
		
		stm.close();
	}
	public List<GrupoTO> pesqGrupo(String termoPesquisa) throws SQLException {
		ArrayList<GrupoTO> grupos = new ArrayList<GrupoTO>();
		
		Connection con = Conexao.getInstance().getConexao();
		
		String sql = "select * from grupo where cast(cd_grupo as text) ilike ? or tx_nome ilike ?";
		
		PreparedStatement stm = con.prepareStatement(sql);
		stm.setString(1, "%"+termoPesquisa+"%");
		stm.setString(2, "%"+termoPesquisa+"%");
		ResultSet rset = stm.executeQuery();
		
		while (rset.next()) {
			GrupoTO g = new GrupoTO();
			g.setCdGrupo(rset.getInt("cd_grupo"));
			g.setTxNome(rset.getString("tx_nome"));
			grupos.add(g);
		}
		
		rset.close();
		
		stm.close();
		
		return grupos;
	}

}
