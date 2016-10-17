package com.rustedbrain.web.controller.jdbc.category;

import com.rustedbrain.web.controller.jdbc.CRUDController;
import com.rustedbrain.web.model.Category;

import java.util.List;

public interface DBCategoryController extends CRUDController<Category> {

    List<Category> getCategories(int currentPage, int categoriesPerPage);

}
