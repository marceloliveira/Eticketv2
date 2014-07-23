package br.jus.tjse.eticket.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.jus.tjse.eticket.bo.GrupoBO;
import br.jus.tjse.eticket.model.Grupo;
import br.jus.tjse.eticket.tipo.TipoMensagem;

@ManagedBean
@ViewScoped
public class ListaGrupoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String termoPesquisa;
	private List<Grupo> grupos;
	
	private TipoMensagem tipoMensagem = TipoMensagem.NEHUMA;
	private String mensagem;

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

	public String getTermoPesquisa() {
		return termoPesquisa;
	}

	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}

	public List<Grupo> getGrupos() {
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

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public void excluir(String cdGrupo) {
		try {
			GrupoBO.getInstance().excluirGrupo(Integer.parseInt(cdGrupo));
			setTipoMensagem(TipoMensagem.SUCESSO);
			setMensagem("Grupo excluído com sucesso.");
		} catch (SQLException e) {
			setTipoMensagem(TipoMensagem.ERRO);
			setMensagem("Erro de acesso ao banco de dados.");
			e.printStackTrace();
		}
	}

	

}
