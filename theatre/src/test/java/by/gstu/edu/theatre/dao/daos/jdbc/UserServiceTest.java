package by.gstu.edu.theatre.dao.daos.jdbc;

import by.gstu.edu.theatre.dao.daos.jdbc.daos.implementations.UserDao;
import by.gstu.edu.theatre.services.UserService;
import by.gstu.edu.theatre.services.parsers.json.UserJsonParser;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;

import java.util.Optional;

public class UserServiceTest {
    private static final String userJson = "{email: \"mail\", password: \"password\"}";
    private static final String userToSignUp = "{id = -1, login = \"login1\", email = \"mail1\", password = \"password1\", phone = \"+375264432\", role = \"USER\"}";

    private static final UserService userService = new UserService(new UserDao(), new UserJsonParser());

    @Test
    void testDoSignIn() {
        Optional<String> jsonUser = userService.doSignIn(userJson);
        Assertions.assertTrue(jsonUser.isPresent());

        Optional<String> jsonEmpty = userService.doSignIn("{email: \"ml\", password: \"pass\"}");
        Assertions.assertFalse(jsonEmpty.isPresent());
    }

    @Test
    void testDoSignUp() {
        Optional<String> user = userService.doSignUp(userToSignUp);

        Assertions.assertTrue(user.isPresent());

        new UserDao().remove(new UserJsonParser().getUser(user.get()).getId());
    }

}
