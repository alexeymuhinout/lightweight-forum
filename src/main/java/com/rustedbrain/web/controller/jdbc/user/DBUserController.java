package com.rustedbrain.web.controller.jdbc.user;

import com.rustedbrain.web.controller.jdbc.DBController;
import com.rustedbrain.web.model.jdbc.User;

import java.sql.SQLException;
import java.util.List;

public abstract class DBUserController extends DBController<User> {

    public abstract User getUserByLogin(String login) throws SQLException;

    public abstract User getUserByMail(String mail) throws SQLException;

    public abstract List<User> getAdminUsers() throws SQLException;
}
