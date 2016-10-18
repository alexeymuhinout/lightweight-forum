package com.rustedbrain.web.controller.jdbc.category;

import com.rustedbrain.web.controller.jdbc.DBController;
import com.rustedbrain.web.model.Category;

import java.util.List;

public abstract class DBCategoryController extends DBController<Category> {

    public abstract List<Category> getCategories(int currentPage, int categoriesPerPage);

}
