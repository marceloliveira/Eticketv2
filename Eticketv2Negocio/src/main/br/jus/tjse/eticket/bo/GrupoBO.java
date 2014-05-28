package br.jus.tjse.eticket.bo;

import java.sql.SQLException;
import java.util.List;

import br.jus.tjse.eticket.dao.GrupoDAO;
import br.jus.tjse.eticket.dao.UsuarioGrupoDAO;
import br.jus.tjse.eticket.to.GrupoTO;

public class GrupoBO {
	
	private static final GrupoBO INSTANCE = new GrupoBO();

	public static synchronized GrupoBO getInstance() {
		return INSTANCE;
	}

	private GrupoBO() {
	}
	
	public List<GrupoTO> getGrupos() throws SQLException {
		return GrupoDAO.getInstance().getGrupos();
	}

	public GrupoTO getGrupoByCodigo(int cdGrupo) throws SQLException {
		GrupoDAO gd = GrupoDAO.getInstance();
		GrupoTO g = gd.getGrupoByCodigo(cdGrupo);
		if (g.getCdGrupo() == 0) {
			return null;
		}
		return g;
	}

	public void cadastrarGrupo(GrupoTO grupo) throws SQLException {
		GrupoDAO gd = GrupoDAO.getInstance();
		GrupoTO g = getGrupoByCodigo(grupo.getCdGrupo());
		if (g==null) {
			gd.addGrupo(grupo);
		} else {
			gd.updateGrupo(grupo);
		}
	}

	public List<GrupoTO> pesquisarGrupo(String termoPesquisa) throws SQLException {
		if (termoPesquisa==null || termoPesquisa.equals("")) {
			return null;
		}
		return GrupoDAO.getInstance().pesqGrupo(termoPesquisa);
	}

	public void excluirGrupo(int cdGrupo) throws SQLException {
		if (cdGrupo == 0) {
			return;
		}
		GrupoDAO.getInstance().deleteGrupo(cdGrupo);
	}

	public void cadastrarUsuario(int nrMatricula,
			int cdGrupo) throws SQLException {
		UsuarioGrupoDAO ugdo = UsuarioGrupoDAO.getInstance();
		ugdo.addUsuarioGrupo(nrMatricula,cdGrupo);
	}

	public void excluirUsuario(int nrMatricula, int cdGrupo) throws SQLException {
		UsuarioGrupoDAO ugdo = UsuarioGrupoDAO.getInstance();
		ugdo.deleteUsuarioGrupo(nrMatricula,cdGrupo);
	}

}
