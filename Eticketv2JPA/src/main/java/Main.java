import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.jus.tjse.eticket.model.Chamado;
import br.jus.tjse.eticket.model.Usuario;


public class Main {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eticketv2JPA");
		EntityManager em = emf.createEntityManager();
		
		Set<Chamado> c = em.find(Usuario.class, 10089).getChamadosResponsavel();
		
		for (Chamado u:c) {
			System.out.println(u.getNrChamado());
		}
	}

}
