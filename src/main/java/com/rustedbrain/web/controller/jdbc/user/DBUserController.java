package com.rustedbrain.web.controller.jdbc.user;

import com.rustedbrain.web.controller.jdbc.DBController;
import com.rustedbrain.web.model.jdbc.User;

import java.sql.SQLException;

public abstract class DBUserController extends DBController<User> {

    abstract User getUserByLogin(String login) throws SQLException;
}
