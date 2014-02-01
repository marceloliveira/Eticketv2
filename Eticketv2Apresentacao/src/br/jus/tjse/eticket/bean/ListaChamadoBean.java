package br.jus.tjse.eticket.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;

import br.jus.tjse.eticket.tipo.TipoMensagem;
import br.jus.tjse.eticket.to.ChamadoTO;

@ManagedBean
public class ListaChamadoBean {
	
	private List<ChamadoTO> chamados;
	private TipoMensagem tipoMensagem;
	private String mensagem;

	public List<ChamadoTO> getChamados() {
		return chamados;
	}

	public void setChamados(List<ChamadoTO> chamados) {
		this.chamados = chamados;
	}

	public TipoMensagem getTipoMensagem() {
		return tipoMensagem;
	}

	public void setTipoMensagem(TipoMensagem tipoMensagem) {
		this.tipoMensagem = tipoMensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public static String getStatusChamado(String flStatus) {
		String status = "";
		return status;
	}

}
