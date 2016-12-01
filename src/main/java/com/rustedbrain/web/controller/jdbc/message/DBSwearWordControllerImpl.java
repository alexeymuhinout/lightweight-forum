package com.rustedbrain.web.controller.jdbc.message;

import com.rustedbrain.web.controller.jdbc.DBConnector;
import com.rustedbrain.web.controller.jdbc.util.DBUtil;
import com.rustedbrain.web.controller.resource.Manager;
import com.rustedbrain.web.model.jdbc.SwearWord;

import javax.xml.bind.JAXBException;
import java.sql.*;
import java.util.Collections;
import java.util.List;

public class DBSwearWordControllerImpl extends DBSwearWordController {

    private Manager sqlManager;
    private DBConnector dbConnector;
    private DBUtil dbUtil;

    public DBSwearWordControllerImpl(Manager sqlManager, DBConnector dbConnector, DBUtil dbUtil) {
        this.sqlManager = sqlManager;
        this.dbConnector = dbConnector;
        this.dbUtil = dbUtil;
    }

    @Override
    public void checkTableExistence() throws SQLException {
        try (Connection connection = dbConnector.getConnection()) {
            connection.setAutoCommit(false);
            try {
                dbUtil.checkTableExistence("public", "swear_word", connection);
            } catch (SQLException | JAXBException e) {
                connection.rollback();
                e.printStackTrace();
            }
            connection.commit();
            connection.setAutoCommit(true);
        }
    }

    @Override
    public SwearWord getEntityById(int id) throws SQLException {
        checkTableExistence();
        String sqlSelect = sqlManager.getProperty("database.sql.select.swear-word.id").replace("%1", String.valueOf(id));
        return executeSelectEntity(dbConnector, sqlSelect);
    }

    @Override
    public void insert(SwearWord entity) throws SQLException {
        this.insertAll(Collections.singletonList(entity));
    }

    @Override
    public void insertAll(List<SwearWord> entities) throws SQLException {
        checkTableExistence();
        String sqlInsert = sqlManager.getProperty("database.sql.insert.swear-words");
        executePreparedInsert(dbConnector, sqlInsert, entities);
    }

    @Override
    public void update(SwearWord oldEntity, SwearWord newEntity) throws SQLException {
        checkTableExistence();
        String sqlUpdate = sqlManager.getProperty("database.sql.update.swear-word");
        executePreparedUpdate(dbConnector, oldEntity, newEntity, sqlUpdate);
    }

    @Override
    public void delete(SwearWord swearWord) throws SQLException {
        this.deleteAll(Collections.singletonList(swearWord));
    }

    @Override
    public void deleteAll(List<SwearWord> entities) throws SQLException {
        checkTableExistence();
        String sqlDelete = sqlManager.getProperty("database.sql.delete.swear-words");
        executePreparedDelete(dbConnector, sqlDelete, entities);
    }

    @Override
    public List<SwearWord> getAll() throws SQLException {
        checkTableExistence();
        String sqlSelect = sqlManager.getProperty("database.sql.select.swear-words");
        return executeSelectEntities(dbConnector, sqlSelect);
    }

    @Override
    protected void fillDeleteStatement(SwearWord swearWord, PreparedStatement deleteStatement) throws SQLException {
        deleteStatement.setInt(1, swearWord.getId());
    }

    @Override
    protected SwearWord mapSelectResultSetToEntity(ResultSet resultSet) throws SQLException {
        SwearWord swearWord = new SwearWord();
        swearWord.setId(resultSet.getInt(1));
        swearWord.setCreationDate(resultSet.getDate(2));
        swearWord.setText(resultSet.getString(3));
        return swearWord;
    }

    @Override
    protected void fillGeneratedEntityId(SwearWord swearWord, ResultSet generatedKeys) throws SQLException {
        swearWord.setId(generatedKeys.getInt(1));
    }

    @Override
    protected void fillInsertStatement(SwearWord swearWord, PreparedStatement insertStatement) throws SQLException {
        insertStatement.setDate(1, swearWord.getCreationDate());
        insertStatement.setString(2, swearWord.getText());
    }

    @Override
    protected void fillUpdateStatement(SwearWord oldEntity, SwearWord newEntity, PreparedStatement updateStatement) throws SQLException {
        updateStatement.setDate(1, newEntity.getCreationDate());
        updateStatement.setString(2, newEntity.getText());
        updateStatement.setInt(3, oldEntity.getId());
    }

    @Override
    public boolean isSwearWord(String word) throws SQLException {
        String sqlSelect = sqlManager.getProperty("database.sql.select.swear-word.word").replace("?", word);
        try (Connection connection = dbConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlSelect)) {
            return resultSet.isBeforeFirst();
        }
    }
}
