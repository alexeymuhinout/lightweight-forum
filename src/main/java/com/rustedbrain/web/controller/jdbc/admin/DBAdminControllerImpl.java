package com.rustedbrain.web.controller.jdbc.admin;

import com.rustedbrain.web.controller.jdbc.DBConnector;
import com.rustedbrain.web.controller.jdbc.PostgreSQLDBConnector;
import com.rustedbrain.web.controller.jdbc.user.DBUserControllerImpl;
import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.controller.resource.Manager;
import com.rustedbrain.web.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class DBAdminControllerImpl extends DBUserControllerImpl {

    private Manager configurationManager = ConfigurationManager.getInstance();
    private DBConnector dbConnector = PostgreSQLDBConnector.getInstance();

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
        String sqlInsert = configurationManager.getProperty("database.sql.insert.admins");
        executePreparedInsert(dbConnector, sqlInsert, entities);
    }

    @Override
    public void update(User oldEntity, User newEntity) throws SQLException {
        String sqlUpdate = configurationManager.getProperty("database.sql.update.admins");
        executePreparedUpdate(dbConnector, oldEntity, newEntity, sqlUpdate);
    }

    @Override
    public void delete(User entity) throws SQLException {
        this.deleteAll(Collections.singletonList(entity));
    }

    @Override
    public void deleteAll(List<User> entities) throws SQLException {
        String sqlDelete = configurationManager.getProperty("database.sql.delete.admins");
        executePreparedDelete(dbConnector, sqlDelete, entities);
    }

    @Override
    protected void fillDeleteStatement(User userToDelete, PreparedStatement deleteStatement) throws SQLException {
        deleteStatement.setInt(1, userToDelete.getId());
    }

    public List<User> getAll() throws SQLException {
        String sqlSelect = configurationManager.getProperty("database.sql.select.admins");
        return executeSelectEntities(dbConnector, sqlSelect);
    }

    @Override
    protected void fillGeneratedEntityId(User entity, ResultSet generatedKeys) throws SQLException {
        entity.setId(generatedKeys.getInt(1));
    }
}
