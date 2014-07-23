package br.jus.tjse.eticket.bean;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.jus.tjse.eticket.bo.ChamadoBO;
import br.jus.tjse.eticket.bo.GrupoBO;
import br.jus.tjse.eticket.model.Chamado;
import br.jus.tjse.eticket.model.Grupo;
import br.jus.tjse.eticket.model.Mensagem;

@ManagedBean
@SessionScoped
public class DetalhesChamadoBean {
	
	private long nrChamado;
	private Chamado chamado;
	private List<Grupo> grupos;
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
			} 
		}
		return nrChamado;
	}

	public void setNrChamado(long nrChamado) {
		this.nrChamado = nrChamado;
	}

	public List<Grupo> getGrupos() {
		GrupoBO gbo = GrupoBO.getInstance();
		try {
			grupos = gbo.getGrupos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public int getCdGrupoSelecionado() {
		return cdGrupoSelecionado;
	}

	public void setCdGrupoSelecionado(int cdGrupoSelecionado) {
		this.cdGrupoSelecionado = cdGrupoSelecionado;
	}

	public Chamado getChamado() {
		ChamadoBO cbo = ChamadoBO.getInstance();
		chamado = cbo.getChamadoByNumero(nrChamado);
		return chamado;
	}

	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}

	public String getTxMensagem() {
		return txMensagem;
	}

	public void setTxMensagem(String txMensagem) {
		this.txMensagem = txMensagem;
	}
	
	public void cadastrarMensagem() {
		Mensagem mensagem = new Mensagem();
		mensagem.setChamado(chamado);
		mensagem.setUsuario(sessaoBean.getUsuarioLogado());
		mensagem.setTxMensagem(txMensagem);
		chamado.addMensagen(mensagem);
	}

	public SessaoBean getSessaoBean() {
		return sessaoBean;
	}

	public void setSessaoBean(SessaoBean sessaoBean) {
		this.sessaoBean = sessaoBean;
	}
	
	public void atenderChamado() {
		ChamadoBO cbo = ChamadoBO.getInstance();
		cbo.addResponsavelChamado(sessaoBean.getUsuarioLogado().getNrMatricula(), nrChamado);
	}
	
	public void removerResponsavel(String nrMatricula) {
		ChamadoBO cbo = ChamadoBO.getInstance();
		cbo.removeResponsavelChamado(Integer.parseInt(nrMatricula),nrChamado);
	}
	
	public void fecharChamado() {
		ChamadoBO cbo = ChamadoBO.getInstance();
		chamado = cbo.getChamadoByNumero(nrChamado); 
		chamado.setFlStatus("F");
		cbo.cadastrarChamado(chamado);
	}
	public void reabrirChamado() {
		ChamadoBO cbo = ChamadoBO.getInstance();
		chamado = cbo.getChamadoByNumero(nrChamado); 
		chamado.setFlStatus("A");
		cbo.cadastrarChamado(chamado);
	}
	public void transferirChamado(String codGrupo) {
		ChamadoBO cbo = ChamadoBO.getInstance();
		GrupoBO gbo = GrupoBO.getInstance();
		try {
			chamado = cbo.getChamadoByNumero(nrChamado); 
			chamado.setGrupoAtual(gbo.getGrupoByCodigo(Integer.parseInt(codGrupo)));
			cbo.cadastrarChamado(chamado);
			cdGrupoSelecionado = 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean isFechado() {
		return chamado.getFlStatus().equals("F");
	}
	public boolean isAberto() {
		return chamado.getFlStatus().equals("A");
	}

}
