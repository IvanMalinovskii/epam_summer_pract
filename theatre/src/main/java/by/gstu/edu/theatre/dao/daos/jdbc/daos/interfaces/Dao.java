package by.gstu.edu.theatre.dao.daos.jdbc.daos.interfaces;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Optional<List<T>> getAll();

    <E> Optional<List<T>> getAll(E key);

    Optional<T> get(long id);

    Optional<Long> insert(T entity);

    Optional<Boolean> remove(long id);
}
