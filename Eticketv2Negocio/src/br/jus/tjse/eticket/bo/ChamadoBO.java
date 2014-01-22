package br.jus.tjse.eticket.bo;

import java.sql.SQLException;
import java.util.List;

import br.jus.tjse.eticket.dao.ChamadoDAO;
import br.jus.tjse.eticket.to.ChamadoTO;

public class ChamadoBO {
	
	public List<ChamadoTO> getChamados() throws SQLException {
		ChamadoDAO cd = ChamadoDAO.getInstance();
		return cd.getChamados();
	}

}
