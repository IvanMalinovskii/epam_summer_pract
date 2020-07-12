package by.gstu.edu.theatre.dao.daos.jdbc;

import by.gstu.edu.theatre.dao.daos.jdbc.daos.implementations.UserDao;
import by.gstu.edu.theatre.services.UserService;
import by.gstu.edu.theatre.services.parsers.json.UserJsonParser;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.*;

public class UserServiceTest {
    private static final String userJson = "{email = \"mail\", password = \"password\"}";
    private static final String userToSignUp = "{id = -1, login = \"login1\", email = \"mail1\", password = \"password1\", role = \"USER\"}";
    @Test
    void testDoSignIn() {
        JsonParser parser = new JsonParser();
        System.out.println(parser.parse(userJson).getAsJsonObject());
        JsonObject object = new JsonObject();
        object.add("user", parser.parse(userJson).getAsJsonObject());
        object.addProperty("status", "ok");

        System.out.println(object);
    }

}
