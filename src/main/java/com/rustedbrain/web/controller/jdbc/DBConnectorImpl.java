package com.rustedbrain.web.controller.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectorImpl implements DBConnector {

    private Properties properties;
    private Connection connection;

    public DBConnectorImpl(Properties properties) {
        this.properties = properties;
    }

    public DBConnectorImpl(String host, String user, String password) {
        this.properties = new Properties();
        this.properties.put("url", host);
        this.properties.put("user", user);
        this.properties.put("password", password);
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (this.connection == null || this.connection.isClosed()) {
            this.connection = DriverManager.getConnection(properties.getProperty("url"), properties);
        }
        return this.connection;
    }
}
