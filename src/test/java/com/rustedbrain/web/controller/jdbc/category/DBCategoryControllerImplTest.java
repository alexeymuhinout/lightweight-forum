package com.rustedbrain.web.controller.jdbc.category;

import com.rustedbrain.web.model.Category;
import junit.framework.TestCase;
import org.junit.Assert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class DBCategoryControllerImplTest extends TestCase {

    private Connection connection;
    private DBCategoryControllerImpl dbCategoryController;
    private Category category;

    @Override
    public void setUp() throws Exception {
        String url = "jdbc:postgresql://192.168.184.102:5432/lightweight-forum";
        String dbUser = "postgres";
        String dbPassword = "postgres";
        this.connection = DriverManager.getConnection(url, dbUser, dbPassword);
        this.dbCategoryController = new DBCategoryControllerImpl();
        this.category = createTestCategory(0, "C++");
    }

    public void testInsertCategory() throws Exception {
        List<Category> categoriesBeforeInsert = dbCategoryController.getAll();
        int categoriesBeforeInsertSize = categoriesBeforeInsert.size();

        dbCategoryController.insert(this.category);
        Assert.assertEquals(categoriesBeforeInsertSize, categoriesBeforeInsertSize + 1);
    }

    public void testInsertCategories() throws Exception {
        List<Category> categoriesBeforeInsert = dbCategoryController.getAll();
        int categoriesBeforeInsertSize = categoriesBeforeInsert.size();

        dbCategoryController.insertAll(Arrays.asList(this.category, this.category));
        Assert.assertEquals(categoriesBeforeInsertSize, categoriesBeforeInsertSize + 2);
    }

    private Category createTestCategory(int id, String name) {
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        return category;
    }

    @Override
    public void tearDown() throws Exception {
        String sqlDeleteAllCategories = "DELETE FROM category";
        Statement statement = this.connection.createStatement();
        statement.executeUpdate(sqlDeleteAllCategories);
        statement.close();
        this.connection.close();
    }
}
