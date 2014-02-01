package br.jus.tjse.eticket.bean;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import br.jus.tjse.eticket.bo.ChamadoBO;
import br.jus.tjse.eticket.tipo.TipoMensagem;
import br.jus.tjse.eticket.to.ChamadoTO;

@ManagedBean
public class ListaChamadoBean {
	
	private List<ChamadoTO> chamados;
	private TipoMensagem tipoMensagem;
	private String mensagem;
	private int tipoLista;
	@ManagedProperty(value="#{sessaoBean}")
	private SessaoBean sessaoBean;
	
	@PostConstruct
    public void init() {
    }

	public List<ChamadoTO> getChamados() {
		ChamadoBO cbo = ChamadoBO.getInstance();
		try {
			switch (tipoLista) {
			case 1: chamados = cbo.getChamadosByResponsavel(sessaoBean.getUsuarioLogado().getNrMatricula()); break;
			case 2: break;
			default: chamados = cbo.getChamados(); break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public int getTipoLista() {
		String tipoListaTxt = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("tipoLista");
		if (tipoListaTxt!=null && !tipoListaTxt.equals("")) {
			tipoLista = Integer.parseInt(tipoListaTxt);
		} else {
			tipoLista = 0;
		}
		return tipoLista;
	}

	public void setTipoLista(int tipoLista) {
		this.tipoLista = tipoLista;
	}
	
	public String getClassesChamado() {
		String classes = "";
		for (ChamadoTO c:chamados) {
			if (classes.equals("")) {
				classes += c.getStyleClasse();
			} else {
				classes += ","+c.getStyleClasse();
			}
		}
		return classes;
	}

	public SessaoBean getSessaoBean() {
		return sessaoBean;
	}

	public void setSessaoBean(SessaoBean sessaoBean) {
		this.sessaoBean = sessaoBean;
	}

}
