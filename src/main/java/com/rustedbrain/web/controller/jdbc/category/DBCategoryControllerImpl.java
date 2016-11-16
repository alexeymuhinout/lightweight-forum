package com.rustedbrain.web.controller.jdbc.category;

import com.rustedbrain.web.controller.jdbc.DBConnector;
import com.rustedbrain.web.controller.jdbc.util.DBUtil;
import com.rustedbrain.web.controller.resource.Manager;
import com.rustedbrain.web.model.jdbc.Category;
import com.rustedbrain.web.model.servlet.ModeratedCategory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.xml.bind.JAXBException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DBCategoryControllerImpl extends DBCategoryController {

    private Manager sqlManager;
    private DBConnector dbConnector;
    private DBUtil dbUtil;

    public DBCategoryControllerImpl(Manager sqlManager, DBConnector dbConnector, DBUtil dbUtil) {
        this.sqlManager = sqlManager;
        this.dbConnector = dbConnector;
        this.dbUtil = dbUtil;
    }

    @Override
    public void checkTableExistence() throws SQLException {
        Connection connection = dbConnector.getConnection();
        connection.setAutoCommit(false);
        try {
            dbUtil.checkTableExistence("public", "category", connection);
            connection.commit();
        } catch (JAXBException e) {
            connection.rollback();
            e.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public Category getEntityById(int id) throws SQLException {
        checkTableExistence();
        String sqlSelect = sqlManager.getProperty("database.sql.select.category.id").replace("%1", String.valueOf(id));
        return executeSelectEntity(dbConnector, sqlSelect);
    }

    @Override
    public void insert(Category entity) throws SQLException {
        this.insertAll(Collections.singletonList(entity));
    }

    @Override
    public void insertAll(List<Category> entities) throws SQLException {
        checkTableExistence();
        String sqlInsert = sqlManager.getProperty("database.sql.insert.categories");
        executePreparedInsert(dbConnector, sqlInsert, entities);
    }

    @Override
    public void update(Category oldEntity, Category newEntity) throws SQLException {
        checkTableExistence();
        String sqlUpdate = sqlManager.getProperty("database.sql.update.categories");
        executePreparedUpdate(dbConnector, oldEntity, newEntity, sqlUpdate);
    }

    @Override
    public void delete(Category entity) throws SQLException {
        this.deleteAll(Collections.singletonList(entity));
    }

    @Override
    public void deleteAll(List<Category> entities) throws SQLException {
        checkTableExistence();
        String sqlDelete = sqlManager.getProperty("database.sql.delete.categories");
        executePreparedDelete(dbConnector, sqlDelete, entities);
    }

    @Override
    public List<Category> getAll() throws SQLException {
        checkTableExistence();
        String sqlSelect = sqlManager.getProperty("database.sql.select.categories");
        return executeSelectEntities(dbConnector, sqlSelect);
    }

    @Override
    protected void fillDeleteStatement(Category category, PreparedStatement deleteStatement) throws SQLException {
        deleteStatement.setInt(1, category.getId());
    }

    @Override
    protected Category mapSelectResultSetToEntity(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getInt(1));
        category.setCreationDate(resultSet.getDate(2));
        category.setName(resultSet.getString(3));
        category.setUserId(resultSet.getInt(4));
        return category;
    }

    @Override
    protected void fillGeneratedEntityId(Category entity, ResultSet generatedKeys) throws SQLException {
        entity.setId(generatedKeys.getInt(1));
    }

    @Override
    protected void fillInsertStatement(Category entity, PreparedStatement insertStatement) throws SQLException {
        insertStatement.setDate(1, entity.getCreationDate());
        insertStatement.setString(2, entity.getName());
        insertStatement.setInt(3, entity.getUserId());
    }

    @Override
    protected void fillUpdateStatement(Category oldEntity, Category newEntity, PreparedStatement updateStatement) throws SQLException {
        updateStatement.setDate(1, newEntity.getCreationDate());
        updateStatement.setString(2, newEntity.getName());
        updateStatement.setInt(3, newEntity.getUserId());
        updateStatement.setInt(4, oldEntity.getId());
    }

    @Override
    public List<Category> getCategories(int currentPage, int categoriesPerPage) {
        throw new NotImplementedException();
    }


    @Override
    protected List<ModeratedCategory> mapSelectResultSetToModeratedCategories(ResultSet resultSet) throws SQLException {
        List<ModeratedCategory> categories = new ArrayList<>();
        while (resultSet.next()) {
            ModeratedCategory moderatedCategory = new ModeratedCategory();
            moderatedCategory.setCategory(mapSelectResultSetToEntity(resultSet));
            moderatedCategory.setModerator(resultSet.getString(5));
            categories.add(moderatedCategory);
        }
        return categories;
    }

    @Override
    public List<ModeratedCategory> getModeratedCategories() throws SQLException {
        checkTableExistence();
        String sqlSelect = sqlManager.getProperty("database.sql.select.categories.users");

        Connection connection = dbConnector.getConnection();
        List<ModeratedCategory> moderatedCategories = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlSelect)) {
            moderatedCategories.addAll(mapSelectResultSetToModeratedCategories(resultSet));
        }

        return moderatedCategories;
    }
}
