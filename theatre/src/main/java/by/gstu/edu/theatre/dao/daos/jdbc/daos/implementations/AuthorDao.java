package by.gstu.edu.theatre.dao.daos.jdbc.daos.implementations;

import by.gstu.edu.theatre.dao.daos.jdbc.PropertyManager;
import by.gstu.edu.theatre.dao.daos.jdbc.daos.interfaces.Dao;
import by.gstu.edu.theatre.entities.Author;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AuthorDao implements Dao<Author> {
    private static final Logger LOGGER = LogManager.getLogger(AuthorDao.class.getSimpleName());
    private final PropertyManager propertyManager;

    public AuthorDao() {
        propertyManager = PropertyManager.getManager();
    }

    @Override
    public Optional<List<Author>> getAll() {
        try {
            return Optional.of(GenericDao.getAll(propertyManager.getProperty("query.getAll"), this::getFormResultSet));
        }
        catch (IllegalArgumentException e) {
            LOGGER.warn(String.format("%s: couldn't get authors from db, cause: %s", LOGGER.getName(), e));
            return Optional.empty();
        }
    }

    @Override
    public <E> Optional<List<Author>> getAll(E key) {
        return Optional.empty();
    }

    @Override
    public Optional<Author> get(long id) {
        try {
            return Optional.of(GenericDao.get(propertyManager.getProperty("query.getAuthor"), id, this::getFormResultSet));
        }
        catch (IllegalArgumentException e) {
            LOGGER.warn(String.format("%s: couldn't get an author from db, cause: %s", LOGGER.getName(), e));
            return Optional.empty();
        }
    }

    @Override
    public Optional<Long> insert(Author entity) {
        try {
            return Optional.of(GenericDao.insert(propertyManager.getProperty("query.insertAuthor"), entity, this::fillPreparedStatement));
        }
        catch (IllegalArgumentException e) {
            LOGGER.warn(String.format("%s: couldn't insert an author into db, cause: %s", LOGGER.getName(), e));
            return Optional.empty();
        }
    }

    @Override
    public Optional<Boolean> remove(long id) {
        try {
            return Optional.of(GenericDao.remove(propertyManager.getProperty("query.removeAuthor"), id));
        }
        catch (IllegalArgumentException e) {
            LOGGER.warn(String.format("%s: couldn't remove an author from db, cause: %s", LOGGER.getName(), e));
            return Optional.empty();
        }
    }

    private void fillPreparedStatement(Author author, PreparedStatement statement) {
        try {
            statement.setString(1, author.getName());
        }
        catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    private Author getFormResultSet(ResultSet resultSet) {
        try {
            long id = resultSet.getLong(1);
            String name = resultSet.getString(2);

            return new Author(id, name);
        }
        catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }
}
