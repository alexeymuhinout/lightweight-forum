package com.rustedbrain.web.controller.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class DBController<T> {

    public abstract void checkTableExistence() throws SQLException;

    public abstract T getEntityById(int id) throws SQLException;

    public abstract void insert(T entity) throws SQLException;

    public abstract void insertAll(List<T> entities) throws SQLException;

    public abstract void update(T oldEntity, T newEntity) throws SQLException;

    public abstract void delete(T entity) throws SQLException;

    public abstract void deleteAll(List<T> entities) throws SQLException;

    public abstract List<T> getAll() throws SQLException;

    protected void executePreparedDelete(DBConnector dbConnector, String sqlDelete, List<T> entities) throws SQLException {
        Connection connection = dbConnector.getConnection();
        PreparedStatement deleteStatement = null;

        try {
            connection.setAutoCommit(false);
            deleteStatement = connection.prepareStatement(sqlDelete);
            for (T entity : entities) {
                fillDeleteStatement(entity, deleteStatement);
                deleteStatement.executeUpdate();
                connection.commit();
            }
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            if (deleteStatement != null) {
                deleteStatement.close();
            }
            connection.setAutoCommit(true);
        }
    }

    protected abstract void fillDeleteStatement(T user, PreparedStatement deleteStatement) throws SQLException;

    protected void executePreparedUpdate(DBConnector dbConnector, T oldEntity, T newEntity, String sqlUpdate) throws SQLException {

        PreparedStatement updateStatement = null;
        Connection connection = dbConnector.getConnection();
        try {
            connection.setAutoCommit(false);
            updateStatement = connection.prepareStatement(sqlUpdate);
            fillUpdateStatement(oldEntity, newEntity, updateStatement);
            updateStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            if (updateStatement != null) {
                updateStatement.close();
            }
            connection.setAutoCommit(true);
        }
    }

    protected void executePreparedInsert(DBConnector dbConnector, String sqlInsert, List<T> entities) throws SQLException {
        Connection connection = dbConnector.getConnection();
        PreparedStatement insertStatement = null;
        try {
            connection.setAutoCommit(false);
            insertStatement = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            for (T entity : entities) {
                fillInsertStatement(entity, insertStatement);
                insertStatement.executeUpdate();
                connection.commit();
                try (ResultSet generatedKeys = insertStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        fillGeneratedEntityId(entity, generatedKeys);
                    } else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }
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

    protected T executeSelectEntity(DBConnector dbConnector, String sqlSelect) throws SQLException {
        Connection connection = dbConnector.getConnection();
        T entity = null;

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlSelect)) {
            if (!resultSet.isBeforeFirst()) {
                return null;
            } else {
                resultSet.next();
                entity = mapSelectResultSetToEntity(resultSet);
            }
        }
        return entity;
    }

    protected List<T> executeSelectEntities(DBConnector dbConnector, String sqlSelect) throws SQLException {
        Connection connection = dbConnector.getConnection();
        List<T> entities = new ArrayList<>();
        try (Statement insertStatement = connection.createStatement();
             ResultSet resultSet = insertStatement.executeQuery(sqlSelect)) {
            entities.addAll(mapSelectResultSetToEntities(resultSet));
        }
        return entities;
    }

    protected abstract List<T> mapSelectResultSetToEntities(ResultSet resultSet) throws SQLException;

    protected abstract T mapSelectResultSetToEntity(ResultSet resultSet) throws SQLException;

    protected abstract void fillGeneratedEntityId(T entity, ResultSet generatedKeys) throws SQLException;

    protected abstract void fillInsertStatement(T entity, PreparedStatement insertStatement) throws SQLException;

    protected abstract void fillUpdateStatement(T oldEntity, T newEntity, PreparedStatement updateStatement) throws SQLException;
}
