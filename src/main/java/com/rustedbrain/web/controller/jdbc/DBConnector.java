package com.rustedbrain.web.controller.jdbc;

public class DBConnector {

    private static DBConnector ourInstance = new DBConnector();

    private DBConnector() {
    }

    public static DBConnector getInstance() {
        return ourInstance;
    }


}
