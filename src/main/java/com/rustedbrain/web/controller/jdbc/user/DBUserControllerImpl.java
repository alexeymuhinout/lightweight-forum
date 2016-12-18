package com.rustedbrain.web.controller.jdbc.user;

import com.rustedbrain.web.controller.jdbc.DBConnector;
import com.rustedbrain.web.controller.jdbc.util.DBUtil;
import com.rustedbrain.web.controller.resource.Manager;
import com.rustedbrain.web.model.jdbc.User;

import javax.xml.bind.JAXBException;
import java.sql.*;
import java.util.Collections;
import java.util.List;

public class DBUserControllerImpl extends DBUserController {

    private Manager sqlManager;
    private DBConnector dbConnector;
    private DBUtil dbUtil;

    public DBUserControllerImpl(Manager sqlManager, DBConnector dbConnector, DBUtil dbUtil) {
        this.sqlManager = sqlManager;
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
        insertStatement.setBoolean(9, user.isAdmin());
        if (user.getBlockUntilDate() == null) {
            insertStatement.setNull(10, Types.DATE);
        } else {
            insertStatement.setDate(10, user.getBlockUntilDate());
        }
        if (user.getBlockReason() == null) {
            insertStatement.setNull(11, Types.VARCHAR);
        } else {
            insertStatement.setString(11, user.getBlockReason());
        }
    }

    @Override
    public void checkTableExistence() throws SQLException {
        try (Connection connection = dbConnector.getConnection()) {
            connection.setAutoCommit(false);
            try {
                dbUtil.checkTableExistence("public", "user", connection);
            } catch (JAXBException e) {
                connection.rollback();
                e.printStackTrace();
            }
            connection.commit();
            connection.setAutoCommit(true);
        }
    }

    @Override
    public User getEntityById(int id) throws SQLException {
        String sqlSelect = sqlManager.getProperty("database.sql.select.user.id").replace("%1", String.valueOf(id));
        return executeSelectEntity(dbConnector, sqlSelect);
    }

    @Override
    public void insert(User entity) throws SQLException {
        this.insertAll(Collections.singletonList(entity));
    }

    @Override
    public void insertAll(List<User> entities) throws SQLException {
        checkTableExistence();
        String sqlInsert = sqlManager.getProperty("database.sql.insert.users");
        executePreparedInsert(dbConnector, sqlInsert, entities);
    }

    @Override
    public void update(User oldEntity, User newEntity) throws SQLException {
        String sqlUpdate = sqlManager.getProperty("database.sql.update.users");
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
        statement.setBoolean(9, newUser.isAdmin());
        if (newUser.getBlockUntilDate() == null) {
            statement.setNull(10, Types.DATE);
        } else {
            statement.setDate(10, newUser.getBlockUntilDate());
        }
        if (newUser.getBlockReason() == null) {
            statement.setNull(11, Types.VARCHAR);
        } else {
            statement.setString(11, newUser.getBlockReason());
        }
        statement.setInt(12, oldUser.getId());
    }

    @Override
    public void delete(User entity) throws SQLException {
        this.deleteAll(Collections.singletonList(entity));
    }

    @Override
    public void deleteAll(List<User> entities) throws SQLException {
        checkTableExistence();
        String sqlDelete = sqlManager.getProperty("database.sql.delete.users");
        executePreparedDelete(dbConnector, sqlDelete, entities);
    }

    @Override
    protected void fillDeleteStatement(User userToDelete, PreparedStatement deleteStatement) throws SQLException {
        deleteStatement.setInt(1, userToDelete.getId());
    }

    public List<User> getAll() throws SQLException {
        checkTableExistence();
        String sqlSelect = sqlManager.getProperty("database.sql.select.users");
        return executeSelectEntities(dbConnector, sqlSelect);
    }

    @Override
    protected void fillGeneratedEntityId(User entity, ResultSet generatedKeys) throws SQLException {
        entity.setId(generatedKeys.getInt(1));
    }

    @Override
    protected User mapSelectResultSetToEntity(ResultSet resultSet) throws SQLException {
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
        user.setAdmin(resultSet.getBoolean(10));
        user.setBlockUntilDate(resultSet.getDate(11));
        user.setBlockReason(resultSet.getString(12));
        return user;
    }

    public User getUserByLogin(String login) throws SQLException {
        checkTableExistence();
        String sqlSelect = sqlManager.getProperty("database.sql.select.user.login").replace("%1", login);
        return super.executeSelectEntity(dbConnector, sqlSelect);
    }

    @Override
    public User getUserByMail(String mail) throws SQLException {
        checkTableExistence();
        String sqlSelect = sqlManager.getProperty("database.sql.select.user.mail").replace("%1", mail);
        return super.executeSelectEntity(dbConnector, sqlSelect);
    }

    @Override
    public List<User> getAdminUsers() throws SQLException {
        checkTableExistence();
        String sqlSelect = sqlManager.getProperty("database.sql.select.users.admin");
        return executeSelectEntities(dbConnector, sqlSelect);
    }
}
