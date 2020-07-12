package by.gstu.edu.theatre.dao.daos.jdbc;

import by.gstu.edu.theatre.entities.*;
import by.gstu.edu.theatre.services.parsers.json.PlayDatesJsonParser;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class PlayDatesJsonParserTest {
    private static PlayDates playDates = new PlayDates(
            new Play(1, "play_name", "play_description",
                    new Author(1, "author_name"),
                    new Genre(1, "genre_name")),
            Arrays.asList(new Date(1, LocalDate.of(2020, 6, 12), 1),
                    new Date(1, LocalDate.of(2020, 6, 13), 1))
    );

    private String jsonString = "{\n" +
            "  \"play\": {\n" +
            "    \"id\": 1,\n" +
            "    \"name\": \"play_name\",\n" +
            "    \"description\": \"play_description\",\n" +
            "    \"author\": {\n" +
            "      \"id\": 1,\n" +
            "      \"name\": \"author_name\"\n" +
            "    },\n" +
            "    \"genre\": {\n" +
            "      \"id\": 1,\n" +
            "      \"name\": \"genre_name\"\n" +
            "    }\n" +
            "  },\n" +
            "  \"dates\": [\n" +
            "    {\n" +
            "      \"id\": 1,\n" +
            "      \"date\": \"2020-06-12\",\n" +
            "      \"playId\": 1\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 1,\n" +
            "      \"date\": \"2020-06-13\",\n" +
            "      \"playId\": 1\n" +
            "    }\n" +
            "  ]\n" +
            "}";
    @Test
    void testGetJsonString() {

        Assertions.assertEquals(jsonString, new PlayDatesJsonParser().getJsonString(playDates));
    }

    @Test
    void testGetPlayDates() {
        Assertions.assertEquals(playDates, new PlayDatesJsonParser().getPlayDates(jsonString));
    }
}
