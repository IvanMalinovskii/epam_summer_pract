package by.gstu.edu.theatre.services.parsers.json;

import by.gstu.edu.theatre.entities.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UserJsonParser {
    private Gson parser;

    public UserJsonParser() {
        parser = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    public String getJsonString(User user) {
        return parser.toJson(user);
    }

    public User getUser(String jsonString) {
        return parser.fromJson(jsonString, User.class);
    }
}
