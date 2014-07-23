package br.jus.tjse.eticket.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.jus.tjse.eticket.model.Grupo;
import br.jus.tjse.eticket.model.Usuario;

public class GrupoDAO {
	
	private static final GrupoDAO INSTANCE = new GrupoDAO();
	
	public static synchronized GrupoDAO getInstance() {
		return INSTANCE;
	}
	
	private GrupoDAO(){}
	
	public List<Grupo> getGrupos() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eticketv2JPA");
		EntityManager em = emf.createEntityManager();
		
		TypedQuery<Grupo> query = em.createNamedQuery("Grupo.findAll",Grupo.class);
		
		List<Grupo> grupos = query.getResultList();
		
		em.close();		
		emf.close();
		
		return grupos;
	}
	
	public Grupo getGrupoByCodigo(int cdGrupo) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eticketv2JPA");
		EntityManager em = emf.createEntityManager();
		
		Grupo g = em.find(Grupo.class, cdGrupo);

		em.close();
		emf.close();
		
		return g;
	}
	
	public void addGrupo(Grupo grupo) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eticketv2JPA");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(grupo);
		em.getTransaction().commit();

		em.close();
		emf.close();
	}
	
	public void updateGrupo(Grupo grupo) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eticketv2JPA");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(grupo);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}
	
	public List<Grupo> pesqGrupo(String termoPesquisa) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eticketv2JPA");
		EntityManager em = emf.createEntityManager();
		
		TypedQuery<Grupo> query = em.createQuery("SELECT g FROM Grupo g where lower(g.txNome)=:nome", Grupo.class);

		List<Grupo> grupos = query.setParameter("nome", termoPesquisa.toLowerCase()).getResultList();

		em.close();		
		emf.close();
		
		return grupos;
	}
	
	public void deleteGrupo(int cdGrupo) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eticketv2JPA");
		EntityManager em = emf.createEntityManager();
		
		Grupo g = em.find(Grupo.class, cdGrupo);
		
		em.getTransaction().begin();
		em.remove(g);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}
	
	public List<Grupo> getGruposByUsuario(int nrMatricula) throws SQLException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eticketv2JPA");
		EntityManager em = emf.createEntityManager();
		
		List<Grupo> grupos = em.find(Usuario.class, nrMatricula).getGrupos();
		
		em.close();		
		emf.close();
		
		return grupos;
	}

	public void addUsuarioGrupo(int nrMatricula, int cdGrupo) throws SQLException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eticketv2JPA");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.find(Grupo.class, cdGrupo).getUsuarios().add(em.find(Usuario.class, nrMatricula));
		em.getTransaction().commit();
		
		em.close();		
		emf.close();
	}

	public void deleteUsuarioGrupo(int nrMatricula, int cdGrupo) throws SQLException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eticketv2JPA");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.find(Grupo.class, cdGrupo).getUsuarios().remove(em.find(Usuario.class, nrMatricula));
		em.getTransaction().commit();
		
		em.close();		
		emf.close();
	}

}
