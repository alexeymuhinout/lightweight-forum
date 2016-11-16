package com.rustedbrain.web.controller.jdbc.subcategory;

import com.rustedbrain.web.controller.jdbc.DBController;
import com.rustedbrain.web.model.jdbc.Subcategory;
import com.rustedbrain.web.model.servlet.UserSubcategory;

import java.sql.SQLException;
import java.util.List;

public abstract class DBSubcategoryController extends DBController<Subcategory> {

    public abstract List<Subcategory> getSubcategories(Integer categoryId) throws SQLException;

    public abstract List<UserSubcategory> getUserSubcategories(Integer categoryId) throws SQLException;
}
