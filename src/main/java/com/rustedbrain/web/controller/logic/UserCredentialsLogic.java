package com.rustedbrain.web.controller.logic;

import com.rustedbrain.web.controller.jdbc.PostgresDBConnectorImpl;
import com.rustedbrain.web.controller.jdbc.city.DBCityController;
import com.rustedbrain.web.controller.jdbc.city.DBCityControllerImpl;
import com.rustedbrain.web.controller.jdbc.user.DBUserController;
import com.rustedbrain.web.controller.jdbc.user.DBUserControllerImpl;
import com.rustedbrain.web.controller.jdbc.util.DBUtil;
import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.controller.resource.MessageManager;
import com.rustedbrain.web.controller.resource.SQLManager;
import com.rustedbrain.web.controller.util.jaxb.JaxbParser;
import com.rustedbrain.web.model.jdbc.City;
import com.rustedbrain.web.model.jdbc.User;
import com.rustedbrain.web.model.servlet.ProxyUser;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserCredentialsLogic {

    private static UserCredentialsLogic ourInstance = new UserCredentialsLogic();
    private DBUserController dbUserController =
            new DBUserControllerImpl(
                    SQLManager.getInstance(),
                    PostgresDBConnectorImpl.getPostgresDBConnector(),
                    new DBUtil(ConfigurationManager.getInstance(), MessageManager.getInstance(), SQLManager.getInstance(), new JaxbParser()));

    private DBCityController dbCityController =
            new DBCityControllerImpl(
                    SQLManager.getInstance(),
                    PostgresDBConnectorImpl.getPostgresDBConnector(),
                    new DBUtil(ConfigurationManager.getInstance(), MessageManager.getInstance(), SQLManager.getInstance(), new JaxbParser()));

    private UserCredentialsLogic() {
    }

    public static UserCredentialsLogic getInstance() {
        return ourInstance;
    }

    public boolean isCorrectLoginCredentials(String login, String password) throws SQLException, IllegalArgumentException {
        checkLoginPassword(login, password);

        User user = dbUserController.getUserByLogin(login);
        if (user != null) {
            return user.getPassword().equals(password);
        }
        user = dbUserController.getUserByMail(login);
        if (user != null) {
            return user.getPassword().equals(password);
        }
        throw new IllegalArgumentException("User with this login was not found");

    }

    private void checkLoginPassword(String login, String password) throws IllegalArgumentException {
        if (login.isEmpty()) {
            throw new IllegalArgumentException("Login cannot be empty");
        }
        if (password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
    }

    public void registerUser(String name, String surname, String login, String password, String mail, Date birthday, String cityName) throws SQLException {
        registerUser(name, surname, login, password, mail, birthday, cityName, false);
    }

    public void registerUser(String name, String surname, String login, String password, String mail, Date birthday, Integer cityId) throws SQLException {
        registerUser(name, surname, login, password, mail, birthday, cityId, false);
    }

    public void registerUser(String name, String surname, String login, String password, String mail, Date birthday, Integer cityId, Boolean isAdmin) throws SQLException {
        User user = new User();
        user.setCreationDate(Date.valueOf(LocalDate.now()));
        user.setName(name);
        user.setSurname(surname);
        user.setLogin(login);
        user.setPassword(password);
        user.setBirthday(birthday);
        user.setMail(mail);
        user.setCityId(cityId);
        user.setAdmin(isAdmin);
        dbUserController.insert(user);
    }

    public void registerUser(String name, String surname, String login, String password, String mail, Date birthday, String cityName, Boolean isAdmin) throws SQLException {
        User user = new User();
        user.setCreationDate(Date.valueOf(LocalDate.now()));
        user.setName(name);
        user.setSurname(surname);
        user.setLogin(login);
        user.setPassword(password);
        user.setBirthday(birthday);
        user.setMail(mail);
        City city = dbCityController.getCityByName(cityName);
        if (city == null) {
            createNewCity(cityName);
        }
        user.setCityId(dbCityController.getCityByName(cityName).getId());
        dbUserController.insert(user);
    }

    private void createNewCity(String name) throws SQLException {
        City city = new City();
        city.setCreationDate(Date.valueOf(LocalDate.now()));
        city.setName(name);
        dbCityController.insert(city);
    }

    public List<City> getAllCities() throws SQLException {
        return dbCityController.getAll();
    }

    public ProxyUser getProxyUserByLogin(String login) throws SQLException {
        User user = dbUserController.getUserByLogin(login);
        if (user != null) {
            return createProxyUser(user);
        }
        user = dbUserController.getUserByMail(login);
        if (user != null) {
            return createProxyUser(user);
        }
        throw new IllegalArgumentException("User with this login was not found");
    }

    private ProxyUser createProxyUser(User user) throws SQLException {
        String cityName = dbCityController.getEntityById(user.getCityId()).getName();
        if (cityName != null) {
            return new ProxyUser(user.getId(), user.getCreationDate(), user.getName(), user.getSurname(), user.getLogin(), user.getMail(), user.getBirthday(), user.isAdmin(), cityName);
        } else {
            return new ProxyUser(user.getId(), user.getCreationDate(), user.getName(), user.getSurname(), user.getLogin(), user.getMail(), user.getBirthday(), user.isAdmin(), null);
        }
    }

    private List<ProxyUser> createProxyUsers(List<User> users) throws SQLException {
        List<ProxyUser> proxyUsers = new ArrayList<>();
        for (User user : users) {
            proxyUsers.add(createProxyUser(user));
        }
        return proxyUsers;
    }

    public List<ProxyUser> getAdminProxyUsers() throws SQLException {
        List<User> users = dbUserController.getAdminUsers();
        return createProxyUsers(users);
    }
}
