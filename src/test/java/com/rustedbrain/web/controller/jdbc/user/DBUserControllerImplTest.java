package com.rustedbrain.web.controller.jdbc.user;


import com.rustedbrain.web.controller.jdbc.DBConnectorImpl;
import com.rustedbrain.web.controller.jdbc.util.DBUtil;
import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.controller.resource.MessageManager;
import com.rustedbrain.web.controller.resource.SQLManager;
import com.rustedbrain.web.controller.util.jaxb.JaxbParser;
import com.rustedbrain.web.controller.util.jaxb.Parser;
import com.rustedbrain.web.model.jdbc.User;
import junit.framework.TestCase;
import org.junit.Assert;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class DBUserControllerImplTest extends TestCase {

    private Connection connection;
    private DBUserController dbUserController;
    private User user1;
    private User user2;

    @Override
    public void setUp() throws Exception {
        String url = "jdbc:postgresql://127.0.0.1:5432/lightweight-forum-test";
        String dbUser = "postgres";
        String dbPassword = "postgres";
        Parser parser = new JaxbParser();
        DBUtil dbUtil = new DBUtil(ConfigurationManager.getInstance(), MessageManager.getInstance(), SQLManager.getInstance(), parser);
        this.dbUserController = new DBUserControllerImpl(ConfigurationManager.getInstance(), new DBConnectorImpl(url, dbUser, dbPassword), dbUtil);
        this.user1 = createTestUser(null, "John", "Coleman", "Johny", "jing$bing", "jhncmn@gmail.com", Date.valueOf(LocalDate.now()), 0);
        this.user2 = createTestUser(null, "Jack", "Jerido", "likant", "234lilian", "kolgun@gmail.com", Date.valueOf(LocalDate.now()), 1);
    }

    private User createTestUser(Integer id, String name, String surname, String login, String password, String mail, Date birthday, int cityId) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setSurname(surname);
        user.setLogin(login);
        user.setPassword(password);
        user.setMail(mail);
        user.setBirthday(birthday);
        user.setCityId(cityId);
        return user;
    }

    public void testInsertUser() throws Exception {
        dbUserController.insert(user1);
        Assert.assertEquals(dbUserController.getEntityById(user1.getId()), this.user1);
    }

    public void testGetUser() throws Exception {
        dbUserController.insert(user1);
        User user = dbUserController.getEntityById(user1.getId());
        Assert.assertEquals(user1, user);
    }

    public void testSelectUser() throws Exception {
        dbUserController.insert(user1);
        User user = dbUserController.getUserByLogin(this.user1.getLogin());
        Assert.assertNotNull(user);
    }

    public void testSelectUsers() throws Exception {
        dbUserController.insertAll(Arrays.asList(user1, user2));
        List<User> users = dbUserController.getAll();
        Assert.assertEquals(users.size(), 2);
    }

    public void testUpdateUser() throws Exception {
        User oldUser = this.user1;
        dbUserController.insert(oldUser);

        User newUser = oldUser.clone();
        String newLogin = "jefry";
        newUser.setLogin(newLogin);

        dbUserController.update(oldUser, newUser);

        User updatedUser = dbUserController.getUserByLogin(newLogin);
        Assert.assertNotEquals(oldUser, updatedUser);
    }

    public void testDeleteUser() throws Exception {
        dbUserController.insert(user1);
        User user = dbUserController.getUserByLogin(this.user1.getLogin());
        dbUserController.delete(user);
        user = dbUserController.getUserByLogin(this.user1.getLogin());
        Assert.assertNull(user);
    }

    @Override
    public void tearDown() throws Exception {
        String sqlDeleteAllUsers = "DELETE FROM \"user\";";
        Statement statement = this.connection.createStatement();
        statement.executeUpdate(sqlDeleteAllUsers);
        statement.close();
        this.connection.close();
    }
}
