package repositories;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import javax.persistence.TransactionRequiredException;

public class GenericRepo<T> implements GenericCRUD<T> {
	private static final String persistence_unit_name = "ID317380_ticketing";
	private static final String persistence_unit_name_LOCAL = "id317380_ticketing";
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistence_unit_name);
	public static final EntityManager em = emf.createEntityManager();
	private final Class<T> type;

	public GenericRepo(Class<T> type) {
		this.type = type;
	}

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
	

	public void create(T object) throws EntityExistsException, IllegalArgumentException, TransactionRequiredException{
		em.persist(object);
	}
	
	public List<T> findAll(T t) throws IllegalStateException, RollbackException{
		List<T> list =em.createQuery(String.format("Select e From %s e",t.getClass().getSimpleName())).getResultList();
		return list; 
	}

    public <U> T get(U id) throws IllegalArgumentException{
        T entity = em.find(type, id);
        return entity;
    }

	public T update(T object) throws IllegalArgumentException, TransactionRequiredException {
		 return em.merge(object);
	}

	public void delete(T object) throws IllegalArgumentException, TransactionRequiredException {
		 em.remove(em.merge(object));
	}

	public <U> boolean exists(U id) throws IllegalArgumentException {
		T entity = em.find(type, id);
        return entity != null;
	}

	public List<T> findAll(String soort) {
		startTransaction();
		List<T> list =em.createQuery(String.format("Select e From %s e",soort)).getResultList();
		commitTransaction();
		return list;
	}




}
