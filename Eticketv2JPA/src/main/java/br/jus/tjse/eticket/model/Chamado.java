package br.jus.tjse.eticket.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the chamado database table.
 * 
 */
@Entity
@NamedQuery(name="Chamado.findAll", query="SELECT c FROM Chamado c")
public class Chamado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="nr_chamado")
	private Long nrChamado;

	@Column(name="dh_criacao")
	private Timestamp dhCriacao;

	@Column(name="fl_status")
	private String flStatus;

	@Column(name="tx_descricao")
	private String txDescricao;

	@Column(name="tx_titulo")
	private String txTitulo;

	//bi-directional many-to-one association to Grupo
	@ManyToOne
	@JoinColumn(name="cd_grupo_atual")
	private Grupo grupoAtual;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="nr_matricula_criador")
	private Usuario usuarioCriador;

	//bi-directional many-to-many association to Usuario
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name="responsavel_chamado"
		, joinColumns={
			@JoinColumn(name="nr_chamado")
			}
		, inverseJoinColumns={
			@JoinColumn(name="nr_matricula")
			}
		)
	private List<Usuario> responsaveis;

	//bi-directional many-to-one association to Mensagem
	@OneToMany(fetch = FetchType.EAGER, mappedBy="chamado")
	private List<Mensagem> mensagens;

	//bi-directional many-to-one association to UsuarioChamado
	@OneToMany(fetch = FetchType.EAGER, mappedBy="chamado")
	private List<UsuarioChamado> usuariosChamado;

	public Chamado() {
	}

	public Long getNrChamado() {
		return this.nrChamado;
	}

	public void setNrChamado(Long nrChamado) {
		this.nrChamado = nrChamado;
	}

	public Timestamp getDhCriacao() {
		return this.dhCriacao;
	}

	public void setDhCriacao(Timestamp dhCriacao) {
		this.dhCriacao = dhCriacao;
	}

	public String getFlStatus() {
		return this.flStatus;
	}

	public void setFlStatus(String flStatus) {
		this.flStatus = flStatus;
	}

	public String getTxDescricao() {
		return this.txDescricao;
	}

	public void setTxDescricao(String txDescricao) {
		this.txDescricao = txDescricao;
	}

	public String getTxTitulo() {
		return this.txTitulo;
	}

	public void setTxTitulo(String txTitulo) {
		this.txTitulo = txTitulo;
	}

	public Grupo getGrupoAtual() {
		return this.grupoAtual;
	}

	public void setGrupoAtual(Grupo grupoAtual) {
		this.grupoAtual = grupoAtual;
	}

	public Usuario getUsuarioCriador() {
		return this.usuarioCriador;
	}

	public void setUsuarioCriador(Usuario usuarioCriador) {
		this.usuarioCriador = usuarioCriador;
	}

	public List<Usuario> getResponsaveis() {
		return this.responsaveis;
	}

	public void setResponsaveis(List<Usuario> responsaveis) {
		this.responsaveis = responsaveis;
	}

	public List<Mensagem> getMensagens() {
		return this.mensagens;
	}

	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}

	public Mensagem addMensagen(Mensagem mensagem) {
		getMensagens().add(mensagem);
		mensagem.setChamado(this);

		return mensagem;
	}

	public Mensagem removeMensagen(Mensagem mensagem) {
		getMensagens().remove(mensagem);
		mensagem.setChamado(null);

		return mensagem;
	}

	public List<UsuarioChamado> getUsuariosChamado() {
		return this.usuariosChamado;
	}

	public void setUsuariosChamado(List<UsuarioChamado> usuariosChamado) {
		this.usuariosChamado = usuariosChamado;
	}

	public UsuarioChamado addUsuariosChamado(UsuarioChamado usuariosChamado) {
		getUsuariosChamado().add(usuariosChamado);
		usuariosChamado.setChamado(this);

		return usuariosChamado;
	}

	public UsuarioChamado removeUsuariosChamado(UsuarioChamado usuariosChamado) {
		getUsuariosChamado().remove(usuariosChamado);
		usuariosChamado.setChamado(null);

		return usuariosChamado;
	}

}