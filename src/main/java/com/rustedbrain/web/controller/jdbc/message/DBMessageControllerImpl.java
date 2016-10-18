package com.rustedbrain.web.controller.jdbc.message;

import com.rustedbrain.web.model.Category;
import com.rustedbrain.web.model.Message;
import com.rustedbrain.web.model.Subcategory;
import com.rustedbrain.web.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBMessageControllerImpl extends DBMessageController {


    @Override
    public Message getEntityById(int id) throws SQLException {
        return null;
    }

    @Override
    public void insert(Message entity) throws SQLException {

    }

    @Override
    public void insertAll(List<Message> entities) throws SQLException {

    }

    @Override
    public void update(Message oldEntity, Message newEntity) throws SQLException {

    }

    @Override
    public void delete(Message entity) throws SQLException {

    }

    @Override
    public void deleteAll(List<Message> entities) throws SQLException {

    }

    @Override
    public List<Message> getAll() throws SQLException {
        return null;
    }

    @Override
    protected void fillDeleteStatement(Message user, PreparedStatement deleteStatement) throws SQLException {

    }

    @Override
    protected List<Message> mapSelectResultSetToEntities(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    protected Message mapSelectResultSetToEntity(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    protected void fillGeneratedEntityId(Message entity, ResultSet generatedKeys) throws SQLException {

    }

    @Override
    protected void fillInsertStatement(Message entity, PreparedStatement insertStatement) throws SQLException {

    }

    @Override
    protected void fillUpdateStatement(Message oldEntity, Message newEntity, PreparedStatement updateStatement) throws SQLException {

    }

    @Override
    public List<Message> getMessages(Category category) {
        return null;
    }

    @Override
    public List<Message> getMessages(Subcategory subcategory) {
        return null;
    }

    @Override
    public List<Message> getMessages(User user) {
        return null;
    }
}
