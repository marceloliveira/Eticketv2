package br.jus.tjse.eticket.to;

import java.sql.Timestamp;

public class MensagemTO {

	private long nrChamado;
	private int sqMensagem;
	private Timestamp dhMensagem;
	private UsuarioTO usuario;
	private String txMensagem;

	public long getNrChamado() {
		return nrChamado;
	}

	public void setNrChamado(long nrChamado) {
		this.nrChamado = nrChamado;
	}

	public int getSqMensagem() {
		return sqMensagem;
	}

	public void setSqMensagem(int sqMensagem) {
		this.sqMensagem = sqMensagem;
	}

	public String getDhMensagem() {
		return dhMensagem.toLocaleString();
	}

	public void setDhMensagem(Timestamp dhMensagem) {
		this.dhMensagem = dhMensagem;
	}

	public UsuarioTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioTO usuario) {
		this.usuario = usuario;
	}

	public String getTxMensagem() {
		return txMensagem;
	}

	public void setTxMensagem(String txMensagem) {
		this.txMensagem = txMensagem;
	}

}