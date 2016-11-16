package com.rustedbrain.web.controller.jdbc.category;

import com.rustedbrain.web.controller.jdbc.DBConnector;
import org.h2.tools.DeleteDbFiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestDBConnector implements DBConnector {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:~/test";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    public TestDBConnector() {
        DeleteDbFiles.execute("~", "test", true);
    }

    public void createTable(String sql) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement createPreparedStatement = null;
        try {
            connection.setAutoCommit(false);
            createPreparedStatement = connection.prepareStatement(sql);
            createPreparedStatement.executeUpdate();
            createPreparedStatement.close();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,
                    DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
