package br.jus.tjse.eticket.to;

import java.io.Serializable;


public class UsuarioTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int nrMatricula;
	private String txNome;
	private String txTelefone;
	private String txEmail;
	public int getNrMatricula() {
		return nrMatricula;
	}
	public void setNrMatricula(int nrMatricula) {
		this.nrMatricula = nrMatricula;
	}
	public String getTxNome() {
		return txNome;
	}
	public void setTxNome(String txNome) {
		this.txNome = txNome;
	}
	public String getTxTelefone() {
		return txTelefone;
	}
	public void setTxTelefone(String txTelefone) {
		this.txTelefone = txTelefone;
	}
	public String getTxEmail() {
		return txEmail;
	}
	public void setTxEmail(String txEmail) {
		this.txEmail = txEmail;
	}

}
