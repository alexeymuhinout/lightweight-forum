package com.rustedbrain.web.controller.jdbc.subcategory;

import com.rustedbrain.web.controller.jdbc.DBConnector;
import com.rustedbrain.web.controller.jdbc.util.DBUtil;
import com.rustedbrain.web.controller.resource.Manager;
import com.rustedbrain.web.model.jdbc.Subcategory;

import javax.xml.bind.JAXBException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DBSubcategoryControllerImpl extends DBSubcategoryController {

    private Manager configurationManager;
    private DBConnector dbConnector;
    private DBUtil dbUtil;

    public DBSubcategoryControllerImpl(Manager configurationManager, DBConnector dbConnector, DBUtil dbUtil) {
        this.configurationManager = configurationManager;
        this.dbConnector = dbConnector;
        this.dbUtil = dbUtil;
    }

    @Override
    public void checkTableExistence() throws SQLException {
        Connection connection = dbConnector.getConnection();
        connection.setAutoCommit(false);
        try {
            dbUtil.checkTableExistence("public", "subcategory", connection);
            connection.commit();
        } catch (JAXBException e) {
            connection.rollback();
            e.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public Subcategory getEntityById(int id) throws SQLException {
        checkTableExistence();
        String sqlSelect = configurationManager.getProperty("database.sql.select.subcategory.id").replace("%1", String.valueOf(id));
        return executeSelectEntity(dbConnector, sqlSelect);
    }

    @Override
    public void insert(Subcategory entity) throws SQLException {
        this.insertAll(Collections.singletonList(entity));
    }

    @Override
    public void insertAll(List<Subcategory> entities) throws SQLException {
        checkTableExistence();
        String sqlInsert = configurationManager.getProperty("database.sql.insert.subcategories");
        executePreparedInsert(dbConnector, sqlInsert, entities);
    }

    @Override
    public void update(Subcategory oldEntity, Subcategory newEntity) throws SQLException {
        checkTableExistence();
        String sqlUpdate = configurationManager.getProperty("database.sql.update.subcategories");
        executePreparedUpdate(dbConnector, oldEntity, newEntity, sqlUpdate);
    }

    @Override
    public void delete(Subcategory entity) throws SQLException {
        this.deleteAll(Collections.singletonList(entity));
    }

    @Override
    public void deleteAll(List<Subcategory> entities) throws SQLException {
        checkTableExistence();
        String sqlDelete = configurationManager.getProperty("database.sql.delete.subcategories");
        executePreparedDelete(dbConnector, sqlDelete, entities);
    }

    @Override
    public List<Subcategory> getAll() throws SQLException {
        checkTableExistence();
        String sqlSelect = configurationManager.getProperty("database.sql.select.subcategories");
        return executeSelectEntities(dbConnector, sqlSelect);
    }

    @Override
    protected void fillDeleteStatement(Subcategory subcategory, PreparedStatement deleteStatement) throws SQLException {
        deleteStatement.setInt(1, subcategory.getId());
    }

    @Override
    protected List<Subcategory> mapSelectResultSetToEntities(ResultSet resultSet) throws SQLException {
        List<Subcategory> subcategories = new ArrayList<>();
        while (resultSet.next()) {
            Subcategory subcategory = new Subcategory();
            subcategory.setId(resultSet.getInt(1));
            subcategory.setCreationDate(resultSet.getDate(2));
            subcategory.setName(resultSet.getString(3));
            subcategory.setUserId(resultSet.getInt(4));
            subcategory.setCategoryId(resultSet.getInt(5));
            subcategories.add(subcategory);
        }
        return subcategories;
    }

    @Override
    protected Subcategory mapSelectResultSetToEntity(ResultSet resultSet) throws SQLException {
        Subcategory subcategory = new Subcategory();
        subcategory.setId(resultSet.getInt(1));
        subcategory.setCreationDate(resultSet.getDate(2));
        subcategory.setName(resultSet.getString(3));
        subcategory.setUserId(resultSet.getInt(4));
        subcategory.setCategoryId(resultSet.getInt(5));
        return subcategory;
    }

    @Override
    protected void fillGeneratedEntityId(Subcategory entity, ResultSet generatedKeys) throws SQLException {
        entity.setId(generatedKeys.getInt(1));
    }

    @Override
    protected void fillInsertStatement(Subcategory entity, PreparedStatement insertStatement) throws SQLException {
        insertStatement.setDate(1, entity.getCreationDate());
        insertStatement.setString(2, entity.getName());
        insertStatement.setInt(3, entity.getUserId());
        insertStatement.setInt(4, entity.getCategoryId());
    }

    @Override
    protected void fillUpdateStatement(Subcategory oldEntity, Subcategory newEntity, PreparedStatement updateStatement) throws SQLException {
        updateStatement.setDate(1, newEntity.getCreationDate());
        updateStatement.setString(2, newEntity.getName());
        updateStatement.setInt(3, newEntity.getUserId());
        updateStatement.setInt(4, newEntity.getCategoryId());
        updateStatement.setInt(5, oldEntity.getId());
    }
}
