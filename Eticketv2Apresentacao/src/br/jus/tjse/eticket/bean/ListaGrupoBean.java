package br.jus.tjse.eticket.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;

import br.jus.tjse.eticket.bo.GrupoBO;
import br.jus.tjse.eticket.to.GrupoTO;

@ManagedBean
public class ListaGrupoBean {
	
	private String termoPesquisa;
	private List<GrupoTO> grupos;

	public String getTermoPesquisa() {
		return termoPesquisa;
	}

	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}

	public List<GrupoTO> getGrupos() {
		GrupoBO gbo = GrupoBO.getInstance();
		try {
			if (termoPesquisa==null || termoPesquisa.equals("")) {
				grupos = gbo.getGrupos();
			} else {
				grupos = gbo.pesquisarGrupo(termoPesquisa);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return grupos;
	}

	public void setGrupos(List<GrupoTO> grupos) {
		this.grupos = grupos;
	}

	

}
