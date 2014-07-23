package br.jus.tjse.eticket.bo;

import java.util.List;

import br.jus.tjse.eticket.dao.ChamadoDAO;
import br.jus.tjse.eticket.model.Chamado;

public class ChamadoBO {
	
	private static final ChamadoBO INSTANCE = new ChamadoBO();

	public static synchronized ChamadoBO getInstance() {
		return INSTANCE;
	}

	private ChamadoBO() {
	}
	
	public List<Chamado> getChamados() {
		ChamadoDAO cd = ChamadoDAO.getInstance();
		return cd.getChamados();
	}
	
	public List<Chamado> getChamadosByResponsavel(int nrMatricula) {
		ChamadoDAO cd = ChamadoDAO.getInstance();
		return cd.getChamadosByResponsavel(nrMatricula);
	}
	
	public Chamado getChamadoByNumero(long nrChamado) {
		ChamadoDAO cd = ChamadoDAO.getInstance();
		Chamado c = cd.getChamadoByNrChamado(nrChamado);
		if (c.getNrChamado() == 0) {
			return null;
		}
		return c;
	}
	
	public void cadastrarChamado(Chamado chamado) {
		ChamadoDAO cd = ChamadoDAO.getInstance();
		Chamado c = getChamadoByNumero(chamado.getNrChamado());
		if (c == null) {
			chamado.setFlStatus("A");
			cd.addChamado(chamado);
		} else {
			cd.updateChamado(chamado);
		}
	}
	
	public void addResponsavelChamado(int nrMatricula, long nrChamado) {
		ChamadoDAO cd = ChamadoDAO.getInstance();
		cd.addResponsavelChamado(nrMatricula, nrChamado);
	}

	public void removeResponsavelChamado(int nrMatricula, long nrChamado) {
		ChamadoDAO cd = ChamadoDAO.getInstance();
		cd.removeResponsavelChamado(nrMatricula, nrChamado);
	}
	
	public static String getStyleClasse(Chamado chamado) {
		String status = "";
			switch (chamado.getFlStatus().charAt(0)) {
			case 'A': status = "success"; break;
			case 'F': status = "danger"; break;
			default: status = ""; break;
			}
		return status;
	}

}
