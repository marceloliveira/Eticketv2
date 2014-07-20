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
			g.setIdCorFundo(rset.getString("id_cor_fundo"));
			g.setIdCorLetra(rset.getString("id_cor_letra"));
			g.setTxNomeAbreviado(rset.getString("tx_nome_abreviado"));
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
			g.setIdCorFundo(rset.getString("id_cor_fundo"));
			g.setIdCorLetra(rset.getString("id_cor_letra"));
			g.setTxNomeAbreviado(rset.getString("tx_nome_abreviado"));
		}
		
		rset.close();
		
		stm.close();
		return g;
	}
	public void addGrupo(GrupoTO grupo) throws SQLException {
		Connection con = Conexao.getInstance().getConexao();
		
		String sql = "insert into grupo (tx_nome,id_cor_fundo,id_cor_letra,tx_nome_abreviado) values (?,?,?,?)";
		
		PreparedStatement stm = con.prepareStatement(sql);
		stm.setString(1, grupo.getTxNome());
		stm.setString(2, grupo.getIdCorFundo());
		stm.setString(3, grupo.getIdCorLetra());
		stm.setString(4, grupo.getTxNomeAbreviado());
		stm.executeUpdate();
		
		stm.close();
	}
	public void updateGrupo(GrupoTO grupo) throws SQLException {
		Connection con = Conexao.getInstance().getConexao();
		
		String sql = "update grupo set tx_nome = ?, id_cor_fundo = ?, id_cor_letra = ?, tx_nome_abreviado = ? where cd_grupo = ?";
		
		PreparedStatement stm = con.prepareStatement(sql);
		stm.setString(1, grupo.getTxNome());
		stm.setString(2, grupo.getIdCorFundo());
		stm.setString(3, grupo.getIdCorLetra());
		stm.setString(4, grupo.getTxNomeAbreviado());
		stm.setInt(5, grupo.getCdGrupo());
		stm.executeUpdate();
		
		stm.close();
	}
	public List<GrupoTO> pesqGrupo(String termoPesquisa) throws SQLException {
		ArrayList<GrupoTO> grupos = new ArrayList<GrupoTO>();
		
		Connection con = Conexao.getInstance().getConexao();
		
		String sql = "select * from grupo where tx_nome ilike ?";
		
		PreparedStatement stm = con.prepareStatement(sql);
		stm.setString(1, "%"+termoPesquisa+"%");
		ResultSet rset = stm.executeQuery();
		
		while (rset.next()) {
			GrupoTO g = new GrupoTO();
			g.setCdGrupo(rset.getInt("cd_grupo"));
			g.setTxNome(rset.getString("tx_nome"));
			g.setIdCorFundo(rset.getString("id_cor_fundo"));
			g.setIdCorLetra(rset.getString("id_cor_letra"));
			g.setTxNomeAbreviado(rset.getString("tx_nome_abreviado"));
			grupos.add(g);
		}
		
		rset.close();
		
		stm.close();
		
		return grupos;
	}
	
	public void deleteGrupo(int cdGrupo) throws SQLException {
		Connection con = Conexao.getInstance().getConexao();
		
		String sql = "delete from grupo where cd_grupo = ?";
		
		PreparedStatement stm = con.prepareStatement(sql);
		stm.setInt(1, cdGrupo);
		stm.executeUpdate();
		
		stm.close();
	}

}
