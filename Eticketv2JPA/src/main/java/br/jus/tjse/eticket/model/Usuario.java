package br.jus.tjse.eticket.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="nr_matricula")
	private Integer nrMatricula;

	@Column(name="tx_email")
	private String txEmail;

	@Column(name="tx_login")
	private String txLogin;

	@Column(name="tx_nome")
	private String txNome;

	@Column(name="tx_senha")
	private String txSenha;

	@Column(name="tx_telefone")
	private String txTelefone;

	//bi-directional many-to-one association to Chamado
	@OneToMany(mappedBy="usuarioCriador", fetch=FetchType.EAGER)
	private Set<Chamado> chamadosCriados;

	//bi-directional many-to-many association to Chamado
	@ManyToMany(mappedBy="responsaveis", fetch=FetchType.EAGER)
	private Set<Chamado> chamadosResponsavel;

	//bi-directional many-to-many association to Grupo
	@ManyToMany(mappedBy="usuarios", fetch=FetchType.EAGER)
	private Set<Grupo> grupos;

	//bi-directional many-to-one association to UsuarioChamado
	@OneToMany(mappedBy="usuario", fetch=FetchType.EAGER)
	private Set<UsuarioChamado> usuariosChamado;

	public Usuario() {
	}

	public Integer getNrMatricula() {
		return this.nrMatricula;
	}

	public void setNrMatricula(Integer nrMatricula) {
		this.nrMatricula = nrMatricula;
	}

	public String getTxEmail() {
		return this.txEmail;
	}

	public void setTxEmail(String txEmail) {
		this.txEmail = txEmail;
	}

	public String getTxLogin() {
		return this.txLogin;
	}

	public void setTxLogin(String txLogin) {
		this.txLogin = txLogin;
	}

	public String getTxNome() {
		return this.txNome;
	}

	public void setTxNome(String txNome) {
		this.txNome = txNome;
	}

	public String getTxSenha() {
		return this.txSenha;
	}

	public void setTxSenha(String txSenha) {
		this.txSenha = txSenha;
	}

	public String getTxTelefone() {
		return this.txTelefone;
	}

	public void setTxTelefone(String txTelefone) {
		this.txTelefone = txTelefone;
	}

	public Set<Chamado> getChamadosCriados() {
		return this.chamadosCriados;
	}

	public void setChamadosCriados(Set<Chamado> chamadosCriados) {
		this.chamadosCriados = chamadosCriados;
	}

	public Chamado addChamadosCriado(Chamado chamadosCriado) {
		getChamadosCriados().add(chamadosCriado);
		chamadosCriado.setUsuarioCriador(this);

		return chamadosCriado;
	}

	public Chamado removeChamadosCriado(Chamado chamadosCriado) {
		getChamadosCriados().remove(chamadosCriado);
		chamadosCriado.setUsuarioCriador(null);

		return chamadosCriado;
	}

	public Set<Chamado> getChamadosResponsavel() {
		return this.chamadosResponsavel;
	}

	public void setChamadosResponsavel(Set<Chamado> chamadosResponsavel) {
		this.chamadosResponsavel = chamadosResponsavel;
	}

	public Set<Grupo> getGrupos() {
		return this.grupos;
	}

	public void setGrupos(Set<Grupo> grupos) {
		this.grupos = grupos;
	}

	public Set<UsuarioChamado> getUsuariosChamado() {
		return this.usuariosChamado;
	}

	public void setUsuariosChamado(Set<UsuarioChamado> usuariosChamado) {
		this.usuariosChamado = usuariosChamado;
	}

	public UsuarioChamado addUsuariosChamado(UsuarioChamado usuariosChamado) {
		getUsuariosChamado().add(usuariosChamado);
		usuariosChamado.setUsuario(this);

		return usuariosChamado;
	}

	public UsuarioChamado removeUsuariosChamado(UsuarioChamado usuariosChamado) {
		getUsuariosChamado().remove(usuariosChamado);
		usuariosChamado.setUsuario(null);

		return usuariosChamado;
	}
	
	public Set<String> getRoles() {
		Set<String> roles = new HashSet<String>();
		for (Grupo g:getGrupos()) {
			roles.add(g.getCdGrupo().toString());
		}
		return roles;
	}

}