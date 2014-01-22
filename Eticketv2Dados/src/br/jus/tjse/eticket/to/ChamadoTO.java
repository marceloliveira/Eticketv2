package br.jus.tjse.eticket.to;

import java.sql.Timestamp;

public class ChamadoTO {
	
	private long nrChamado;
	private String txTitulo;
	private String txDescricao;
	private Timestamp dhCriacao;
	private char flStatus;
	private int cdGrupoAtual;
	private int nrMatriculaCriador;
	public long getNrChamado() {
		return nrChamado;
	}
	public void setNrChamado(long nrChamado) {
		this.nrChamado = nrChamado;
	}
	public String getTxTitulo() {
		return txTitulo;
	}
	public void setTxTitulo(String txTitulo) {
		this.txTitulo = txTitulo;
	}
	public String getTxDescricao() {
		return txDescricao;
	}
	public void setTxDescricao(String txDescricao) {
		this.txDescricao = txDescricao;
	}
	public Timestamp getDhCriacao() {
		return dhCriacao;
	}
	public void setDhCriacao(Timestamp dhCriacao) {
		this.dhCriacao = dhCriacao;
	}
	public char getFlStatus() {
		return flStatus;
	}
	public void setFlStatus(char flStatus) {
		this.flStatus = flStatus;
	}
	public int getCdGrupoAtual() {
		return cdGrupoAtual;
	}
	public void setCdGrupoAtual(int cdGrupoAtual) {
		this.cdGrupoAtual = cdGrupoAtual;
	}
	public int getNrMatriculaCriador() {
		return nrMatriculaCriador;
	}
	public void setNrMatriculaCriador(int nrMatriculaCriador) {
		this.nrMatriculaCriador = nrMatriculaCriador;
	}

}
