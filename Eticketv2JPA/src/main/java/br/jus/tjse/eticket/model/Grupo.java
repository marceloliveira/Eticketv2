package br.jus.tjse.eticket.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the grupo database table.
 * 
 */
@Entity
@NamedQuery(name="Grupo.findAll", query="SELECT g FROM Grupo g")
public class Grupo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cd_grupo")
	private Integer cdGrupo;

	@Column(name="id_cor_fundo")
	private String idCorFundo;

	@Column(name="id_cor_letra")
	private String idCorLetra;

	@Column(name="tx_nome")
	private String txNome;

	@Column(name="tx_nome_abreviado")
	private String txNomeAbreviado;

	//bi-directional many-to-one association to Chamado
	@OneToMany(mappedBy="grupoAtual")
	private List<Chamado> chamados;

	//bi-directional many-to-many association to Usuario
	@ManyToMany
	@JoinTable(
		name="usuario_grupo"
		, joinColumns={
			@JoinColumn(name="cd_grupo")
			}
		, inverseJoinColumns={
			@JoinColumn(name="nr_matricula")
			}
		)
	private List<Usuario> usuarios;

	public Grupo() {
	}

	public Integer getCdGrupo() {
		return this.cdGrupo;
	}

	public void setCdGrupo(Integer cdGrupo) {
		this.cdGrupo = cdGrupo;
	}

	public String getIdCorFundo() {
		return this.idCorFundo;
	}

	public void setIdCorFundo(String idCorFundo) {
		this.idCorFundo = idCorFundo;
	}

	public String getIdCorLetra() {
		return this.idCorLetra;
	}

	public void setIdCorLetra(String idCorLetra) {
		this.idCorLetra = idCorLetra;
	}

	public String getTxNome() {
		return this.txNome;
	}

	public void setTxNome(String txNome) {
		this.txNome = txNome;
	}

	public String getTxNomeAbreviado() {
		return this.txNomeAbreviado;
	}

	public void setTxNomeAbreviado(String txNomeAbreviado) {
		this.txNomeAbreviado = txNomeAbreviado;
	}

	public List<Chamado> getChamados() {
		return this.chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}

	public Chamado addChamado(Chamado chamado) {
		getChamados().add(chamado);
		chamado.setGrupoAtual(this);

		return chamado;
	}

	public Chamado removeChamado(Chamado chamado) {
		getChamados().remove(chamado);
		chamado.setGrupoAtual(null);

		return chamado;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}