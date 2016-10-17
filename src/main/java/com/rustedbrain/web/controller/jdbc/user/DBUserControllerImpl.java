package com.rustedbrain.web.controller.jdbc.user;

import com.rustedbrain.web.controller.jdbc.PostgreSQLDBConnector;
import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUserControllerImpl implements DBUserController {

    private ConfigurationManager configurationManager = ConfigurationManager.getInstance();
    private PostgreSQLDBConnector dbConnector = PostgreSQLDBConnector.getInstance();

    public void insertUsers(User... users) throws SQLException {
        String sqlInsert = configurationManager.getProperty("database.sql.insert.users");
        Connection connection = dbConnector.getConnection();
        PreparedStatement insertStatement = null;

        try {
            connection.setAutoCommit(false);
            insertStatement = connection.prepareStatement(sqlInsert);
            for (User user : users) {
                fillInsertStatement(user, insertStatement);
                insertStatement.executeUpdate();
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            if (insertStatement != null) {
                insertStatement.close();
            }
            connection.setAutoCommit(true);
        }
    }

    private void fillInsertStatement(User user, PreparedStatement insertStatement) throws SQLException {
        insertStatement.setString(1, user.getName());
        insertStatement.setString(2, user.getSurname());
        insertStatement.setString(3, user.getLogin());
        insertStatement.setString(4, user.getPassword());
        insertStatement.setString(5, user.getMail());
        insertStatement.setDate(6, user.getBirthday());
        insertStatement.setInt(7, user.getCityId());
    }

    @Override
    public void insert(User entity) throws SQLException {
        this.insertUsers(entity);
    }

    public void update(User oldEntity, User newEntity) throws SQLException {
        String sqlUpdate = configurationManager.getProperty("database.sql.update.users");
        Connection connection = dbConnector.getConnection();
        PreparedStatement updateStatement = null;

        try {
            connection.setAutoCommit(false);
            updateStatement = connection.prepareStatement(sqlUpdate);
            fillUpdateStatement(oldEntity, newEntity, updateStatement);
            updateStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            if (updateStatement != null) {
                updateStatement.close();
            }
            connection.setAutoCommit(true);
        }
    }

    private void fillUpdateStatement(User oldUser, User newUser, PreparedStatement statement) throws SQLException {
        statement.setString(1, newUser.getName());
        statement.setString(2, newUser.getSurname());
        statement.setString(3, newUser.getLogin());
        statement.setString(4, newUser.getPassword());
        statement.setString(5, newUser.getMail());
        statement.setDate(6, newUser.getBirthday());
        statement.setInt(5, newUser.getCityId());
        statement.setInt(5, oldUser.getId());
    }

    public void delete(User entity) throws SQLException {
        this.deleteUsers(entity);
    }

    private void deleteUsers(User... users) throws SQLException {
        String sqlDelete = configurationManager.getProperty("database.sql.delete.users");
        Connection connection = dbConnector.getConnection();
        PreparedStatement deleteStatement = null;

        try {
            connection.setAutoCommit(false);
            deleteStatement = connection.prepareStatement(sqlDelete);
            for (User user : users) {
                fillDeleteStatement(user, deleteStatement);
                deleteStatement.executeUpdate();
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            if (deleteStatement != null) {
                deleteStatement.close();
            }
            connection.setAutoCommit(true);
        }
    }

    private void fillDeleteStatement(User userToDelete, PreparedStatement deleteStatement) throws SQLException {
        deleteStatement.setInt(1, userToDelete.getId());
    }

    public List<User> getAllEntities() {
        Connection connection = dbConnector.getConnection();

        String sqlSelect = configurationManager.getProperty("database.sql.select.users");
        List<User> users = new ArrayList<>();

        try (Statement insertStatement = connection.createStatement();
             ResultSet resultSet = insertStatement.executeQuery(sqlSelect)) {
            mapSelectResultSetToUsers(users, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    private void mapSelectResultSetToUsers(List<User> users, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt(7));
            user.setName(resultSet.getString(1));
            user.setSurname(resultSet.getString(2));
            user.setLogin(resultSet.getString(3));
            user.setPassword(resultSet.getString(4));
            user.setMail(resultSet.getString(5));
            user.setBirthday(resultSet.getDate(6));
            user.setCityId(resultSet.getInt(8));
            users.add(user);
        }
    }

    public User getUserByLogin(String login) {

        Connection connection = dbConnector.getConnection();

        String sqlSelect = configurationManager.getProperty("database.sql.select.user.login").replace("%1", login);
        User user = null;

        try (Statement insertStatement = connection.createStatement();
             ResultSet resultSet = insertStatement.executeQuery(sqlSelect)) {

            if (!resultSet.isBeforeFirst()) {
                return null;
            } else {
                resultSet.next();
                user = new User();
                user.setName(resultSet.getString(1));
                user.setSurname(resultSet.getString(2));
                user.setLogin(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
                user.setMail(resultSet.getString(5));
                user.setBirthday(resultSet.getDate(6));
                user.setId(resultSet.getInt(7));
                user.setCityId(resultSet.getInt(8));
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
