package repositories;

import javax.persistence.Query;
import javax.persistence.RollbackException;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.QueryTimeoutException;
import javax.persistence.TransactionRequiredException;

import domein.Dokter;
import domein.Patient;
import exceptions.DatabankException;

public class PatientRepository {



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
	

	public void create(Patient object) throws EntityExistsException, IllegalArgumentException, TransactionRequiredException{
		em.persist(object);
	}
	
	public List<Patient> findAll(Patient t) throws IllegalStateException, RollbackException{
		List<T> list =em.createQuery(String.format("Select e From %s e",t.getClass().getSimpleName())).getResultList();
		return list; 
	}

    public Patient get(String id) throws IllegalArgumentException{
        T entity = em.find(Patient.class, id)
        return entity;
    }

	public Patient update(Patient object) throws IllegalArgumentException, TransactionRequiredException {
		 return em.merge(object);
	}

	public void delete(Patient object) throws IllegalArgumentException, TransactionRequiredException {
		 em.remove(em.merge(object));
	}

	public boolean exists(String id) throws IllegalArgumentException {
		Patient entity = em.find(Patient.class, id);
        return entity != null;
	}

	public List<T> findAll(String soort) {
		startTransaction();
		List<T> list =em.createQuery(String.format("Select e From %s e",soort)).getResultList();
		commitTransaction();
		return list;
	}

	public boolean checkGebruiker(String gebruikersnaam) throws DatabankException {
		try {
			startTransaction();
			Query query = em.createNamedQuery("find Patient by gebruikersnaam");
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
	public List<Patient>getPatientenVanDokter(String dokter) {
		startTransaction();
		Query q= em.createQuery("Select p from Patient p where p.dokter.gebruikersnaam like :dok");
		q.setParameter("dok", dokter);
		List<Patient> lijst = q.getResultList();
		commitTransaction();
		return lijst;
	}
}
