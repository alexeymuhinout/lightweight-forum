package com.rustedbrain.web.controller.jdbc.user;


import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.model.User;
import junit.framework.TestCase;
import org.junit.Assert;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

public class DBUserControllerImplTest extends TestCase {

    private String url = ConfigurationManager.getInstance().getProperty("database.url");
    private String dbUser = ConfigurationManager.getInstance().getProperty("database.user");
    private String dbPassword = ConfigurationManager.getInstance().getProperty("database.password");
    private Connection connection;
    private DBUserControllerImpl dbUserController;
    private User user;

    @Override
    public void setUp() throws Exception {
        this.connection = DriverManager.getConnection(url, dbUser, dbPassword);
        dbUserController = new DBUserControllerImpl();
        user = new User();
        user.setId(0);
        user.setName("John");
        user.setSurname("Coleman");
        user.setLogin("Johny");
        user.setPassword("jing$bing");
        user.setMail("jhncmn@gmail.com");
        user.setBirthday(Date.valueOf(LocalDate.now()));
        user.setCityId(0);
    }

    public void testInsertUser() throws Exception {
        dbUserController.insert(user);
    }

    public void testSelectUser() throws Exception {
        dbUserController.insert(user);
        User user = dbUserController.getUserByLogin(this.user.getLogin());
        Assert.assertNotNull(user);
    }

    public void testSelectUsers() throws Exception {
        dbUserController.insertUsers(user, user);
        List<User> users = dbUserController.getAllEntities();
        Assert.assertEquals(users.size(), 2);
    }

    public void testUpdateUser() throws Exception {
        User oldUser = this.user;
        User newUser = oldUser;

    }

    public void testDeleteUser() throws Exception {
        dbUserController.insert(user);
        User user = dbUserController.getUserByLogin(this.user.getLogin());
        dbUserController.delete(user);
        user = dbUserController.getUserByLogin(this.user.getLogin());
        Assert.assertNull(user);
    }

    @Override
    public void tearDown() throws Exception {
        String sqlDeleteAllUsers = "DELETE FROM \"user\" WHERE \"user\".name = '" + this.user.getName() + "'" +
                " AND \"user\".surname = '" + user.getSurname() + "'" +
                " AND \"user\".login = '" + user.getLogin() + "';";
        Statement statement = this.connection.createStatement();
        statement.executeUpdate(sqlDeleteAllUsers);
        statement.close();
        this.connection.close();
    }
}
