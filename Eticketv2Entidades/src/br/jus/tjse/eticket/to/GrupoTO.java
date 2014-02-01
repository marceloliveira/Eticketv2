package br.jus.tjse.eticket.to;

import java.io.Serializable;

public class GrupoTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int cdGrupo;
	private String txNome;
	public int getCdGrupo() {
		return cdGrupo;
	}
	public void setCdGrupo(int cdGrupo) {
		this.cdGrupo = cdGrupo;
	}
	public String getTxNome() {
		return txNome;
	}
	public void setTxNome(String txNome) {
		this.txNome = txNome;
	}
	
	

}
