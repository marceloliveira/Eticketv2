package br.jus.tjse.eticket.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.jus.tjse.eticket.model.Chamado;
import br.jus.tjse.eticket.model.Grupo;
import br.jus.tjse.eticket.model.Usuario;

public class UsuarioDAO {
	
	private static final UsuarioDAO INSTANCE = new UsuarioDAO();
	
	public static synchronized UsuarioDAO getInstance() {
		return INSTANCE;
	}
	
	private UsuarioDAO(){}
	
	public List<Usuario> getUsuarios() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eticketv2JPA");
		EntityManager em = emf.createEntityManager();
		
		TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findAll",Usuario.class);
		
		List<Usuario> usuarios = query.getResultList();
		
		em.close();		
		emf.close();
		
		return usuarios;
	}

	public List<Usuario> pesqUsuario(String termoPesquisa) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eticketv2JPA");
		EntityManager em = emf.createEntityManager();
		
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u where u.nrMatricula like :mat or lower(u.txNome) like :nome or u.txTelefone like :tel or lower(u.txEmail) like :email", Usuario.class);
		query.setParameter("mat", "%"+termoPesquisa.toLowerCase()+"%");
		query.setParameter("nome", "%"+termoPesquisa.toLowerCase()+"%");
		query.setParameter("tel", "%"+termoPesquisa.toLowerCase()+"%");
		query.setParameter("email", "%"+termoPesquisa.toLowerCase()+"%");
		List<Usuario> usuarios = query.getResultList();

		em.close();		
		emf.close();
		
		return usuarios;
	}

	public Usuario getUsuarioByMatricula(int nrMatricula) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eticketv2JPA");
		EntityManager em = emf.createEntityManager();
		
		Usuario usuario = em.find(Usuario.class, nrMatricula);
		
		em.close();		
		emf.close();
		
		return usuario;
	}

	public Usuario getUsuarioByLogin(String txLogin) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eticketv2JPA");
		EntityManager em = emf.createEntityManager();
		
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u where u.txLogin=:log", Usuario.class);
		query.setParameter("log", txLogin);
		Usuario usuario = query.getSingleResult();
		
		em.close();		
		emf.close();
		
		return usuario;
	}

	public void addUsuario(Usuario usuario) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eticketv2JPA");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		
		em.close();		
		emf.close();
	}

	public void updateUsuario(Usuario usuario) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eticketv2JPA");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		
		em.close();		
		emf.close();
	}

	public void deleteUsuario(int nrMatricula) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eticketv2JPA");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.remove(nrMatricula);
		em.getTransaction().commit();
		
		em.close();		
		emf.close();
	}
	
	public List<Usuario> getResponsaveisByChamado(long nrChamado) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eticketv2JPA");
		EntityManager em = emf.createEntityManager();
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.addAll(em.find(Chamado.class, nrChamado).getResponsaveis());
		
		em.close();		
		emf.close();
		
		return usuarios;
	}
	
	public List<Usuario> getUsuariosByGrupo(int cdGrupo) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eticketv2JPA");
		EntityManager em = emf.createEntityManager();
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		Grupo g = em.find(Grupo.class, cdGrupo);
		if (g != null)
			usuarios.addAll(g.getUsuarios());
		
		em.close();		
		emf.close();
		
		return usuarios;
	}

}
