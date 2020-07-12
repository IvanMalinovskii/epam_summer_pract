package by.gstu.edu.theatre.dao.daos.jdbc.implementations;

import by.gstu.edu.theatre.dao.daos.jdbc.daos.implementations.DateDao;
import by.gstu.edu.theatre.entities.Date;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.ZoneId;

public class DateDaoTest {

    @Test
    void testGetDate() {
        //DateDao dateDao = new DateDao();
        System.out.println(LocalDate.now(ZoneId.of("Europe/Minsk")));
        //System.out.println(dateDao.get(2));
        //System.out.println(dateDao.insert(new Date(LocalDate.now(), 1)));
    }
}
