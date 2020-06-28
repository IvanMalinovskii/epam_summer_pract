package by.gstu.edu.theatre.dao.daos.jdbc.implementations;

import by.gstu.edu.theatre.dao.daos.jdbc.daos.implementations.UserDao;
import by.gstu.edu.theatre.entities.Role;
import by.gstu.edu.theatre.entities.User;
import org.junit.jupiter.api.*;

public class UserDaoTest {
    UserDao dao = new UserDao();

    @Test
    void testGetAll() {
        //System.out.println(dao.getById(1));
        User user = new User("log1", "pass1", "email1", "+3333", Role.ADMIN);

        System.out.println(dao.add(user));
        System.out.println("----");
        System.out.println(dao.getAll());
    }
}
