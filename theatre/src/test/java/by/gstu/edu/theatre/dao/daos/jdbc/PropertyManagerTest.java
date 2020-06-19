package by.gstu.edu.theatre.dao.daos.jdbc;

import org.junit.jupiter.api.*;

public class PropertyManagerTest {

    @Test
    void testGetManager() {
        PropertyManager firstManager = PropertyManager.getManager();
        PropertyManager secondManager = PropertyManager.getManager();

        Assertions.assertSame(firstManager, secondManager);
    }

    @Test
    void testGetProperty() {
        PropertyManager propertyManager = PropertyManager.getManager();

        String nullString = propertyManager.getProperty("null_string");
        Assertions.assertNull(nullString);

        String notNullString = propertyManager.getProperty("db.driver");
        Assertions.assertNotNull(notNullString);
    }
}
