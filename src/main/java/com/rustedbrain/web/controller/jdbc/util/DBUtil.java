package com.rustedbrain.web.controller.jdbc.util;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

    public boolean tableExist(String tableName, Connection connection) throws SQLException {
        String sql = "SELECT " + tableName + " FROM information_schema.tables WHERE table_schema = 'public'";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        boolean isExist = resultSet.isBeforeFirst();

        statement.close();
        resultSet.close();

        return isExist;
    }

    public void createTable(String tableName) {

    }

}
