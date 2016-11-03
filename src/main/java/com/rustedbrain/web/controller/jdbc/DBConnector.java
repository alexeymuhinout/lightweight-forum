package com.rustedbrain.web.controller.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBConnector {

    Connection getConnection() throws SQLException;
}
