package com.rustedbrain.web.controller.jdbc.category;

import com.rustedbrain.web.controller.jdbc.DBController;
import com.rustedbrain.web.model.jdbc.Category;
import com.rustedbrain.web.model.servlet.ModeratedCategory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class DBCategoryController extends DBController<Category> {

    public abstract List<Category> getCategories(int currentPage, int categoriesPerPage);

    protected abstract List<ModeratedCategory> mapSelectResultSetToModeratedCategories(ResultSet resultSet) throws SQLException;

    public abstract List<ModeratedCategory> getModeratedCategories() throws SQLException;

}
