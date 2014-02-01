package br.jus.tjse.eticket.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;

import br.jus.tjse.eticket.to.ChamadoTO;

@ManagedBean
public class ListaChamadoBean {
	
	private List<ChamadoTO> chamados;

	public List<ChamadoTO> getChamados() {
		return chamados;
	}

	public void setChamados(List<ChamadoTO> chamados) {
		this.chamados = chamados;
	}
	

}
