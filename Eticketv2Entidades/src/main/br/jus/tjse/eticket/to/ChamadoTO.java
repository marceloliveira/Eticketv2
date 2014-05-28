package br.jus.tjse.eticket.to;

import java.sql.Timestamp;
import java.util.List;

public class ChamadoTO {
	
	private long nrChamado;
	private String txTitulo;
	private String txDescricao;
	private Timestamp dhCriacao;
	private char flStatus;
	private GrupoTO grupoAtual;
	private UsuarioTO usuarioCriador;
	private List<UsuarioTO> responsaveis;
	private List<UsuarioTO> afetados;
	private List<MensagemTO> mensagens;
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
	public String getDhCriacao() {
		return dhCriacao.toLocaleString();
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
	public GrupoTO getGrupoAtual() {
		return grupoAtual;
	}
	public void setGrupoAtual(GrupoTO grupoAtual) {
		this.grupoAtual = grupoAtual;
	}
	public UsuarioTO getUsuarioCriador() {
		return usuarioCriador;
	}
	public void setUsuarioCriador(UsuarioTO usuarioCriador) {
		this.usuarioCriador = usuarioCriador;
	}
	public List<UsuarioTO> getResponsaveis() {
		return responsaveis;
	}
	public void setResponsaveis(List<UsuarioTO> responsaveis) {
		this.responsaveis = responsaveis;
	}
	public List<UsuarioTO> getAfetados() {
		return afetados;
	}
	public void setAfetados(List<UsuarioTO> afetados) {
		this.afetados = afetados;
	}
	
	public String getStyleClasse() {
		String status = "";
			switch (flStatus) {
			case 'A': status = "success"; break;
			case 'F': status = "danger"; break;
			default: status = ""; break;
			}
		return status;
	}
	public List<MensagemTO> getMensagens() {
		return mensagens;
	}
	public void setMensagens(List<MensagemTO> mensagens) {
		this.mensagens = mensagens;
	}

}
