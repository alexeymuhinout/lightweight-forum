package com.rustedbrain.web.controller.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class DBController<T> {

    public abstract void checkTableExistence() throws SQLException, ClassNotFoundException;

    public abstract T getEntityById(int id) throws SQLException;

    public abstract void insert(T entity) throws SQLException;

    public abstract void insertAll(List<T> entities) throws SQLException;

    public abstract void update(T oldEntity, T newEntity) throws SQLException;

    public abstract void delete(T entity) throws SQLException;

    public abstract void deleteAll(List<T> entities) throws SQLException;

    public abstract List<T> getAll() throws SQLException;

    private List<T> mapSelectResultSetToEntities(ResultSet resultSet) throws SQLException {
        List<T> subcategories = new ArrayList<>();
        while (resultSet.next()) {
            subcategories.add(mapSelectResultSetToEntity(resultSet));
        }
        return subcategories;
    }

    protected void executePreparedDelete(DBConnector dbConnector, String sqlDelete, List<T> entities) throws SQLException {

        try (Connection connection = dbConnector.getConnection(); PreparedStatement deleteStatement = connection.prepareStatement(sqlDelete)) {
            connection.setAutoCommit(false);
            try {
                for (T entity : entities) {
                    fillDeleteStatement(entity, deleteStatement);
                    deleteStatement.executeUpdate();
                }
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
            connection.commit();
            connection.setAutoCommit(true);
        }
    }

    protected abstract void fillDeleteStatement(T user, PreparedStatement deleteStatement) throws SQLException;

    protected void executePreparedUpdate(DBConnector dbConnector, T oldEntity, T newEntity, String sqlUpdate) throws SQLException {
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement updateStatement = connection.prepareStatement(sqlUpdate)) {
            connection.setAutoCommit(false);
            try {
                fillUpdateStatement(oldEntity, newEntity, updateStatement);
                updateStatement.executeUpdate();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
            connection.commit();
            connection.setAutoCommit(true);
        }
    }

    protected void executePreparedInsert(DBConnector dbConnector, String sqlInsert, List<T> entities) throws SQLException {
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement insertStatement = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            try {
                for (T entity : entities) {
                    fillInsertStatement(entity, insertStatement);
                    insertStatement.executeUpdate();
                    try (ResultSet generatedKeys = insertStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            fillGeneratedEntityId(entity, generatedKeys);
                        } else {
                            throw new SQLException("Creating user failed, no ID obtained.");
                        }
                    }
                }
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
            connection.commit();
            connection.setAutoCommit(true);
        }
    }

    protected T executeSelectEntity(DBConnector dbConnector, String sqlSelect) throws SQLException {
        try (Connection connection = dbConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlSelect)) {
            if (!resultSet.isBeforeFirst()) {
                return null;
            } else {
                resultSet.next();
                return mapSelectResultSetToEntity(resultSet);
            }
        }
    }

    protected List<T> executeSelectEntities(DBConnector dbConnector, String sqlSelect) throws SQLException {
        List<T> entities = new ArrayList<>();
        try (Connection connection = dbConnector.getConnection();
             Statement insertStatement = connection.createStatement();
             ResultSet resultSet = insertStatement.executeQuery(sqlSelect)) {
            entities.addAll(mapSelectResultSetToEntities(resultSet));
        }
        return entities;
    }


    protected abstract T mapSelectResultSetToEntity(ResultSet resultSet) throws SQLException;

    protected abstract void fillGeneratedEntityId(T entity, ResultSet generatedKeys) throws SQLException;

    protected abstract void fillInsertStatement(T entity, PreparedStatement insertStatement) throws SQLException;

    protected abstract void fillUpdateStatement(T oldEntity, T newEntity, PreparedStatement updateStatement) throws SQLException;
}
