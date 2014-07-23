package br.jus.tjse.eticket.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the perfil database table.
 * 
 */
@Entity
@NamedQuery(name="Perfil.findAll", query="SELECT p FROM Perfil p")
public class Perfil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cd_perfil")
	private Integer cdPerfil;

	@Column(name="tx_nome")
	private String txNome;

	//bi-directional many-to-many association to Usuario
	@ManyToMany(mappedBy="perfis")
	private List<Usuario> usuarios;

	public Perfil() {
	}

	public Integer getCdPerfil() {
		return this.cdPerfil;
	}

	public void setCdPerfil(Integer cdPerfil) {
		this.cdPerfil = cdPerfil;
	}

	public String getTxNome() {
		return this.txNome;
	}

	public void setTxNome(String txNome) {
		this.txNome = txNome;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}