package br.jus.tjse.eticket.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the mensagem database table.
 * 
 */
@Entity
@NamedQuery(name="Mensagem.findAll", query="SELECT m FROM Mensagem m")
public class Mensagem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sq_mensagem")
	private Integer sqMensagem;

	@Column(name="dh_mensagem")
	private Timestamp dhMensagem;

	@Column(name="tx_mensagem")
	private String txMensagem;

	//bi-directional many-to-one association to Chamado
	@ManyToOne
	@JoinColumn(name="nr_chamado")
	private Chamado chamado;

	//uni-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="nr_matricula")
	private Usuario usuario;

	public Mensagem() {
	}

	public Integer getSqMensagem() {
		return this.sqMensagem;
	}

	public void setSqMensagem(Integer sqMensagem) {
		this.sqMensagem = sqMensagem;
	}

	public Timestamp getDhMensagem() {
		return this.dhMensagem;
	}

	public void setDhMensagem(Timestamp dhMensagem) {
		this.dhMensagem = dhMensagem;
	}

	public String getTxMensagem() {
		return this.txMensagem;
	}

	public void setTxMensagem(String txMensagem) {
		this.txMensagem = txMensagem;
	}

	public Chamado getChamado() {
		return this.chamado;
	}

	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}