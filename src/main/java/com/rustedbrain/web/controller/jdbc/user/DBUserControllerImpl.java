package com.rustedbrain.web.controller.jdbc.user;

import com.rustedbrain.web.controller.jdbc.DBConnector;
import com.rustedbrain.web.controller.jdbc.util.DBUtil;
import com.rustedbrain.web.controller.resource.Manager;
import com.rustedbrain.web.model.jdbc.User;

import javax.xml.bind.JAXBException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DBUserControllerImpl extends DBUserController {

    private Manager configurationManager;
    private DBConnector dbConnector;
    private DBUtil dbUtil;

    public DBUserControllerImpl(Manager configurationManager, DBConnector dbConnector, DBUtil dbUtil) {
        this.configurationManager = configurationManager;
        this.dbConnector = dbConnector;
        this.dbUtil = dbUtil;
    }

    @Override
    protected void fillInsertStatement(User user, PreparedStatement insertStatement) throws SQLException {
        insertStatement.setDate(1, user.getCreationDate());
        insertStatement.setString(2, user.getName());
        insertStatement.setString(3, user.getSurname());
        insertStatement.setString(4, user.getLogin());
        insertStatement.setString(5, user.getPassword());
        insertStatement.setString(6, user.getMail());
        insertStatement.setDate(7, user.getBirthday());
        insertStatement.setInt(8, user.getCityId());
    }

    @Override
    public void checkTableExistence() throws SQLException {
        Connection connection = dbConnector.getConnection();
        connection.setAutoCommit(false);
        try {
            dbUtil.checkTableExistence("public", "user", connection);
            connection.commit();
        } catch (JAXBException e) {
            connection.rollback();
            e.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public User getEntityById(int id) throws SQLException {
        String sqlSelect = configurationManager.getProperty("database.sql.select.user.id").replace("%1", String.valueOf(id));
        return executeSelectEntity(dbConnector, sqlSelect);
    }

    @Override
    public void insert(User entity) throws SQLException {
        this.insertAll(Collections.singletonList(entity));
    }

    @Override
    public void insertAll(List<User> entities) throws SQLException {
        String sqlInsert = configurationManager.getProperty("database.sql.insert.users");
        executePreparedInsert(dbConnector, sqlInsert, entities);
    }

    @Override
    public void update(User oldEntity, User newEntity) throws SQLException {
        String sqlUpdate = configurationManager.getProperty("database.sql.update.users");
        executePreparedUpdate(dbConnector, oldEntity, newEntity, sqlUpdate);
    }

    @Override
    protected void fillUpdateStatement(User oldUser, User newUser, PreparedStatement statement) throws SQLException {
        statement.setDate(1, newUser.getCreationDate());
        statement.setString(2, newUser.getName());
        statement.setString(3, newUser.getSurname());
        statement.setString(4, newUser.getLogin());
        statement.setString(5, newUser.getPassword());
        statement.setString(6, newUser.getMail());
        statement.setDate(7, newUser.getBirthday());
        statement.setInt(8, newUser.getCityId());
        statement.setInt(9, oldUser.getId());
    }

    @Override
    public void delete(User entity) throws SQLException {
        this.deleteAll(Collections.singletonList(entity));
    }

    @Override
    public void deleteAll(List<User> entities) throws SQLException {
        String sqlDelete = configurationManager.getProperty("database.sql.delete.users");
        executePreparedDelete(dbConnector, sqlDelete, entities);
    }

    @Override
    protected void fillDeleteStatement(User userToDelete, PreparedStatement deleteStatement) throws SQLException {
        deleteStatement.setInt(1, userToDelete.getId());
    }

    public List<User> getAll() throws SQLException {
        String sqlSelect = configurationManager.getProperty("database.sql.select.users");
        return executeSelectEntities(dbConnector, sqlSelect);
    }

    @Override
    protected void fillGeneratedEntityId(User entity, ResultSet generatedKeys) throws SQLException {
        entity.setId(generatedKeys.getInt(1));
    }

    @Override
    protected List<User> mapSelectResultSetToEntities(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt(1));
            user.setCreationDate(resultSet.getDate(2));
            user.setName(resultSet.getString(3));
            user.setSurname(resultSet.getString(4));
            user.setLogin(resultSet.getString(5));
            user.setPassword(resultSet.getString(6));
            user.setMail(resultSet.getString(7));
            user.setBirthday(resultSet.getDate(8));
            user.setCityId(resultSet.getInt(9));
            users.add(user);
        }
        return users;
    }

    @Override
    protected User mapSelectResultSetToEntity(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(1));
        user.setCreationDate(resultSet.getDate(2));
        user.setName(resultSet.getString(2));
        user.setSurname(resultSet.getString(3));
        user.setLogin(resultSet.getString(4));
        user.setPassword(resultSet.getString(5));
        user.setMail(resultSet.getString(6));
        user.setBirthday(resultSet.getDate(7));
        user.setCityId(resultSet.getInt(8));
        return user;
    }

    public User getUserByLogin(String login) throws SQLException {
        String sqlSelect = configurationManager.getProperty("database.sql.select.user.login").replace("%1", login);
        Connection connection = dbConnector.getConnection();

        try (Statement insertStatement = connection.createStatement();
             ResultSet resultSet = insertStatement.executeQuery(sqlSelect)) {

            if (!resultSet.isBeforeFirst()) {
                return null;
            } else {
                resultSet.next();
                return mapSelectResultSetToEntity(resultSet);
            }
        }
    }
}
