package br.jus.tjse.eticket.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


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
	@OneToMany(mappedBy="usuarioCriador")
	private List<Chamado> chamadosCriados;

	//bi-directional many-to-many association to Chamado
	@ManyToMany(mappedBy="responsaveis")
	private List<Chamado> chamadosResponsavel;

	//bi-directional many-to-many association to Grupo
	@ManyToMany(mappedBy="usuarios")
	private List<Grupo> grupos;

	//bi-directional many-to-many association to Perfil
	@ManyToMany
	@JoinTable(
		name="usuario_perfil"
		, joinColumns={
			@JoinColumn(name="tx_login", referencedColumnName="tx_login")
			}
		, inverseJoinColumns={
			@JoinColumn(name="cd_perfil")
			}
		)
	private List<Perfil> perfis;

	//bi-directional many-to-one association to UsuarioChamado
	@OneToMany(mappedBy="usuario")
	private List<UsuarioChamado> usuariosChamado;

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

	public List<Chamado> getChamadosCriados() {
		return this.chamadosCriados;
	}

	public void setChamadosCriados(List<Chamado> chamadosCriados) {
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

	public List<Chamado> getChamadosResponsavel() {
		return this.chamadosResponsavel;
	}

	public void setChamadosResponsavel(List<Chamado> chamadosResponsavel) {
		this.chamadosResponsavel = chamadosResponsavel;
	}

	public List<Grupo> getGrupos() {
		return this.grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public List<Perfil> getPerfis() {
		return this.perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public List<UsuarioChamado> getUsuariosChamado() {
		return this.usuariosChamado;
	}

	public void setUsuariosChamado(List<UsuarioChamado> usuariosChamado) {
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

}