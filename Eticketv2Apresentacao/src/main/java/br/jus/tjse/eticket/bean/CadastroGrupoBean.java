package br.jus.tjse.eticket.bean;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.jus.tjse.eticket.bo.GrupoBO;
import br.jus.tjse.eticket.model.Grupo;

@ManagedBean
public class CadastroGrupoBean {
	
	private int cdGrupo;
	
	private Grupo grupo = new Grupo();
	
	private boolean alteracao = false;

	public boolean isAlteracao() {
		return alteracao;
	}

	public void setAlteracao(boolean alteracao) {
		this.alteracao = alteracao;
	}

	public int getCdGrupo()  {
		String cdGrupoTxt = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("cdGrupo");
		if (cdGrupoTxt!=null && !cdGrupoTxt.equals("")){
			try {
				cdGrupo = Integer.parseInt(cdGrupoTxt);
				grupo = GrupoBO.getInstance().getGrupoByCodigo(cdGrupo);
				alteracao = true;
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
		return cdGrupo;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setCdGrupo(int cdGrupo) {
		this.cdGrupo = cdGrupo;
	}

	public void setUsuario(Grupo grupo) {
		this.grupo = grupo;
	}
	
	public String cadastrar(){
		GrupoBO gbo = GrupoBO.getInstance();
		try {
			gbo.cadastrarGrupo(grupo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "listaGrupo";
	}

	

}
