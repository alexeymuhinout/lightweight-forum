package com.rustedbrain.web.controller.logic;

import com.rustedbrain.web.controller.jdbc.category.DBCategoryController;
import com.rustedbrain.web.controller.jdbc.category.DBCategoryControllerImpl;
import com.rustedbrain.web.model.Category;

import java.util.List;

public class ForumLogic {

    private static ForumLogic ourInstance = new ForumLogic();
    private DBCategoryController dbCategoryController = DBCategoryControllerImpl.getInstance();

    private ForumLogic() {
    }

    public static ForumLogic getInstance() {
        return ourInstance;
    }

    public List<Category> getCategories(int currentCategoriesPage, int maxCategoriesPerPage) {
        return null;
    }
}