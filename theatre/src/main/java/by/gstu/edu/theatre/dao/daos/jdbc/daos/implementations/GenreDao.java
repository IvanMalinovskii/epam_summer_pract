package by.gstu.edu.theatre.dao.daos.jdbc.daos.implementations;

import by.gstu.edu.theatre.dao.daos.jdbc.PropertyManager;
import by.gstu.edu.theatre.dao.daos.jdbc.daos.interfaces.Dao;
import by.gstu.edu.theatre.entities.Genre;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class GenreDao implements Dao<Genre> {
    private static final Logger LOGGER = LogManager.getLogger(GenreDao.class.getName());
    private final PropertyManager properties;

    public GenreDao() {
        properties = PropertyManager.getManager();
    }

    @Override
    public Optional<List<Genre>> getAll() {
        try {
            return Optional.of(GenericDao.getAll(properties.getProperty("query.getAllGenres"), this::getFromResultSet));
        }
        catch (IllegalArgumentException e) {
            LOGGER.warn(String.format("%s: couldn't get genres from db, cause: %s", LOGGER.getName(), e));
            return Optional.empty();
        }
    }

    @Override
    public <E> Optional<List<Genre>> getAll(E key) {
        return Optional.empty();
    }

    @Override
    public Optional<Genre> get(long id) {
        try {
            return Optional.of(GenericDao.get(properties.getProperty("query.getGenre"), 1, this::getFromResultSet));
        }
        catch (IllegalArgumentException e) {
            LOGGER.warn(String.format("%s: couldn't get a genre from db, cause: %s", LOGGER.getName(), e));
            return Optional.empty();
        }
    }

    @Override
    public Optional<Long> insert(Genre genre) {
        try {
            return Optional.of(GenericDao.insert(properties.getProperty("query.insertGenre"), genre, this::fillPreparedStatement));
        }
        catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Boolean> remove(long id) {
        try {
            return Optional.of(GenericDao.remove(properties.getProperty("query.removeGenre"), id));
        }
        catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    private void fillPreparedStatement(Genre genre, PreparedStatement statement) {
        try {
            statement.setLong(1, genre.getId());
            statement.setString(2, genre.getName());
        }
        catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    private Genre getFromResultSet(ResultSet resultSet) {
        try {
            long id = resultSet.getLong(1);
            String name = resultSet.getString(2);
            return new Genre(id, name);
        }
        catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }
}
