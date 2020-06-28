package by.gstu.edu.theatre.dao.daos.jdbc.daos.interfaces;

import java.util.List;

public interface Dao<T> {
    List<T> getAll();
    T get(long id);
    long insert(T entity);
    boolean remove(long id);
}
