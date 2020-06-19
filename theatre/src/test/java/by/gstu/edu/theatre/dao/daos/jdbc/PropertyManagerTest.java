package by.gstu.edu.theatre.dao.daos.jdbc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

public class PropertyManagerTest {

    @Test
    void testGetManager() {
        PropertyManager firstManager = PropertyManager.getManager();
        PropertyManager secondManager = PropertyManager.getManager();

        Assertions.assertSame(firstManager, secondManager);
    }
}
