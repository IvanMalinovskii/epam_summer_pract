package by.gstu.edu.theatre.dao.daos.jdbc;

import org.junit.jupiter.api.*;

import java.sql.Connection;

public class ConnectionManagerTest {

    @Test
    void testGetManager() {
        ConnectionManager firstManager = ConnectionManager.getManager();
        ConnectionManager secondManager = ConnectionManager.getManager();

        Assertions.assertSame(firstManager, secondManager);
    }

    @Test
    void testPullCreating() {
        ConnectionManager connectionManager = ConnectionManager.getManager();
        int pullSize = connectionManager.getConnectionsCount();
        int expect = Integer.parseInt(PropertyManager.getManager().getProperty("db.pool"));

        Assertions.assertEquals(expect, pullSize);
    }

    @Test
    void testGetReleaseConnection() {
        ConnectionManager connectionManager = ConnectionManager.getManager();
        Connection connection = connectionManager.getConnection();
        int pullSize = connectionManager.getConnectionsCount();
        int expect = Integer.parseInt(PropertyManager.getManager().getProperty("db.pool")) - 1;

        Assertions.assertNotNull(connection);
        Assertions.assertEquals(expect, pullSize);

        connectionManager.releaseConnection(connection);
        pullSize = connectionManager.getConnectionsCount();
        expect += 1;

        Assertions.assertEquals(expect, pullSize);
    }
}
