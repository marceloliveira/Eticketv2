package br.jus.tjse.eticket.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import br.jus.tjse.eticket.bo.ChamadoBO;
import br.jus.tjse.eticket.model.Chamado;
import br.jus.tjse.eticket.tipo.TipoMensagem;

@ManagedBean
public class ListaChamadoBean {
	
	private List<Chamado> chamados;
	private TipoMensagem tipoMensagem = TipoMensagem.NEHUMA;
	private String mensagem;
	private int tipoLista;
	@ManagedProperty(value="#{sessaoBean}")
	private SessaoBean sessaoBean;
	
	@PostConstruct
    public void init() {
    }

	public List<Chamado> getChamados() {
		ChamadoBO cbo = ChamadoBO.getInstance();
		switch (tipoLista) {
		case 1: chamados = cbo.getChamadosByResponsavel(sessaoBean.getUsuarioLogado().getNrMatricula()); break;
		case 2: break;
		default: chamados = cbo.getChamados(); break;
		}
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
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
		for (Chamado c:chamados) {
			if (classes.equals("")) {
				classes += ChamadoBO.getStyleClasse(c);
			} else {
				classes += ","+ChamadoBO.getStyleClasse(c);
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
