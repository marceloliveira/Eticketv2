package br.jus.tjse.eticket.bean;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;

import br.jus.tjse.eticket.bo.UsuarioBO;
import br.jus.tjse.eticket.to.UsuarioTO;

@ManagedBean
public class ListaUsuarioBean {
	
	private String termoPesquisa;
	public String getTermoPesquisa() {
		return termoPesquisa;
	}

	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}

	private List<UsuarioTO> usuarios;

	public List<UsuarioTO> getUsuarios() {
		UsuarioBO ubo = UsuarioBO.getInstance();
		try {
			if (termoPesquisa==null || termoPesquisa.equals("")) {
				usuarios = ubo.getUsuarios();
			} else {
				usuarios = ubo.pesquisarUsuarioPorNome(termoPesquisa);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	public void setUsuarios(List<UsuarioTO> usuarios) {
		this.usuarios = usuarios;
	}
	
	public void excluir(String nrMatricula) {
		try {
			UsuarioBO.getInstance().excluirUsuario(Integer.parseInt(nrMatricula));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
