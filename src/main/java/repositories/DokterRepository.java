package repositories;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.persistence.TransactionRequiredException;
import domein.Dokter;
import domein.Patient;
import exceptions.DatabankException;

public class DokterRepository {

	private static final String persistence_unit_name = "plaskalender";
	private static final String persistence_unit_name_LOCAL = "plaskalender";
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistence_unit_name);
	public static final EntityManager em = emf.createEntityManager();



	public static void closePersistency() throws IllegalStateException {
		em.close();
		emf.close();
	}

	public static void startTransaction() throws IllegalStateException {
		em.getTransaction().begin();
	}

	public static void commitTransaction() throws IllegalStateException, RollbackException {
		em.getTransaction().commit();
	}

	public static void rollbackTransaction() throws IllegalStateException, PersistenceException {
		em.getTransaction().rollback();
	}
	

	public void create(Dokter dokter) throws EntityExistsException, IllegalArgumentException, TransactionRequiredException{
		em.persist(dokter);
	}
	
	public List<Dokter> findAll(Dokter t) throws IllegalStateException, RollbackException{
		List<Dokter> list =em.createQuery(String.format("Select e From %s e",t.getClass().getSimpleName())).getResultList();
		return list; 
	}

    public Dokter get(String id) throws IllegalArgumentException{
        Dokter entity = em.find(Dokter.class, id);
        return entity;
    }

	public Dokter update(Dokter object) throws IllegalArgumentException, TransactionRequiredException {
		 return em.merge(object);
	}

	public void delete(Patient object) throws IllegalArgumentException, TransactionRequiredException {
		 em.remove(em.merge(object));
	}

	public boolean exists(String id) throws IllegalArgumentException {
		Dokter entity = em.find(Dokter.class, id);
        return entity != null;
	}

	public List<Dokter> findAll(String soort) {
		startTransaction();
		List<Dokter> list =em.createQuery(String.format("Select e From %s e",soort)).getResultList();
		commitTransaction();
		return list;
	}
	
	public boolean checkGebruiker(String gebruikersnaam) throws DatabankException {
		try {
			startTransaction();
			Query query = em.createNamedQuery("find Dokter by gebruikersnaam");
			query.setParameter("gebruikersnaam", gebruikersnaam);
			if(query.getResultList().isEmpty()){
				return false;	
			}
			return true;
		} catch (RollbackException | IllegalStateException e) {
			rollbackTransaction();
			throw new DatabankException("GeenWerknemer");
		}
		finally {
			if(em.getTransaction().isActive()) {
				commitTransaction();	
			}	
		}
		
		
	}

	public String geefHash(String Gebruikersnaam) throws DatabankException {
		try {
			startTransaction();
			Query query = em.createNamedQuery("find Dokter by gebruikersnaam");
			query.setParameter("gebruikersnaam", Gebruikersnaam);
			Object object = query.getSingleResult();			
			if(!(object instanceof String)) {	
				throw new IllegalArgumentException("Hash is geen String");
			}
			return (String) object;
		}catch(Exception e) {
			rollbackTransaction();
			throw new DatabankException("GeenWerknemer");
		}
		finally {
			if(em.getTransaction().isActive()) {
				commitTransaction();	
			}	
		}
		
	}
}
