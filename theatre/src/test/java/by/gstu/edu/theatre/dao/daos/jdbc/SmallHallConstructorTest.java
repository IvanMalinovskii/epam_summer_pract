package by.gstu.edu.theatre.dao.daos.jdbc;

import by.gstu.edu.theatre.entities.categories.Category;
import by.gstu.edu.theatre.entities.categories.HallRepository;
import by.gstu.edu.theatre.entities.categories.SmallHallConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SmallHallConstructorTest {

    @Test
    void testSmallHallConstructor() {
        SmallHallConstructor constructor = new SmallHallConstructor();
        List<Category> categories = constructor.create();

        Assertions.assertEquals(2, categories.size());
        Assertions.assertEquals(29, categories.get(0).getSeats().size());
        Assertions.assertEquals(27, categories.get(1).getSeats().size());
    }

    @Test
    void testSth() {
        System.out.println(HallRepository.getInstance().getHallByDateId(2));
    }
}
