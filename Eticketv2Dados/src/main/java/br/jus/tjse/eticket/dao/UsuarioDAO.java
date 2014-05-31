package br.jus.tjse.eticket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.jus.tjse.eticket.conexao.Conexao;
import br.jus.tjse.eticket.to.UsuarioTO;

public class UsuarioDAO {
	
	private static final UsuarioDAO INSTANCE = new UsuarioDAO();
	
	public static synchronized UsuarioDAO getInstance() {
		return INSTANCE;
	}
	
	private UsuarioDAO(){}
	
	public List<UsuarioTO> getUsuarios() throws SQLException {
		ArrayList<UsuarioTO> usuarios = new ArrayList<UsuarioTO>();
		
		Connection con = Conexao.getInstance().getConexao();
		
		String sql = "select * from usuario";
		
		Statement stm = con.createStatement();
		
		ResultSet rset = stm.executeQuery(sql);
		
		while (rset.next()) {
			UsuarioTO u = new UsuarioTO();
			u.setNrMatricula(rset.getInt("nr_matricula"));
			u.setTxNome(rset.getString("tx_nome"));
			u.setTxTelefone(rset.getString("tx_telefone"));
			u.setTxEmail(rset.getString("tx_email"));
			usuarios.add(u);
		}
		
		rset.close();
		
		stm.close();
		
		return usuarios;
	}

	public List<UsuarioTO> pesqUsuario(String termoPesquisa) throws SQLException {
		ArrayList<UsuarioTO> usuarios = new ArrayList<UsuarioTO>();
		
		Connection con = Conexao.getInstance().getConexao();
		
		String sql = "select * from usuario where cast(nr_matricula as text) ilike ? or tx_nome ilike ? or tx_telefone ilike ? or tx_email ilike ?";
		
		PreparedStatement stm = con.prepareStatement(sql);
		stm.setString(1, "%"+termoPesquisa+"%");
		stm.setString(2, "%"+termoPesquisa+"%");
		stm.setString(3, "%"+termoPesquisa+"%");
		stm.setString(4, "%"+termoPesquisa+"%");
		ResultSet rset = stm.executeQuery();
		
		while (rset.next()) {
			UsuarioTO u = new UsuarioTO();
			u.setNrMatricula(rset.getInt("nr_matricula"));
			u.setTxNome(rset.getString("tx_nome"));
			u.setTxTelefone(rset.getString("tx_telefone"));
			u.setTxEmail(rset.getString("tx_email"));
			usuarios.add(u);
		}
		
		rset.close();
		
		stm.close();
		
		return usuarios;
	}

	public UsuarioTO getUsuarioByMatricula(int nrMatricula) throws SQLException {
		UsuarioTO usuario = new UsuarioTO();
		
		Connection con = Conexao.getInstance().getConexao();
		
		String sql = "select * from usuario where nr_matricula = ?";
		
		PreparedStatement stm = con.prepareStatement(sql);
		stm.setInt(1, nrMatricula);
		ResultSet rset = stm.executeQuery();
		
		while (rset.next()) {
			usuario.setNrMatricula(rset.getInt("nr_matricula"));
			usuario.setTxNome(rset.getString("tx_nome"));
			usuario.setTxTelefone(rset.getString("tx_telefone"));
			usuario.setTxEmail(rset.getString("tx_email"));
		}
		
		rset.close();
		
		stm.close();
		
		return usuario;
	}

	public void addUsuario(UsuarioTO usuario) throws SQLException {
		Connection con = Conexao.getInstance().getConexao();
		
		String sql = "insert into usuario (nr_matricula,tx_nome,tx_telefone,tx_email) values (?,?,?,?)";
		
		PreparedStatement stm = con.prepareStatement(sql);
		stm.setInt(1, usuario.getNrMatricula());
		stm.setString(2, usuario.getTxNome());
		stm.setString(3, usuario.getTxTelefone());
		stm.setString(4, usuario.getTxEmail());
		stm.executeUpdate();
		
		stm.close();
	}

	public void updateUsuario(UsuarioTO usuario) throws SQLException {
		Connection con = Conexao.getInstance().getConexao();
		
		String sql = "update usuario set tx_nome = ?, tx_telefone = ?, tx_email = ? where nr_matricula = ?";
		
		PreparedStatement stm = con.prepareStatement(sql);
		stm.setString(1, usuario.getTxNome());
		stm.setString(2, usuario.getTxTelefone());
		stm.setString(3, usuario.getTxEmail());
		stm.setInt(4, usuario.getNrMatricula());
		stm.executeUpdate();
		
		stm.close();
	}

	public void deleteUsuario(int nrMatricula) throws SQLException {
		Connection con = Conexao.getInstance().getConexao();
		
		String sql = "delete from usuario where nr_matricula = ?";
		
		PreparedStatement stm = con.prepareStatement(sql);
		stm.setInt(1, nrMatricula);
		stm.executeUpdate();
		
		stm.close();
	}

}
