package by.gstu.edu.theatre.services;

import by.gstu.edu.theatre.dao.daos.jdbc.daos.interfaces.Dao;
import by.gstu.edu.theatre.entities.User;
import by.gstu.edu.theatre.services.parsers.json.UserJsonParser;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserService implements by.gstu.edu.theatre.services.interfaces.UserService {
    private Dao<User> userDao;
    private UserJsonParser parser;

    public UserService(Dao<User> userDao, UserJsonParser parser) {
        this.userDao = userDao;
        this.parser = parser;
    }

    @Override
    public Optional<String> doSignIn(String jsonString) {
        User userToSignIn = parser.getUser(jsonString);
        List<User> users = userDao.getAll().orElse(Collections.emptyList());

        Optional<User> signedIn = getSignedIn(userToSignIn.getEmail(), userToSignIn.getPassword(), users);

        return signedIn.map(user -> parser.getJsonString(user));
    }

    @Override
    public Optional<String> doSignUp(String jsonString) {
        User userToSignUp = parser.getUser(jsonString);
        System.out.println(userToSignUp);
        List<User> users = userDao.getAll().orElse(Collections.emptyList());
        if (isMailValid(userToSignUp.getEmail(), users)) {
            userDao.insert(userToSignUp);
            return Optional.of(jsonString);
        }
        return Optional.empty();
    }

    private boolean isMailValid(String email, List<User> users) {
        return users.stream()
                .noneMatch(user -> user.getEmail().equals(email));
    }

    private Optional<User> getSignedIn(String email, String password, List<User> users) {
        return users.stream()
                .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password))
                .findAny();
    }
}
