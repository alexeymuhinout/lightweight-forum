package com.rustedbrain.web.controller.jdbc.category;

import com.rustedbrain.web.model.Category;

import java.util.List;

public class DBCategoryControllerImpl implements DBCategoryController {

    private static DBCategoryControllerImpl ourInstance = new DBCategoryControllerImpl();

    private DBCategoryControllerImpl() {
    }

    public static DBCategoryControllerImpl getInstance() {
        return ourInstance;
    }

    public void insert(Category entity) {

    }

    public void update(Category oldEntity, Category newEntity) {

    }

    public void delete(Category entity) {

    }

    public List<Category> getAllEntities() {
        return null;
    }

    @Override
    public List<Category> getCategories(int currentPage, int categoriesPerPage) {
        return null;
    }
}
