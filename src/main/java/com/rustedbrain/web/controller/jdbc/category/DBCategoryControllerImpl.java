package com.rustedbrain.web.controller.jdbc.category;

import com.rustedbrain.web.model.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBCategoryControllerImpl extends DBCategoryController {


    @Override
    public Category getEntityById(int id) throws SQLException {
        return null;
    }

    @Override
    public void insert(Category entity) throws SQLException {

    }

    @Override
    public void insertAll(List<Category> entities) throws SQLException {

    }

    @Override
    public void update(Category oldEntity, Category newEntity) throws SQLException {

    }

    @Override
    public void delete(Category entity) throws SQLException {

    }

    @Override
    public void deleteAll(List<Category> entities) throws SQLException {

    }

    @Override
    public List<Category> getAll() throws SQLException {
        return null;
    }

    @Override
    protected void fillDeleteStatement(Category user, PreparedStatement deleteStatement) throws SQLException {

    }

    @Override
    protected List<Category> mapSelectResultSetToEntities(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    protected Category mapSelectResultSetToEntity(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    protected void fillGeneratedEntityId(Category entity, ResultSet generatedKeys) throws SQLException {

    }

    @Override
    protected void fillInsertStatement(Category entity, PreparedStatement insertStatement) throws SQLException {

    }

    @Override
    protected void fillUpdateStatement(Category oldEntity, Category newEntity, PreparedStatement updateStatement) throws SQLException {

    }

    @Override
    public List<Category> getCategories(int currentPage, int categoriesPerPage) {
        return null;
    }
}
