package com.rustedbrain.web.controller.jdbc;

import com.rustedbrain.web.controller.resource.ConfigurationManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDBConnectorImpl implements DBConnector {

    private String host;
    private String user;
    private String password;
    private Connection connection;

    public PostgresDBConnectorImpl(String host, String user, String password) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        this.host = host;
        this.user = user;
        this.password = password;
    }

    public static PostgresDBConnectorImpl getPostgresDBConnector() {
        try {
            return new PostgresDBConnectorImpl(ConfigurationManager.getInstance().getProperty("database.postgres.url"), ConfigurationManager.getInstance().getProperty("database.postgres.user"), ConfigurationManager.getInstance().getProperty("database.postgres.password"));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (this.connection == null || this.connection.isClosed()) {
            this.connection = DriverManager.getConnection(host, user, password);
        }
        return this.connection;
    }
}
