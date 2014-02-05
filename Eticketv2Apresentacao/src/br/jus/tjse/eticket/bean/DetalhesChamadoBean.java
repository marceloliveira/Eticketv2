package br.jus.tjse.eticket.bean;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import br.jus.tjse.eticket.bo.ChamadoBO;
import br.jus.tjse.eticket.bo.GrupoBO;
import br.jus.tjse.eticket.bo.MensagemBO;
import br.jus.tjse.eticket.to.ChamadoTO;
import br.jus.tjse.eticket.to.GrupoTO;
import br.jus.tjse.eticket.to.MensagemTO;

@ManagedBean
public class DetalhesChamadoBean {
	
	private long nrChamado;
	private ChamadoTO chamado;
	private List<GrupoTO> grupos;
	private int cdGrupoSelecionado;
	private String txMensagem;
	@ManagedProperty(value="#{sessaoBean}")
	private SessaoBean sessaoBean;
	
	@PostConstruct
    public void init() {
    }

	public long getNrChamado() {
		String nrChamadoTxt = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("nrChamado");
		if (nrChamadoTxt!=null && !nrChamadoTxt.equals("") && !nrChamadoTxt.equals("0")){
			try {
				nrChamado = Long.parseLong(nrChamadoTxt);
				ChamadoBO cbo = ChamadoBO.getInstance();
				chamado = cbo.getChamadoByNumero(nrChamado);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		return nrChamado;
	}

	public void setNrChamado(long nrChamado) {
		this.nrChamado = nrChamado;
	}

	public List<GrupoTO> getGrupos() {
		GrupoBO gbo = GrupoBO.getInstance();
		try {
			grupos = gbo.getGrupos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return grupos;
	}

	public void setGrupos(List<GrupoTO> grupos) {
		this.grupos = grupos;
	}

	public int getCdGrupoSelecionado() {
		return cdGrupoSelecionado;
	}

	public void setCdGrupoSelecionado(int cdGrupoSelecionado) {
		this.cdGrupoSelecionado = cdGrupoSelecionado;
	}

	public ChamadoTO getChamado() {
		ChamadoBO cbo = ChamadoBO.getInstance();
		try {
			chamado = cbo.getChamadoByNumero(nrChamado);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chamado;
	}

	public void setChamado(ChamadoTO chamado) {
		this.chamado = chamado;
	}

	public String getTxMensagem() {
		return txMensagem;
	}

	public void setTxMensagem(String txMensagem) {
		this.txMensagem = txMensagem;
	}
	
	public void cadastrarMensagem() {
		MensagemBO mbo = MensagemBO.getInstance();
		MensagemTO mensagem = new MensagemTO();
		mensagem.setNrChamado(nrChamado);
		mensagem.setUsuario(sessaoBean.getUsuarioLogado());
		mensagem.setTxMensagem(txMensagem);
		try {
			mbo.cadastrarMensagem(mensagem);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public SessaoBean getSessaoBean() {
		return sessaoBean;
	}

	public void setSessaoBean(SessaoBean sessaoBean) {
		this.sessaoBean = sessaoBean;
	}
	
	public void atenderChamado() {
		
	}
	
	public void fecharChamado() {
		ChamadoBO cbo = ChamadoBO.getInstance();
		chamado.setFlStatus('F');
		try {
			cbo.cadastrarChamado(chamado);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
