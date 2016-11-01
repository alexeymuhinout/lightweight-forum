package com.rustedbrain.web.controller.jdbc.subcategory;

import com.rustedbrain.web.model.jdbc.Subcategory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBSubcategoryControllerImpl extends DBSubcategoryController {


    @Override
    public Subcategory getEntityById(int id) throws SQLException {
        return null;
    }

    @Override
    public void insert(Subcategory entity) throws SQLException {

    }

    @Override
    public void insertAll(List<Subcategory> entities) throws SQLException {

    }

    @Override
    public void update(Subcategory oldEntity, Subcategory newEntity) throws SQLException {

    }

    @Override
    public void delete(Subcategory entity) throws SQLException {

    }

    @Override
    public void deleteAll(List<Subcategory> entities) throws SQLException {

    }

    @Override
    public List<Subcategory> getAll() throws SQLException {
        return null;
    }

    @Override
    protected void fillDeleteStatement(Subcategory user, PreparedStatement deleteStatement) throws SQLException {

    }

    @Override
    protected List<Subcategory> mapSelectResultSetToEntities(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    protected Subcategory mapSelectResultSetToEntity(ResultSet resultSet) {
        return null;
    }

    @Override
    protected void fillGeneratedEntityId(Subcategory entity, ResultSet generatedKeys) throws SQLException {

    }

    @Override
    protected void fillInsertStatement(Subcategory entity, PreparedStatement insertStatement) throws SQLException {

    }

    @Override
    protected void fillUpdateStatement(Subcategory oldEntity, Subcategory newEntity, PreparedStatement updateStatement) throws SQLException {

    }
}
