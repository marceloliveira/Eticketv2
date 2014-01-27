package br.jus.tjse.eticket.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;

import br.jus.tjse.eticket.bo.UsuarioBO;
import br.jus.tjse.eticket.to.UsuarioTO;

@ManagedBean
public class UsuarioBean {
	
	private List<UsuarioTO> usuarios;

	public List<UsuarioTO> getUsuarios() {
		UsuarioBO ubo = UsuarioBO.getInstance();
		try {
			usuarios = ubo.getUsuarios();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	public void setUsuarios(List<UsuarioTO> usuarios) {
		this.usuarios = usuarios;
	}

}
