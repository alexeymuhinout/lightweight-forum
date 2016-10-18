package com.rustedbrain.web.controller.jdbc;

import com.rustedbrain.web.controller.resource.ConfigurationManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLDBConnector implements DBConnector {

    private static PostgreSQLDBConnector instance;
    private Connection connection;

    private PostgreSQLDBConnector() throws SQLException {
        String url = ConfigurationManager.getInstance().getProperty("database.url");
        String user = ConfigurationManager.getInstance().getProperty("database.user");
        String password = ConfigurationManager.getInstance().getProperty("database.password");
        this.connection = DriverManager.getConnection(url, user, password);
    }

    public static synchronized PostgreSQLDBConnector getInstance() {
        if (instance == null) {
            try {
                instance = new PostgreSQLDBConnector();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    @Override
    public Connection getConnection() {
        return this.connection;
    }
}
