package by.gstu.edu.theatre.dao.daos.jdbc.daos.implementations;

import by.gstu.edu.theatre.dao.daos.jdbc.ConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class GenericDao {
    private static final Logger LOGGER = LogManager.getLogger(GenericDao.class.getName());
    private static final ConnectionManager connectionManager = ConnectionManager.getManager();


    public static <T> List<T> getAll(String query, Function<ResultSet, T> resultSetParser) {
        Connection connection = connectionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                List<T> entities = new ArrayList<>();
                while (resultSet.next()) {
                    entities.add(resultSetParser.apply(resultSet));
                }
                return entities;
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

    public static <T> T get(String query, long id, Function<ResultSet, T> resultSetParser) {
        Connection connection = connectionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                return resultSetParser.apply(resultSet);
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

    public static <T> long insert(String query, T entity, BiConsumer<T, PreparedStatement> statementFiller) {
        Connection connection = connectionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statementFiller.accept(entity, statement);
            statement.executeUpdate();
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

    public static boolean remove(String query, long id) {
        Connection connection = connectionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        }
        catch (SQLException e) {
            LOGGER.error(LOGGER.getName() + ": statement error");
            throw new IllegalArgumentException("error query");
        }
        finally {
            connectionManager.releaseConnection(connection);
        }
    }
}
