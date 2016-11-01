package com.rustedbrain.web.controller.jdbc.city;

import com.rustedbrain.web.model.jdbc.City;
import junit.framework.TestCase;
import org.junit.Assert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class DBCityControllerImplTest extends TestCase {

    private Connection connection;
    private DBCityController dbCityController;
    private City city1;
    private City city2;

    @Override
    public void setUp() throws Exception {
        String url = "jdbc:postgresql://192.168.184.102:5432/lightweight-forum";
        String dbUser = "postgres";
        String dbPassword = "postgres";
        this.connection = DriverManager.getConnection(url, dbUser, dbPassword);
        this.dbCityController = new DBCityControllerImpl();
        this.city1 = createTestCity(0, "Las Vegas");
        this.city2 = createTestCity(1, "New York");
    }

    private City createTestCity(int id, String name) {
        City city = new City();
        city.setId(id);
        city.setName(name);
        return city;
    }

    public void testGetAllCities() throws Exception {
        List<City> cities = dbCityController.getAll();
        Assert.assertEquals(0, cities.size());
    }

    public void testInsertCity() throws Exception {
        List<City> cities = dbCityController.getAll();
        int citiesBeforeInsertSize = cities.size();
        dbCityController.insert(this.city1);
        int expectedResult = citiesBeforeInsertSize + 1;
        Assert.assertEquals(expectedResult, dbCityController.getAll().size());
    }

    public void testInsertCities() throws Exception {
        List<City> cities = dbCityController.getAll();
        int citiesBeforeInsertSize = cities.size();
        dbCityController.insertAll(Arrays.asList(this.city1, this.city2));
        int expectedResult = citiesBeforeInsertSize + 2;
        Assert.assertEquals(expectedResult, dbCityController.getAll().size());
    }

    public void testGetCityById() throws Exception {
        dbCityController.insert(this.city1);
        City city = dbCityController.getEntityById(this.city1.getId());
        Assert.assertEquals(this.city1, city);
    }

    public void testDeleteCity() throws Exception {
        dbCityController.insert(this.city1);
        dbCityController.delete(this.city1);
        City city = dbCityController.getEntityById(city1.getId());
        Assert.assertNull(city);
    }

    public void testDeleteCities() throws Exception {
        dbCityController.insertAll(Arrays.asList(this.city1, this.city1));
        List<City> cities = dbCityController.getAll();
        dbCityController.deleteAll(cities);
        int expectedResult = 0;
        Assert.assertEquals(expectedResult, dbCityController.getAll().size());
    }

    public void testUpdateCity() throws Exception {
        dbCityController.insert(this.city1);
        City oldCity = city1.clone();
        String newName = "Moscow";
        city1.setName(newName);
        dbCityController.update(oldCity, city1);
        Assert.assertNotEquals(oldCity, dbCityController.getEntityById(oldCity.getId()));
    }

    @Override
    public void tearDown() throws Exception {
        String sqlDeleteAllUsers = "DELETE FROM \"city\";";
        Statement statement = this.connection.createStatement();
        statement.executeUpdate(sqlDeleteAllUsers);
        statement.close();
        this.connection.close();
    }
}
