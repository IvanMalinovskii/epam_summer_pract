package by.gstu.edu.theatre.dao.daos.jdbc.daos.interfaces;

import java.util.List;

public interface GetAddDao<T> {
    List<T> getAll();
    T getById(long id);
    long add(T entity);
}
