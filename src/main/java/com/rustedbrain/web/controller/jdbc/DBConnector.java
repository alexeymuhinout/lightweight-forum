package com.rustedbrain.web.controller.jdbc;

import java.sql.Connection;

public interface DBConnector {

    Connection getConnection();
}
