package by.gstu.edu.theatre.dao.daos.jdbc.daos.implementations;

import by.gstu.edu.theatre.dao.daos.jdbc.PropertyManager;
import by.gstu.edu.theatre.dao.daos.jdbc.daos.interfaces.Dao;
import by.gstu.edu.theatre.entities.Author;
import by.gstu.edu.theatre.entities.Genre;
import by.gstu.edu.theatre.entities.Play;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PlayDao implements Dao<Play> {
    private static final Logger LOGGER = LogManager.getLogger(PlayDao.class.getName());
    private final PropertyManager properties = PropertyManager.getManager();

    @Override
    public Optional<List<Play>> getAll() {
        try {
            return Optional.of(GenericDao.getAll(properties.getProperty("query.getAllPlays"), this::getFromResultSet));
        }
        catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    @Override
    public <E> Optional<List<Play>> getAll(E key) {
        return Optional.empty();
    }

    @Override
    public Optional<Play> get(long id) {
        try {
            return Optional.of(GenericDao.get(properties.getProperty("query.getPlay"), id, this::getFromResultSet));
        }
        catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Long> insert(Play play) {
        try {
            return Optional.of(GenericDao.insert(properties.getProperty("query.insertPlay"), play, this::fillPreparedStatement));
        }
        catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Boolean> remove(long id) {
        try {
            return Optional.of(GenericDao.remove(properties.getProperty("query.removePlay"), id));
        }
        catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    private void fillPreparedStatement(Play play, PreparedStatement statement) {
        try {
            statement.setLong(1, play.getId());
            statement.setString(2, play.getName());
            statement.setString(3, play.getDescription());
            statement.setLong(4, play.getAuthor().getId());
            statement.setLong(5, play.getGenre().getId());
        }
        catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    private Play getFromResultSet(ResultSet resultSet) {
        try {
            long id = resultSet.getLong("play_id");
            String name = resultSet.getString("play_name");
            String description = resultSet.getString("play_description");
            long authorId = resultSet.getLong("author_id");
            String authorName = resultSet.getString("author_name");
            long genreId = resultSet.getLong("genre_id");
            String genreName = resultSet.getString("genre_name");
            return new Play(id, name, description, new Author(authorId, authorName), new Genre(genreId, genreName));
        }
        catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }
}
