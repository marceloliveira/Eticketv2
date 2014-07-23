package br.jus.tjse.eticket.bean;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import br.jus.tjse.eticket.bo.ChamadoBO;
import br.jus.tjse.eticket.bo.GrupoBO;
import br.jus.tjse.eticket.model.Chamado;
import br.jus.tjse.eticket.model.Grupo;

@ManagedBean
public class CadastroChamadoBean {
	
	private Chamado chamado = new Chamado();
	private long nrChamado;
	private List<Grupo> grupos;
	private int cdGrupoSelecionado;
	@ManagedProperty(value="#{sessaoBean}")
	private SessaoBean sessaoBean;
	
	@PostConstruct
    public void init() {
    }

	public Chamado getChamado() {
		return chamado;
	}

	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}

	public long getNrChamado() {
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
			//TODO UsuarioDAO
			//chamado.setUsuarioCriador(sessaoBean.getUsuarioLogado());
			chamado.setGrupoAtual(gbo.getGrupoByCodigo(cdGrupoSelecionado));
			cbo.cadastrarChamado(chamado);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "listaChamado";
	}

}
