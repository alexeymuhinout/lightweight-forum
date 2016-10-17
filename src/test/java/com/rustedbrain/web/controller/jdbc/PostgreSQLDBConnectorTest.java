package com.rustedbrain.web.controller.jdbc;

import junit.framework.TestCase;
import org.junit.Assert;

import java.sql.Connection;

public class PostgreSQLDBConnectorTest extends TestCase {

    PostgreSQLDBConnector connector;


    @Override
    public void setUp() throws Exception {
        connector = PostgreSQLDBConnector.getInstance();
    }

    public void testConnection() throws Exception {
        Connection connection = connector.getConnection();
        Assert.assertNotNull(connection);
    }
}
