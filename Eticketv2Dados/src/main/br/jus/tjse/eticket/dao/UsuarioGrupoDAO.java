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
import br.jus.tjse.eticket.to.UsuarioTO;

public class UsuarioGrupoDAO {
	
	private static final UsuarioGrupoDAO INSTANCE = new UsuarioGrupoDAO();

	public static synchronized UsuarioGrupoDAO getInstance() {
		return INSTANCE;
	}

	private UsuarioGrupoDAO() {
	}
	
	public List<GrupoTO> getGruposByUsuario(int nrMatricula) throws SQLException {
		ArrayList<GrupoTO> grupos = new ArrayList<GrupoTO>();
		
		Connection con = Conexao.getInstance().getConexao();
		
		String sql = "select * from usuario_grupo where nrMatricula = ?";
		
		Statement stm = con.createStatement();
		
		ResultSet rset = stm.executeQuery(sql);
		
		while (rset.next()){
			GrupoDAO gdo = GrupoDAO.getInstance();
			GrupoTO g = gdo.getGrupoByCodigo(rset.getInt("cd_grupo"));
			grupos.add(g);
		}
		
		rset.close();
		
		stm.close();
		
		return grupos;
	}
	
	public List<UsuarioTO> getUsuariosByGrupo(int cdGrupo) throws SQLException {
		ArrayList<UsuarioTO> usuarios = new ArrayList<UsuarioTO>();
		
		Connection con = Conexao.getInstance().getConexao();
		
		String sql = "select * from usuario_grupo where cd_grupo = ?";
		
		PreparedStatement stm = con.prepareStatement(sql);
		stm.setInt(1, cdGrupo);
		ResultSet rset = stm.executeQuery();
		
		while (rset.next()){
			UsuarioDAO udo = UsuarioDAO.getInstance();
			UsuarioTO u = udo.getUsuarioByMatricula(rset.getInt("nr_matricula"));
			usuarios.add(u);
		}
		
		rset.close();
		
		stm.close();
		
		return usuarios;
	}

	public void addUsuarioGrupo(int nrMatricula, int cdGrupo) throws SQLException {
		Connection con = Conexao.getInstance().getConexao();
		
		String sql = "insert into usuario_grupo (nr_matricula,cd_grupo) values (?,?)";
		
		PreparedStatement stm = con.prepareStatement(sql);
		stm.setInt(1, nrMatricula);
		stm.setInt(2, cdGrupo);
		stm.executeUpdate();
		
	}

	public void deleteUsuarioGrupo(int nrMatricula, int cdGrupo) throws SQLException {
		Connection con = Conexao.getInstance().getConexao();
		
		String sql = "delete from usuario_grupo where nr_matricula = ? and cd_grupo = ?";
		
		PreparedStatement stm = con.prepareStatement(sql);
		stm.setInt(1, nrMatricula);
		stm.setInt(2, cdGrupo);
		stm.executeUpdate();
	}

}
