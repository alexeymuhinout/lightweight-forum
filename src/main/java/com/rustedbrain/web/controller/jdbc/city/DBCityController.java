package com.rustedbrain.web.controller.jdbc.city;

import com.rustedbrain.web.controller.jdbc.DBController;
import com.rustedbrain.web.model.jdbc.City;

import java.sql.SQLException;

public abstract class DBCityController extends DBController<City> {
    public abstract City getCityByName(String cityName) throws SQLException;
}
