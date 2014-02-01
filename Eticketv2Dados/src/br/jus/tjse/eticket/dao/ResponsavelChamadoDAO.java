package br.jus.tjse.eticket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.jus.tjse.eticket.conexao.Conexao;
import br.jus.tjse.eticket.to.UsuarioTO;

public class ResponsavelChamadoDAO {
	
	private static final ResponsavelChamadoDAO INSTANCE = new ResponsavelChamadoDAO();

	public static synchronized ResponsavelChamadoDAO getInstance() {
		return INSTANCE;
	}

	private ResponsavelChamadoDAO() {
	}
	
	public List<UsuarioTO> getResponsaveisByChamado(int nrChamado) throws SQLException {
		ArrayList<UsuarioTO> usuarios = new ArrayList<UsuarioTO>();
		
		UsuarioDAO udo = UsuarioDAO.getInstance();
		
		Connection con = Conexao.getInstance().getConexao();
		
		String sql = "select * from responsavel_chamado where nr_chamado = ?";
		
		PreparedStatement stm = con.prepareStatement(sql);
		
		ResultSet rset = stm.executeQuery();
		
		while (rset.next()) {
			UsuarioTO u = udo.getUsuarioByMatricula(rset.getInt("nr_matricula"));
			usuarios.add(u);
		}
		
		return usuarios;
	}
	
	public void addResponsavelChamado(int nrMatricula, int nrChamado) throws SQLException {
		Connection con = Conexao.getInstance().getConexao();
		
		String sql = "insert into responsavel_chamado (nr_matricula,nr_chamado) values (?,?)";
		
		PreparedStatement stm = con.prepareStatement(sql);
		stm.setInt(1, nrMatricula);
		stm.setInt(2, nrChamado);
		stm.executeUpdate();
	}

}
