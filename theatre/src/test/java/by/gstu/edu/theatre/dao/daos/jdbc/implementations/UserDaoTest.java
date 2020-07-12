package by.gstu.edu.theatre.dao.daos.jdbc.implementations;

import by.gstu.edu.theatre.dao.daos.jdbc.daos.implementations.UserDao;
import by.gstu.edu.theatre.entities.Role;
import by.gstu.edu.theatre.entities.User;
import org.junit.jupiter.api.*;

import java.util.Collections;

public class UserDaoTest {
    private static final UserDao dao = new UserDao();
    private static final User user = new User("usLog", "usMail", "usPass", "usPhone", Role.USER);

    private static long lastIndex;

    @BeforeAll
    static void insertUser() {
        lastIndex = dao.insert(user).orElse(-1L);
    }

    @AfterAll
    static void deleteUser() {
        dao.remove(lastIndex);
    }

    @Test
    void testGetAll() {
        boolean isThere = dao.getAll().orElse(Collections.emptyList()).stream()
                .anyMatch(u -> u.equals(user));

        Assertions.assertTrue(isThere);
    }

    @Test
    void testGetById() {
        Assertions.assertEquals(user, dao.get(lastIndex).orElse(null));
    }
}
