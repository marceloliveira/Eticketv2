package br.jus.tjse.eticket.bo;

import java.sql.SQLException;
import java.util.List;

import br.jus.tjse.eticket.dao.ChamadoDAO;
import br.jus.tjse.eticket.dao.ResponsavelChamadoDAO;
import br.jus.tjse.eticket.to.ChamadoTO;

public class ChamadoBO {
	
	private static final ChamadoBO INSTANCE = new ChamadoBO();

	public static synchronized ChamadoBO getInstance() {
		return INSTANCE;
	}

	private ChamadoBO() {
	}
	
	public List<ChamadoTO> getChamados() throws SQLException {
		ChamadoDAO cd = ChamadoDAO.getInstance();
		return cd.getChamados();
	}
	
	public List<ChamadoTO> getChamadosByResponsavel(int nrMatricula) throws SQLException {
		ResponsavelChamadoDAO rcdo = ResponsavelChamadoDAO.getInstance();
		return rcdo.getChamadosByResponsavel(nrMatricula);
	}
	
	public ChamadoTO getChamadoByNumero(long nrChamado) throws SQLException {
		ChamadoDAO cd = ChamadoDAO.getInstance();
		ChamadoTO c = cd.getChamadoByNrChamado(nrChamado);
		if (c.getNrChamado() == 0) {
			return null;
		}
		return c;
	}
	
	public void cadastrarChamado(ChamadoTO chamado) throws SQLException {
		ChamadoDAO cd = ChamadoDAO.getInstance();
		ChamadoTO c = getChamadoByNumero(chamado.getNrChamado());
		if (c == null) {
			chamado.setFlStatus('A');
			cd.addChamado(chamado);
		} else {
			cd.updateChamado(chamado);
		}
	}
	
	public void addResponsavelChamado(int nrMatricula, long nrChamado) throws SQLException {
		ResponsavelChamadoDAO rcd = ResponsavelChamadoDAO.getInstance();
		rcd.addResponsavelChamado(nrMatricula, nrChamado);
	}

	public void removeResponsavelChamado(int nrMatricula, long nrChamado) throws SQLException {
		ResponsavelChamadoDAO rcd = ResponsavelChamadoDAO.getInstance();
		rcd.removeResponsavelChamado(nrMatricula, nrChamado);
	}

}
