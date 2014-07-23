package br.jus.tjse.eticket.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.jus.tjse.eticket.model.Chamado;
import br.jus.tjse.eticket.model.Usuario;

public class ChamadoDAO {
	
	private static final ChamadoDAO INSTANCE = new ChamadoDAO();
	
	public static synchronized ChamadoDAO getInstance() {
		return INSTANCE;
	}
	
	private ChamadoDAO(){
		
	}
	
	public List<Chamado> getChamados() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eticketv2JPA");
		EntityManager em = emf.createEntityManager();
		
		TypedQuery<Chamado> query = em.createNamedQuery("Chamado.findAll",Chamado.class);
		
		List<Chamado> chamados = query.getResultList();
		
		em.close();		
		emf.close();
		
		return chamados;
	}
	
	public Chamado getChamadoByNrChamado(long nrChamado) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eticketv2JPA");
		EntityManager em = emf.createEntityManager();
		
		Chamado chamado = em.find(Chamado.class, nrChamado);
		
		em.close();		
		emf.close();
		
		return chamado;
	}

	public void addChamado(Chamado chamado) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eticketv2JPA");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(chamado);
		em.getTransaction().commit();
		
		em.close();		
		emf.close();
	}
	
	public void updateChamado(Chamado chamado) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eticketv2JPA");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(chamado);
		em.getTransaction().commit();
		
		em.close();		
		emf.close();
	}
	
	public List<Chamado> getChamadosByResponsavel(int nrMatricula) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eticketv2JPA");
		EntityManager em = emf.createEntityManager();
		
		List<Chamado> chamados = em.find(Usuario.class, nrMatricula).getChamadosResponsavel();
		
		em.close();		
		emf.close();
		
		return chamados;
	}
	
	public void addResponsavelChamado(int nrMatricula, long nrChamado) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eticketv2JPA");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.find(Chamado.class, nrChamado).getResponsaveis().add(em.find(Usuario.class, nrMatricula));
		em.getTransaction().commit();
		
		em.close();		
		emf.close();
	}

	public void removeResponsavelChamado(int nrMatricula, long nrChamado) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eticketv2JPA");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.find(Chamado.class, nrChamado).getResponsaveis().remove(em.find(Usuario.class, nrMatricula));
		em.getTransaction().commit();
		
		em.close();		
		emf.close();
	}

}
