package com.rustedbrain.web.controller.jdbc;

import junit.framework.TestCase;
import org.junit.Assert;

import java.sql.Connection;

public class PostgresDBConnectorImplTest extends TestCase {

    PostgresDBConnectorImpl connector;

    @Override
    public void setUp() throws Exception {
        connector = new PostgresDBConnectorImpl("jdbc:postgresql://127.0.0.1:5432/lightweight-forum-test", "postgres", "postgres");
    }

    public void testConnection() throws Exception {
        Connection connection = connector.getConnection();
        Assert.assertNotNull(connection);
    }
}
