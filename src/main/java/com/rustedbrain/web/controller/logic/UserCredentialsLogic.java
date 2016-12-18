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
import com.rustedbrain.web.model.servlet.user.PreviewUser;
import com.rustedbrain.web.model.servlet.user.ProxyUser;

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
            checkBlockedUser(user);
            return user.getPassword().equals(password);
        }
        user = dbUserController.getUserByMail(login);
        if (user != null) {
            checkBlockedUser(user);
            return user.getPassword().equals(password);
        }
        throw new IllegalArgumentException(MessageManager.getInstance().getProperty("login.not.success"));
    }

    private void checkBlockedUser(User user) {
        if (user.getBlockUntilDate() != null && !Date.valueOf(LocalDate.now()).after(user.getBlockUntilDate())) {
            String message = MessageManager.getInstance().getProperty("login.not.success.blocked");
            String formattedMessage = message
                    .replace("%1", user.getLogin())
                    .replace("%2", user.getBlockUntilDate().toString())
                    .replace("%3", user.getBlockReason());
            throw new IllegalArgumentException(formattedMessage);
        }
    }

    private void checkLoginPassword(String login, String password) throws IllegalArgumentException {
        if (login.isEmpty()) {
            throw new IllegalArgumentException("Login cannot be empty");
        }
        if (password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
    }

    public void registerUser(String name, String surname, String login, String password, String mail, Date birthday, Integer cityId, String userAdminToken) throws SQLException {
        User user = new User();
        user.setCreationDate(Date.valueOf(LocalDate.now()));
        user.setName(name);
        user.setSurname(surname);
        user.setLogin(login);
        user.setPassword(password);
        user.setBirthday(birthday);
        user.setMail(mail);
        user.setCityId(cityId);
        user.setAdmin(isValidAdminToken(userAdminToken));
        dbUserController.insert(user);
    }

    private boolean isValidAdminToken(String userAdminToken) {
        String adminToken = ConfigurationManager.getInstance().getProperty("user.admin.token");
        return adminToken.equals(userAdminToken.trim());
    }

    public void registerUser(String name, String surname, String login, String password, String mail, Date birthday, String cityName, String userAdminToken) throws SQLException {
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
        user.setAdmin(isValidAdminToken(userAdminToken));
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
        throw new IllegalArgumentException("User with this credentials was not found");
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

    public void deleteUser(Integer userId) throws SQLException {
        User user = this.dbUserController.getEntityById(userId);
        this.dbUserController.delete(user);
    }

    public void updateUserCredentials(Integer userId, String name, String surname, String login, String mail, Date birthday, Integer cityId) throws SQLException, CloneNotSupportedException {
        User oldUser = this.dbUserController.getEntityById(userId);
        User newUser = oldUser.clone();
        newUser.setName(name);
        newUser.setSurname(surname);
        newUser.setLogin(login);
        newUser.setMail(mail);
        newUser.setBirthday(birthday);
        newUser.setCityId(cityId);

        this.dbUserController.update(oldUser, newUser);
    }

    public void updateUserCredentials(Integer userId, String name, String surname, String login, String mail, Date birthday, String cityName) throws SQLException, CloneNotSupportedException {
        City city = dbCityController.getCityByName(cityName);
        if (city == null) {
            createNewCity(cityName);
        }
        this.updateUserCredentials(userId, name, surname, login, mail, birthday, dbCityController.getCityByName(cityName).getId());
    }

    public PreviewUser getPreviewUser(String userName) throws SQLException {
        User user = this.dbUserController.getUserByLogin(userName);

        PreviewUser previewUser = new PreviewUser();
        previewUser.setName(user.getName());
        previewUser.setSurname(user.getSurname());
        previewUser.setLogin(user.getLogin());
        previewUser.setBirthday(user.getBirthday());
        previewUser.setMail(user.getMail());
        previewUser.setCity(this.dbCityController.getEntityById(user.getCityId()).getName());
        previewUser.setBirthday(user.getBirthday());
        previewUser.setRegistrationDate(user.getCreationDate());

        return previewUser;
    }

    public void unblockUser(String login) throws SQLException, CloneNotSupportedException {
        User oldUser = dbUserController.getUserByLogin(login);
        User newUser = oldUser.clone();
        newUser.setBlockUntilDate(null);
        dbUserController.update(oldUser, newUser);
    }

    public void blockUser(String login, String blockReason, Date blockUntilDate) throws SQLException, CloneNotSupportedException {
        User oldUser = dbUserController.getUserByLogin(login);
        User newUser = oldUser.clone();
        newUser.setBlockReason(blockReason);
        newUser.setBlockUntilDate(blockUntilDate);
        dbUserController.update(oldUser, newUser);
    }
}
