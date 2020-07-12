package by.gstu.edu.theatre.dao.daos.jdbc.daos.implementations;

import by.gstu.edu.theatre.dao.daos.jdbc.PropertyManager;
import by.gstu.edu.theatre.dao.daos.jdbc.daos.interfaces.Dao;
import by.gstu.edu.theatre.entities.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class DateDao implements Dao<Date> {
    private final PropertyManager properties;

    public DateDao() {
        properties = PropertyManager.getManager();
    }

    @Override
    public Optional<List<Date>> getAll() {
        try {
            return Optional.of(GenericDao.getAll(properties.getProperty("query.getAllDates"), this::getFromResultSet));
        }
        catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    @Override
    public <E> Optional<List<Date>> getAll(E key) {
        return Optional.empty();
    }

    @Override
    public Optional<Date> get(long id) {
        try {
            return Optional.of(GenericDao.get(properties.getProperty("query.getDate"), id, this::getFromResultSet));
        }
        catch (IllegalArgumentException e) {
            System.out.println(e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<Long> insert(Date date) {
        try {
            return Optional.of(GenericDao.insert(properties.getProperty("query.insertDate"), date, this::fillPreparedStatement));
        }
        catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Boolean> remove(long id) {
        return Optional.empty();
    }

    private void fillPreparedStatement(Date date, PreparedStatement statement) {
        try {
            statement.setDate(1, java.sql.Date.valueOf(date.getDate()));
            statement.setLong(2, date.getPlayId());
        }
        catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    private Date getFromResultSet(ResultSet resultSet) {
        try {
            long id = resultSet.getLong(1);
            LocalDate date = resultSet.getObject(2, LocalDate.class);
            long playId = resultSet.getLong(3);

            return new Date(id, date, playId);
        }
        catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
