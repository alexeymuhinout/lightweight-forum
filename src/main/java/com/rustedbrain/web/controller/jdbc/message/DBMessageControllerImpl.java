package com.rustedbrain.web.controller.jdbc.message;

import com.rustedbrain.web.controller.jdbc.DBConnector;
import com.rustedbrain.web.controller.jdbc.util.DBUtil;
import com.rustedbrain.web.controller.resource.Manager;
import com.rustedbrain.web.model.jdbc.Category;
import com.rustedbrain.web.model.jdbc.Message;
import com.rustedbrain.web.model.jdbc.Subcategory;
import com.rustedbrain.web.model.jdbc.User;
import com.rustedbrain.web.model.servlet.UserMessage;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.xml.bind.JAXBException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DBMessageControllerImpl extends DBMessageController {

    private Manager sqlManager;
    private DBConnector dbConnector;
    private DBUtil dbUtil;

    public DBMessageControllerImpl(Manager sqlManager, DBConnector dbConnector, DBUtil dbUtil) {
        this.sqlManager = sqlManager;
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
        String sqlSelect = sqlManager.getProperty("database.sql.select.message.id").replace("%1", String.valueOf(id));
        return executeSelectEntity(dbConnector, sqlSelect);
    }

    @Override
    public void insert(Message entity) throws SQLException {
        this.insertAll(Collections.singletonList(entity));
    }

    @Override
    public void insertAll(List<Message> entities) throws SQLException {
        checkTableExistence();
        String sqlInsert = sqlManager.getProperty("database.sql.insert.messages");
        executePreparedInsert(dbConnector, sqlInsert, entities);
    }

    @Override
    public void update(Message oldEntity, Message newEntity) throws SQLException {
        checkTableExistence();
        String sqlUpdate = sqlManager.getProperty("database.sql.update.messages");
        executePreparedUpdate(dbConnector, oldEntity, newEntity, sqlUpdate);
    }

    @Override
    public void delete(Message message) throws SQLException {
        this.deleteAll(Collections.singletonList(message));
    }

    @Override
    public void deleteAll(List<Message> entities) throws SQLException {
        checkTableExistence();
        String sqlDelete = sqlManager.getProperty("database.sql.delete.messages");
        executePreparedDelete(dbConnector, sqlDelete, entities);
    }

    @Override
    public List<Message> getAll() throws SQLException {
        checkTableExistence();
        String sqlSelect = sqlManager.getProperty("database.sql.select.messages");
        return executeSelectEntities(dbConnector, sqlSelect);
    }

    @Override
    protected void fillDeleteStatement(Message message, PreparedStatement deleteStatement) throws SQLException {
        deleteStatement.setInt(1, message.getId());
    }

    @Override
    protected Message mapSelectResultSetToEntity(ResultSet resultSet) throws SQLException {
        Message message = new Message();
        message.setId(resultSet.getInt(1));
        message.setCreationDate(resultSet.getDate(2));
        message.setSubcategoryId(resultSet.getInt(3));
        message.setUserId(resultSet.getInt(4));
        int replyToUserId = resultSet.getInt(5);
        if (resultSet.wasNull()) {
            message.setReplyToUserId(null);
        } else {
            message.setReplyToUserId(replyToUserId);
        }
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
        if (entity.getReplyToUserId() == null) {
            insertStatement.setNull(4, Types.INTEGER);
        } else {
            insertStatement.setInt(4, entity.getReplyToUserId());
        }
        insertStatement.setString(5, entity.getText());
    }

    @Override
    protected void fillUpdateStatement(Message oldEntity, Message newEntity, PreparedStatement updateStatement) throws SQLException {
        updateStatement.setDate(1, newEntity.getCreationDate());
        updateStatement.setInt(2, newEntity.getSubcategoryId());
        updateStatement.setInt(3, newEntity.getUserId());
        if (newEntity.getReplyToUserId() == null) {
            updateStatement.setNull(4, Types.INTEGER);
        } else {
            updateStatement.setInt(4, newEntity.getReplyToUserId());
        }
        updateStatement.setString(5, newEntity.getText());
        updateStatement.setInt(6, oldEntity.getId());
    }

    @Override
    public List<Message> getMessages(Category category) {
        throw new NotImplementedException();
    }

    @Override
    public List<Message> getMessages(Subcategory subcategory) {
        throw new NotImplementedException();
    }

    @Override
    public List<Message> getMessages(User user) {
        throw new NotImplementedException();
    }

    @Override
    public List<UserMessage> getUserMessages(Integer subcategoryId) throws SQLException {
        checkTableExistence();
        String sqlSelect = sqlManager.getProperty("database.sql.select.messages.user").replace("%1", String.valueOf(subcategoryId));

        Connection connection = dbConnector.getConnection();
        List<UserMessage> userMessages = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlSelect)) {
            userMessages.addAll(mapSelectResultSetToUserMessages(resultSet));
        }

        return userMessages;
    }

    private Collection<? extends UserMessage> mapSelectResultSetToUserMessages(ResultSet resultSet) throws SQLException {
        List<UserMessage> userMessages = new ArrayList<>();
        while (resultSet.next()) {
            UserMessage userMessage = new UserMessage();
            userMessage.setMessage(mapSelectResultSetToEntity(resultSet));
            userMessage.setSubcategoryName(resultSet.getString(7));
            userMessage.setSenderName(resultSet.getString(8));
            userMessage.setSenderRegistrationDate(resultSet.getDate(9));
            userMessage.setReceiverName(resultSet.getString(10));
            userMessages.add(userMessage);
        }
        return userMessages;
    }
}
