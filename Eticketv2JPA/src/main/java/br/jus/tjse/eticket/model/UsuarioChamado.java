package br.jus.tjse.eticket.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usuario_chamado database table.
 * 
 */
@Entity
@Table(name="usuario_chamado")
@NamedQuery(name="UsuarioChamado.findAll", query="SELECT u FROM UsuarioChamado u")
public class UsuarioChamado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sq_usuario")
	private Integer sqUsuario;

	@Column(name="tx_email")
	private String txEmail;

	@Column(name="tx_nome")
	private String txNome;

	@Column(name="tx_telefone")
	private String txTelefone;

	//bi-directional many-to-one association to Chamado
	@ManyToOne
	@JoinColumn(name="nr_chamado")
	private Chamado chamado;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="nr_matricula")
	private Usuario usuario;

	public UsuarioChamado() {
	}

	public Integer getSqUsuario() {
		return this.sqUsuario;
	}

	public void setSqUsuario(Integer sqUsuario) {
		this.sqUsuario = sqUsuario;
	}

	public String getTxEmail() {
		return this.txEmail;
	}

	public void setTxEmail(String txEmail) {
		this.txEmail = txEmail;
	}

	public String getTxNome() {
		return this.txNome;
	}

	public void setTxNome(String txNome) {
		this.txNome = txNome;
	}

	public String getTxTelefone() {
		return this.txTelefone;
	}

	public void setTxTelefone(String txTelefone) {
		this.txTelefone = txTelefone;
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