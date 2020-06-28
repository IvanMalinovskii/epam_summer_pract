package by.gstu.edu.theatre.dao.daos.jdbc.daos.implementations;

import by.gstu.edu.theatre.dao.daos.jdbc.ConnectionManager;
import by.gstu.edu.theatre.dao.daos.jdbc.PropertyManager;
import by.gstu.edu.theatre.dao.daos.jdbc.daos.interfaces.Dao;
import by.gstu.edu.theatre.entities.Author;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao implements Dao<Author> {
    private static final Logger LOGGER = LogManager.getLogger(AuthorDao.class.getSimpleName());
    private final PropertyManager propertyManager;

    public AuthorDao() {
        propertyManager = PropertyManager.getManager();
    }

    @Override
    public List<Author> getAll() {
        return GenericDao.getAll(propertyManager.getProperty("query.getAll"), this::getFormResultSet);
    }

    @Override
    public Author get(long id) {
        return GenericDao.get(propertyManager.getProperty("query.getAuthor"), id, this::getFormResultSet);
    }

    @Override
    public long insert(Author entity) {
        return GenericDao.insert(propertyManager.getProperty("query.insertAuthor"), entity, this::fillPreparedStatement);
    }

    @Override
    public boolean remove(long id) {
        return GenericDao.remove(propertyManager.getProperty("query.removeAuthor"), id);
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
