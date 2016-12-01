package com.rustedbrain.web.controller.jdbc.city;

import com.rustedbrain.web.controller.jdbc.DBConnector;
import com.rustedbrain.web.controller.jdbc.util.DBUtil;
import com.rustedbrain.web.controller.resource.Manager;
import com.rustedbrain.web.model.jdbc.City;

import javax.xml.bind.JAXBException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class DBCityControllerImpl extends DBCityController {

    private Manager sqlManager;
    private DBConnector dbConnector;
    private DBUtil dbUtil;

    public DBCityControllerImpl(Manager sqlManager, DBConnector dbConnector, DBUtil dbUtil) {
        this.sqlManager = sqlManager;
        this.dbConnector = dbConnector;
        this.dbUtil = dbUtil;
    }

    @Override
    public void checkTableExistence() throws SQLException {
        try (Connection connection = dbConnector.getConnection()) {
            connection.setAutoCommit(false);
            try {
                dbUtil.checkTableExistence("public", "city", connection);
            } catch (JAXBException e) {
                connection.rollback();
                e.printStackTrace();
            }
            connection.commit();
            connection.setAutoCommit(true);
        }
    }

    @Override
    public City getEntityById(int id) throws SQLException {
        checkTableExistence();
        String sqlSelect = sqlManager.getProperty("database.sql.select.city.id").replace("%1", String.valueOf(id));
        return executeSelectEntity(dbConnector, sqlSelect);
    }

    @Override
    public void insert(City entity) throws SQLException {
        this.insertAll(Collections.singletonList(entity));
    }

    @Override
    public void insertAll(List<City> entities) throws SQLException {
        checkTableExistence();
        String sqlInsert = sqlManager.getProperty("database.sql.insert.cities");
        executePreparedInsert(dbConnector, sqlInsert, entities);
    }

    @Override
    public void update(City oldEntity, City newEntity) throws SQLException {
        String sqlUpdate = sqlManager.getProperty("database.sql.update.cities");
        executePreparedUpdate(dbConnector, oldEntity, newEntity, sqlUpdate);
    }

    protected void fillUpdateStatement(City oldCity, City newCity, PreparedStatement statement) throws SQLException {
        statement.setDate(1, newCity.getCreationDate());
        statement.setString(2, newCity.getName());
        statement.setInt(3, oldCity.getId());
    }

    public void delete(City entity) throws SQLException {
        this.deleteAll(Collections.singletonList(entity));
    }

    @Override
    public void deleteAll(List<City> entities) throws SQLException {
        checkTableExistence();
        String sqlDelete = sqlManager.getProperty("database.sql.delete.cities");
        executePreparedDelete(dbConnector, sqlDelete, entities);
    }

    protected void fillDeleteStatement(City cityToDelete, PreparedStatement deleteStatement) throws SQLException {
        deleteStatement.setInt(1, cityToDelete.getId());
    }

    public List<City> getAll() throws SQLException {
        checkTableExistence();
        String sqlSelect = sqlManager.getProperty("database.sql.select.cities");
        return executeSelectEntities(dbConnector, sqlSelect);
    }

    @Override
    protected City mapSelectResultSetToEntity(ResultSet resultSet) throws SQLException {
        City city = new City();
        city.setId(resultSet.getInt(1));
        city.setCreationDate(resultSet.getDate(2));
        city.setName(resultSet.getString(3));
        return city;
    }

    @Override
    protected void fillGeneratedEntityId(City entity, ResultSet generatedKeys) throws SQLException {
        entity.setId(generatedKeys.getInt(1));
    }

    @Override
    protected void fillInsertStatement(City entity, PreparedStatement insertStatement) throws SQLException {
        insertStatement.setDate(1, entity.getCreationDate());
        insertStatement.setString(2, entity.getName());
    }

    @Override
    public City getCityByName(String cityName) throws SQLException {
        checkTableExistence();
        String sqlSelect = sqlManager.getProperty("database.sql.select.city.name").replace("%1", cityName);
        return executeSelectEntity(dbConnector, sqlSelect);
    }
}
