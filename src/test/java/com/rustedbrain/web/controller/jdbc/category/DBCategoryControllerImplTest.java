package com.rustedbrain.web.controller.jdbc.category;

import com.rustedbrain.web.controller.jdbc.DBConnectorImpl;
import com.rustedbrain.web.controller.jdbc.util.DBUtil;
import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.controller.resource.MessageManager;
import com.rustedbrain.web.controller.resource.SQLManager;
import com.rustedbrain.web.controller.util.jaxb.JaxbParser;
import com.rustedbrain.web.controller.util.jaxb.Parser;
import com.rustedbrain.web.model.jdbc.Category;
import junit.framework.TestCase;
import org.junit.Assert;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class DBCategoryControllerImplTest extends TestCase {

    private Connection connection;
    private DBCategoryControllerImpl dbCategoryController;
    private Category category1;
    private Category category2;

    @Override
    public void setUp() throws Exception {
        String url = "jdbc:postgresql://127.0.0.1:5432/lightweight-forum-test";
        String dbUser = "postgres";
        String dbPassword = "postgres";
        Parser parser = new JaxbParser();
        DBUtil dbUtil = new DBUtil(ConfigurationManager.getInstance(), MessageManager.getInstance(), SQLManager.getInstance(), parser);
        this.dbCategoryController = new DBCategoryControllerImpl(ConfigurationManager.getInstance(), new DBConnectorImpl(url, dbUser, dbPassword), dbUtil);
        this.category1 = createTestCategory(0, "C++");
    }

    public void testInsertCategory() throws Exception {
        List<Category> categoriesBeforeInsert = dbCategoryController.getAll();
        int categoriesBeforeInsertSize = categoriesBeforeInsert.size();
        dbCategoryController.insert(this.category1);
        Assert.assertEquals(categoriesBeforeInsertSize, categoriesBeforeInsertSize + 1);
    }

    public void testInsertCategories() throws Exception {
        List<Category> categoriesBeforeInsert = dbCategoryController.getAll();
        int categoriesBeforeInsertSize = categoriesBeforeInsert.size();

        dbCategoryController.insertAll(Arrays.asList(this.category1, this.category1));
        Assert.assertEquals(categoriesBeforeInsertSize, categoriesBeforeInsertSize + 2);
    }

    private Category createTestCategory(int id, String name) {
        Category category = new Category();
        category.setId(id);
        category.setCreationDate(new Date(System.currentTimeMillis()));
        category.setName(name);
        category.setUserId(0);
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
