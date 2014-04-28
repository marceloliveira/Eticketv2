package br.jus.tjse.eticket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.jus.tjse.eticket.conexao.Conexao;
import br.jus.tjse.eticket.to.ChamadoTO;
import br.jus.tjse.eticket.to.UsuarioTO;

public class ResponsavelChamadoDAO {
	
	private static final ResponsavelChamadoDAO INSTANCE = new ResponsavelChamadoDAO();

	public static synchronized ResponsavelChamadoDAO getInstance() {
		return INSTANCE;
	}

	private ResponsavelChamadoDAO() {
	}
	
	public List<UsuarioTO> getResponsaveisByChamado(long nrChamado) throws SQLException {
		ArrayList<UsuarioTO> usuarios = new ArrayList<UsuarioTO>();
		
		UsuarioDAO udo = UsuarioDAO.getInstance();
		
		Connection con = Conexao.getInstance().getConexao();
		
		String sql = "select * from responsavel_chamado where nr_chamado = ?";
		
		PreparedStatement stm = con.prepareStatement(sql);
		stm.setLong(1, nrChamado);
		ResultSet rset = stm.executeQuery();
		
		while (rset.next()) {
			UsuarioTO u = udo.getUsuarioByMatricula(rset.getInt("nr_matricula"));
			usuarios.add(u);
		}
		
		return usuarios;
	}
	
	public void addResponsavelChamado(int nrMatricula, long nrChamado) throws SQLException {
		Connection con = Conexao.getInstance().getConexao();
		
		String sql = "insert into responsavel_chamado (nr_matricula,nr_chamado) values (?,?)";
		
		PreparedStatement stm = con.prepareStatement(sql);
		stm.setInt(1, nrMatricula);
		stm.setLong(2, nrChamado);
		stm.executeUpdate();
	}
	
	public List<ChamadoTO> getChamadosByResponsavel(int nrMatricula) throws SQLException{
		ArrayList<ChamadoTO> chamados = new ArrayList<ChamadoTO>();
		
		ChamadoDAO cdo = ChamadoDAO.getInstance();
		
		Connection con = Conexao.getInstance().getConexao();
		
		String sql = "select * from responsavel_chamado where nr_matricula = ?";
		
		PreparedStatement stm = con.prepareStatement(sql);
		stm.setLong(1, nrMatricula);
		ResultSet rset = stm.executeQuery();
		
		while (rset.next()) {
			ChamadoTO c = cdo.getChamadoByNrChamado(rset.getLong("nr_chamado"));
			chamados.add(c);
		}
		
		return chamados;
	}

	public void removeResponsavelChamado(int nrMatricula, long nrChamado) throws SQLException {
		Connection con = Conexao.getInstance().getConexao();
		
		String sql = "delete from responsavel_chamado where nr_matricula = ? and nr_chamado = ?";
		
		PreparedStatement stm = con.prepareStatement(sql);
		stm.setInt(1, nrMatricula);
		stm.setLong(2, nrChamado);
		stm.executeUpdate();
	}

}
