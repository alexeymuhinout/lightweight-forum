package com.rustedbrain.web.controller.jdbc.city;

import com.rustedbrain.web.controller.jdbc.DBConnector;
import com.rustedbrain.web.controller.jdbc.PostgreSQLDBConnector;
import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.controller.resource.Manager;
import com.rustedbrain.web.model.jdbc.City;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DBCityControllerImpl extends DBCityController {

    private Manager configurationManager = ConfigurationManager.getInstance();
    private DBConnector dbConnector = PostgreSQLDBConnector.getInstance();

    @Override
    public City getEntityById(int id) throws SQLException {
        String sqlSelect = configurationManager.getProperty("database.sql.select.city.id").replace("%1", String.valueOf(id));
        return executeSelectEntity(dbConnector, sqlSelect);
    }

    @Override
    public void insert(City entity) throws SQLException {
        this.insertAll(Collections.singletonList(entity));
    }

    @Override
    public void insertAll(List<City> entities) throws SQLException {
        String sqlInsert = configurationManager.getProperty("database.sql.insert.cities");
        executePreparedInsert(dbConnector, sqlInsert, entities);
    }

    @Override
    public void update(City oldEntity, City newEntity) throws SQLException {
        String sqlUpdate = configurationManager.getProperty("database.sql.update.cities");
        executePreparedUpdate(dbConnector, oldEntity, newEntity, sqlUpdate);
    }

    protected void fillUpdateStatement(City oldCity, City newCity, PreparedStatement statement) throws SQLException {
        statement.setString(1, newCity.getName());
        statement.setInt(2, oldCity.getId());
    }

    public void delete(City entity) throws SQLException {
        this.deleteAll(Collections.singletonList(entity));
    }

    @Override
    public void deleteAll(List<City> entities) throws SQLException {
        String sqlDelete = configurationManager.getProperty("database.sql.delete.cities");
        executePreparedDelete(dbConnector, sqlDelete, entities);
    }

    protected void fillDeleteStatement(City cityToDelete, PreparedStatement deleteStatement) throws SQLException {
        deleteStatement.setInt(1, cityToDelete.getId());
    }

    public List<City> getAll() throws SQLException {
        String sqlSelect = configurationManager.getProperty("database.sql.select.cities");
        return executeSelectEntities(dbConnector, sqlSelect);
    }

    @Override
    protected List<City> mapSelectResultSetToEntities(ResultSet resultSet) throws SQLException {
        List<City> cities = new ArrayList<>();
        while (resultSet.next()) {
            City city = new City();
            city.setId(resultSet.getInt(1));
            city.setName(resultSet.getString(2));
            cities.add(city);
        }
        return cities;
    }

    @Override
    protected City mapSelectResultSetToEntity(ResultSet resultSet) throws SQLException {
        City city = new City();
        city.setId(resultSet.getInt(1));
        city.setName(resultSet.getString(2));
        return city;
    }

    @Override
    protected void fillGeneratedEntityId(City entity, ResultSet generatedKeys) throws SQLException {
        entity.setId(generatedKeys.getInt(1));
    }

    @Override
    protected void fillInsertStatement(City entity, PreparedStatement insertStatement) throws SQLException {
        insertStatement.setString(1, entity.getName());
    }
}
