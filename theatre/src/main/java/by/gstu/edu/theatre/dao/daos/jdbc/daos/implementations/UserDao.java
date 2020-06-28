package by.gstu.edu.theatre.dao.daos.jdbc.daos.implementations;

import by.gstu.edu.theatre.dao.daos.jdbc.ConnectionManager;
import by.gstu.edu.theatre.dao.daos.jdbc.PropertyManager;
import by.gstu.edu.theatre.dao.daos.jdbc.daos.interfaces.GetAddDao;
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

public class UserDao implements GetAddDao<User> {
    private static final Logger LOGGER = LogManager.getLogger(UserDao.class.getSimpleName());
    private final ConnectionManager connectionManager;
    private final PropertyManager propertyManager;

    public UserDao() {
        connectionManager = ConnectionManager.getManager();
        propertyManager = PropertyManager.getManager();
    }

    @Override
    public List<User> getAll() {
        Connection connection = connectionManager.getConnection();
        String query = propertyManager.getProperty("query.getAllUsers");
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                List<User> users = new ArrayList<>();
                while (resultSet.next()) {
                    users.add(getFromResultSet(resultSet));
                }
                return users;
            }
            catch (SQLException e) {
                LOGGER.error(LOGGER.getName() + ": result set error");
                throw new IllegalArgumentException("error parsing state");
            }
        }
        catch (SQLException e) {
            LOGGER.error(LOGGER.getName() + ": statement error");
            throw new IllegalArgumentException("error query");
        }
        finally {
            connectionManager.releaseConnection(connection);
        }
    }

    @Override
    public User getById(long id) {
        Connection connection = connectionManager.getConnection();
        String query = propertyManager.getProperty("query.getUser");
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                return getFromResultSet(resultSet);
            }
            catch (SQLException e) {
                LOGGER.error(LOGGER.getName() + ": result set error");
                throw new IllegalArgumentException("error parsing state");
            }
        }
        catch (SQLException e) {
            LOGGER.error(LOGGER.getName() + ": statement error");
            throw new IllegalArgumentException("error query");
        }
        finally {
            connectionManager.releaseConnection(connection);
        }
    }

    @Override
    public long add(User user) {
        Connection connection = connectionManager.getConnection();
        String query = propertyManager.getProperty("query.insertUser");
        try (PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPhone());
            statement.setInt(5, user.getRole().index());
            statement.executeLargeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                resultSet.next();
                return resultSet.getLong(1);
            }
            catch (SQLException e) {
                LOGGER.error(LOGGER.getName() + ": result set error");
                throw new IllegalArgumentException("error parsing state");
            }
        }
        catch (SQLException e) {
            LOGGER.error(LOGGER.getName() + ": statement error");
            throw new IllegalArgumentException("error query");
        }
        finally {
            connectionManager.releaseConnection(connection);
        }
    }

    private User getFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(1);
        String login = resultSet.getString(2);
        String password = resultSet.getString(3);
        String email = resultSet.getString(4);
        String phone = resultSet.getString(5);
        Role role = Role.valueOf(resultSet.getString(6).toUpperCase());

        return new User(id, login, password, email, phone, role);
    }
}
