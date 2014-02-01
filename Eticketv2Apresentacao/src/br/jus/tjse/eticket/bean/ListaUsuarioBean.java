package br.jus.tjse.eticket.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.jus.tjse.eticket.bo.UsuarioBO;
import br.jus.tjse.eticket.tipo.TipoMensagem;
import br.jus.tjse.eticket.to.UsuarioTO;

@ManagedBean
@ViewScoped
public class ListaUsuarioBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String termoPesquisa;
	private List<UsuarioTO> usuarios;
	
	private TipoMensagem tipoMensagem = TipoMensagem.NEHUMA;
	private String mensagem;
	
	public String getTermoPesquisa() {
		return termoPesquisa;
	}

	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}

	public List<UsuarioTO> getUsuarios() {
		UsuarioBO ubo = UsuarioBO.getInstance();
		try {
			if (termoPesquisa==null || termoPesquisa.equals("")) {
				usuarios = ubo.getUsuarios();
			} else {
				usuarios = ubo.pesquisarUsuario(termoPesquisa);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	public void setUsuarios(List<UsuarioTO> usuarios) {
		this.usuarios = usuarios;
	}
	
	public TipoMensagem getTipoMensagem() {
		TipoMensagem tipoMensagemGet = (TipoMensagem) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("tipoMensagem");
		if (tipoMensagemGet!=null) {
			tipoMensagem=tipoMensagemGet;
		}
		return tipoMensagem;
	}

	public void setTipoMensagem(TipoMensagem tipoMensagem) {
		this.tipoMensagem = tipoMensagem;
	}

	public String getMensagem() {
		String mensagemGet = (String) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("mensagem");
		if (mensagemGet!=null) {
			mensagem = mensagemGet;
		}
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public void excluir(String nrMatricula) {
		try {
			UsuarioBO.getInstance().excluirUsuario(Integer.parseInt(nrMatricula));
			setTipoMensagem(TipoMensagem.SUCESSO);
			setMensagem("Usuário excluído com sucesso.");
		} catch (SQLException e) {
			setTipoMensagem(TipoMensagem.ERRO);
			setMensagem("Erro de acesso ao banco de dados.");
			e.printStackTrace();
		}
	}
	

}
