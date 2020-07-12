package by.gstu.edu.theatre.dao.daos.jdbc.daos.implementations;

import by.gstu.edu.theatre.dao.daos.jdbc.PropertyManager;
import by.gstu.edu.theatre.dao.daos.jdbc.daos.interfaces.Dao;
import by.gstu.edu.theatre.entities.Role;
import by.gstu.edu.theatre.entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<User> {
    private static final Logger LOGGER = LogManager.getLogger(UserDao.class.getSimpleName());
    private final PropertyManager propertyManager;

    public UserDao() {
        propertyManager = PropertyManager.getManager();
    }

    @Override
    public Optional<List<User>> getAll() {
        try {
            return Optional.of(GenericDao.getAll(propertyManager.getProperty("query.getAllUsers"), this::getFromResultSet));
        }
        catch (IllegalArgumentException e) {
            LOGGER.warn(String.format("%s: couldn't get users from db, cause: %s", LOGGER.getName(), e));
            return Optional.empty();
        }
    }

    @Override
    public <E> Optional<List<User>> getAll(E key) {
        return Optional.empty();
    }

    @Override
    public Optional<User> get(long id) {
        try {
            return Optional.of(GenericDao.get(propertyManager.getProperty("query.getUser"), id, this::getFromResultSet));
        }
        catch (IllegalArgumentException e) {
            LOGGER.warn(String.format("%s: couldn't get a user from db, cause: %s", LOGGER.getName(), e));
            return Optional.empty();
        }
    }

    @Override
    public Optional<Long> insert(User user) {
        try {
            return Optional.of(GenericDao.insert(propertyManager.getProperty("query.insertUser"), user, this::fillPreparedStatement));
        }
        catch (IllegalArgumentException e) {
            LOGGER.warn(String.format("%s: couldn't insert a user into db, cause: %s", LOGGER.getName(), e));
            return Optional.empty();
        }
    }

    @Override
    public Optional<Boolean> remove(long id) {
        try {
            return Optional.of(GenericDao.remove(propertyManager.getProperty("query.removeUser"), id));
        }
        catch (IllegalArgumentException e) {
            LOGGER.warn(String.format("%s: couldn't remove a user from db, cause: %s", LOGGER.getName(), e));
            return Optional.empty();
        }
    }

    private void fillPreparedStatement(User user, PreparedStatement statement) {
        try {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPhone());
            statement.setInt(5, user.getRole().index());
        }
        catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    private User getFromResultSet(ResultSet resultSet) {
        try {
            long id = resultSet.getLong(1);
            String login = resultSet.getString(2);
            String password = resultSet.getString(3);
            String email = resultSet.getString(4);
            String phone = resultSet.getString(5);
            Role role = Role.valueOf(resultSet.getString(6).toUpperCase());

            return new User(id, login, password, email, phone, role);
        }
        catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }
}
