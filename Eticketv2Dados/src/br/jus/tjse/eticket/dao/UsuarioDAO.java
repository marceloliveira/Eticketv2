package br.jus.tjse.eticket.dao;

import java.sql.Connection;
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
		
		return usuarios;
	}

}
