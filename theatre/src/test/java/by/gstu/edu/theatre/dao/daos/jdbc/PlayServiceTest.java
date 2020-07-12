package by.gstu.edu.theatre.dao.daos.jdbc;

import by.gstu.edu.theatre.dao.daos.jdbc.daos.implementations.DateDao;
import by.gstu.edu.theatre.dao.daos.jdbc.daos.implementations.PlayDao;
import by.gstu.edu.theatre.services.PlayService;
import by.gstu.edu.theatre.services.parsers.json.PlayDatesJsonParser;
import org.junit.jupiter.api.*;

public class PlayServiceTest {
    @Test
    void testDoService() {
        System.out.println(new PlayService(new PlayDao(), new DateDao(), new PlayDatesJsonParser()).getAllPlays());
    }
}
