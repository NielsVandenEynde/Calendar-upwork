package repositories;

import java.util.List;

public interface GenericCRUD<T> {
	public void create(T object);
	public List<T> findAll(String soort);
    public <U> T get(U id);
    public T update(T object);
    public void delete(T object);
    public <U> boolean exists(U id);
    public List<T> findAll(T t);
}
