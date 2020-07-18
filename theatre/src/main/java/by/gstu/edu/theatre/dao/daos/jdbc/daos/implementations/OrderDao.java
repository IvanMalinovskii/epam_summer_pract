package by.gstu.edu.theatre.dao.daos.jdbc.daos.implementations;

import by.gstu.edu.theatre.dao.daos.jdbc.PropertyManager;
import by.gstu.edu.theatre.dao.daos.jdbc.daos.interfaces.Dao;
import by.gstu.edu.theatre.entities.Date;
import by.gstu.edu.theatre.entities.Order;
import by.gstu.edu.theatre.entities.categories.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class OrderDao implements Dao<Order> {
    private final PropertyManager properties;

    public OrderDao() {
        properties = PropertyManager.getManager();
    }

    @Override
    public Optional<List<Order>> getAll() {
        try {
            return Optional.of(GenericDao.getAll(properties.getProperty("query.getAllOrders"), this::getFromResultSet));
        }
        catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    @Override
    public <E> Optional<List<Order>> getAll(E key) {
        return Optional.empty();
    }

    @Override
    public Optional<Order> get(long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Long> insert(Order order) {
        try {
            return Optional.of(GenericDao.insert(properties.getProperty("query.insertOrder"), order, this::fillPreparedStatement));
        }
        catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Boolean> remove(long id) {
        return Optional.empty();
    }

    private void fillPreparedStatement(Order order, PreparedStatement statement) {
        try {
            statement.setInt(1, order.getCategory().index());
            statement.setInt(2, order.getQuantity());
            statement.setLong(3, order.getDate());
            statement.setLong(4, order.getUser());
        }
        catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private Order getFromResultSet(ResultSet resultSet) {
        try {
            long id = resultSet.getLong("order_id");
            Category.CategoryType category = Category.CategoryType.valueOf(resultSet.getString("category"));
            long dateId = resultSet.getLong("date_id");
            long userId = resultSet.getLong("user_id");
            int quantity = resultSet.getInt("quantity");

            return new Order(id, category, quantity, dateId, userId);
        }
        catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
