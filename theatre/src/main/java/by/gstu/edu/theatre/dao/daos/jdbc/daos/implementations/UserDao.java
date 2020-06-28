package by.gstu.edu.theatre.dao.daos.jdbc.daos.implementations;

import by.gstu.edu.theatre.dao.daos.jdbc.ConnectionManager;
import by.gstu.edu.theatre.dao.daos.jdbc.PropertyManager;
import by.gstu.edu.theatre.dao.daos.jdbc.daos.interfaces.Dao;
import by.gstu.edu.theatre.entities.Role;
import by.gstu.edu.theatre.entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements Dao<User> {
    private static final Logger LOGGER = LogManager.getLogger(UserDao.class.getSimpleName());
    private final PropertyManager propertyManager;

    public UserDao() {
        propertyManager = PropertyManager.getManager();
    }

    @Override
    public List<User> getAll() {
        return GenericDao.getAll(propertyManager.getProperty("query.getAllUsers"), this::getFromResultSet);
    }

    @Override
    public User get(long id) {
        return GenericDao.get(propertyManager.getProperty("query.getUser"), id, this::getFromResultSet);
    }

    @Override
    public long insert(User user) {
        return GenericDao.insert(propertyManager.getProperty("query.insertUser"), user, this::fillPreparedStatement);
    }

    @Override
    public boolean remove(long id) {
        return GenericDao.remove(propertyManager.getProperty("query.removeUser"), id);
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
