package br.jus.tjse.eticket.bean;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import br.jus.tjse.eticket.bo.ChamadoBO;
import br.jus.tjse.eticket.bo.GrupoBO;
import br.jus.tjse.eticket.to.ChamadoTO;
import br.jus.tjse.eticket.to.GrupoTO;

@ManagedBean
public class CadastroChamadoBean {
	
	private ChamadoTO chamado = new ChamadoTO();
	private long nrChamado;
	private List<GrupoTO> grupos;
	private int cdGrupoSelecionado;
	@ManagedProperty(value="#{sessaoBean}")
	private SessaoBean sessaoBean;
	
	@PostConstruct
    public void init() {
    }

	public ChamadoTO getChamado() {
		return chamado;
	}

	public void setChamado(ChamadoTO chamado) {
		this.chamado = chamado;
	}

	public long getNrChamado() {
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

	public SessaoBean getSessaoBean() {
		return sessaoBean;
	}

	public void setSessaoBean(SessaoBean sessaoBean) {
		this.sessaoBean = sessaoBean;
	}
	
	public String cadastrar(){
		ChamadoBO cbo = ChamadoBO.getInstance();
		GrupoBO gbo = GrupoBO.getInstance();
		try {
			chamado.setNrMatriculaCriador(sessaoBean.getUsuarioLogado().getNrMatricula());
			chamado.setGrupoAtual(gbo.getGrupoByCodigo(cdGrupoSelecionado));
			cbo.cadastrarChamado(chamado);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "listaChamado";
	}

}
