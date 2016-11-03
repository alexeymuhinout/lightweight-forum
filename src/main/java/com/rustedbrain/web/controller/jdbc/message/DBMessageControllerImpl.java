package com.rustedbrain.web.controller.jdbc.message;

import com.rustedbrain.web.controller.jdbc.DBConnector;
import com.rustedbrain.web.controller.jdbc.util.DBUtil;
import com.rustedbrain.web.controller.resource.Manager;
import com.rustedbrain.web.model.jdbc.Category;
import com.rustedbrain.web.model.jdbc.Message;
import com.rustedbrain.web.model.jdbc.Subcategory;
import com.rustedbrain.web.model.jdbc.User;

import javax.xml.bind.JAXBException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DBMessageControllerImpl extends DBMessageController {

    private Manager configurationManager;
    private DBConnector dbConnector;
    private DBUtil dbUtil;

    public DBMessageControllerImpl(Manager configurationManager, DBConnector dbConnector, DBUtil dbUtil) {
        this.configurationManager = configurationManager;
        this.dbConnector = dbConnector;
        this.dbUtil = dbUtil;
    }

    @Override
    public void checkTableExistence() throws SQLException {
        Connection connection = dbConnector.getConnection();
        connection.setAutoCommit(false);
        try {
            dbUtil.checkTableExistence("public", "message", connection);
            connection.commit();
        } catch (JAXBException e) {
            connection.rollback();
            e.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public Message getEntityById(int id) throws SQLException {
        checkTableExistence();
        String sqlSelect = configurationManager.getProperty("database.sql.select.message.id").replace("%1", String.valueOf(id));
        return executeSelectEntity(dbConnector, sqlSelect);
    }

    @Override
    public void insert(Message entity) throws SQLException {
        this.insertAll(Collections.singletonList(entity));
    }

    @Override
    public void insertAll(List<Message> entities) throws SQLException {
        checkTableExistence();
        String sqlInsert = configurationManager.getProperty("database.sql.insert.messages");
        executePreparedInsert(dbConnector, sqlInsert, entities);
    }

    @Override
    public void update(Message oldEntity, Message newEntity) throws SQLException {
        checkTableExistence();
        String sqlUpdate = configurationManager.getProperty("database.sql.update.messages");
        executePreparedUpdate(dbConnector, oldEntity, newEntity, sqlUpdate);
    }

    @Override
    public void delete(Message entity) throws SQLException {
        this.deleteAll(Collections.singletonList(entity));
    }

    @Override
    public void deleteAll(List<Message> entities) throws SQLException {
        checkTableExistence();
        String sqlDelete = configurationManager.getProperty("database.sql.delete.messages");
        executePreparedDelete(dbConnector, sqlDelete, entities);
    }

    @Override
    public List<Message> getAll() throws SQLException {
        checkTableExistence();
        String sqlSelect = configurationManager.getProperty("database.sql.select.messages");
        return executeSelectEntities(dbConnector, sqlSelect);
    }

    @Override
    protected void fillDeleteStatement(Message user, PreparedStatement deleteStatement) throws SQLException {
        deleteStatement.setInt(1, user.getUserId());
    }

    @Override
    protected List<Message> mapSelectResultSetToEntities(ResultSet resultSet) throws SQLException {
        List<Message> messages = new ArrayList<>();
        while (resultSet.next()) {
            Message message = new Message();
            message.setId(resultSet.getInt(1));
            message.setCreationDate(resultSet.getDate(2));
            message.setSubcategoryId(resultSet.getInt(3));
            message.setUserId(resultSet.getInt(4));
            message.setReplyToUserId(resultSet.getInt(5));
            message.setText(resultSet.getString(6));
            messages.add(message);
        }
        return messages;
    }

    @Override
    protected Message mapSelectResultSetToEntity(ResultSet resultSet) throws SQLException {
        Message message = new Message();
        message.setId(resultSet.getInt(1));
        message.setCreationDate(resultSet.getDate(2));
        message.setSubcategoryId(resultSet.getInt(3));
        message.setUserId(resultSet.getInt(4));
        message.setReplyToUserId(resultSet.getInt(5));
        message.setText(resultSet.getString(6));
        return message;
    }

    @Override
    protected void fillGeneratedEntityId(Message entity, ResultSet generatedKeys) throws SQLException {
        entity.setId(generatedKeys.getInt(1));
    }

    @Override
    protected void fillInsertStatement(Message entity, PreparedStatement insertStatement) throws SQLException {
        insertStatement.setDate(1, entity.getCreationDate());
        insertStatement.setInt(2, entity.getSubcategoryId());
        insertStatement.setInt(3, entity.getUserId());
        insertStatement.setInt(4, entity.getReplyToUserId());
        insertStatement.setString(5, entity.getText());
    }

    @Override
    protected void fillUpdateStatement(Message oldEntity, Message newEntity, PreparedStatement updateStatement) throws SQLException {
        updateStatement.setDate(1, newEntity.getCreationDate());
        updateStatement.setInt(2, newEntity.getSubcategoryId());
        updateStatement.setInt(3, newEntity.getUserId());
        updateStatement.setInt(4, newEntity.getReplyToUserId());
        updateStatement.setString(5, newEntity.getText());
        updateStatement.setInt(6, oldEntity.getId());
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
