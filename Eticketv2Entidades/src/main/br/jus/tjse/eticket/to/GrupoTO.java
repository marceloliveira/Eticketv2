package br.jus.tjse.eticket.to;

import java.io.Serializable;

public class GrupoTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int cdGrupo;
	private String txNome;
	private String idCorFundo;
	private String idCorLetra;
	private String txNomeAbreviado;
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
	public String getIdCorFundo() {
		return idCorFundo;
	}
	public void setIdCorFundo(String idCorFundo) {
		this.idCorFundo = idCorFundo;
	}
	public String getIdCorLetra() {
		return idCorLetra;
	}
	public void setIdCorLetra(String idCorLetra) {
		this.idCorLetra = idCorLetra;
	}
	public String getTxNomeAbreviado() {
		return txNomeAbreviado;
	}
	public void setTxNomeAbreviado(String txNomeAbreviado) {
		this.txNomeAbreviado = txNomeAbreviado;
	}
	
	

}
