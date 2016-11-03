package com.rustedbrain.web.controller.jdbc.util;


import com.rustedbrain.web.controller.resource.Manager;
import com.rustedbrain.web.controller.util.jaxb.Parser;
import com.rustedbrain.web.controller.util.sql.ObjectToSqlSequenceMapper;
import com.rustedbrain.web.controller.util.sql.ObjectToSqlTableMapper;
import com.rustedbrain.web.model.jaxb.sequence.Sequence;
import com.rustedbrain.web.model.jaxb.sequence.Sequences;
import com.rustedbrain.web.model.jaxb.table.*;

import javax.xml.bind.JAXBException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

    private Manager configurationManager;
    private Manager messageManager;
    private Manager sqlManager;
    private Parser parser;

    public DBUtil(Manager configurationManager, Manager messageManager, Manager sqlManager, Parser parser) {
        this.configurationManager = configurationManager;
        this.messageManager = messageManager;
        this.sqlManager = sqlManager;
        this.parser = parser;
    }

    private boolean tableExist(String tableSchema, String tableName, Connection connection) throws SQLException {
        String resourcesSelectTableSql = "database.sql.select.table";
        String sql = sqlManager.getProperty(resourcesSelectTableSql).replace("%1", tableName).replace("%2", tableSchema);
        return checkRowsExistence(sql, connection);
    }

    private boolean sequenceExist(String sequenceName, Connection connection) throws SQLException {
        String resourcesSelectTableSql = "database.sql.select.sequence";
        String sql = sqlManager.getProperty(resourcesSelectTableSql).replace("%1", sequenceName);
        return checkRowsExistence(sql, connection);
    }

    private boolean checkRowsExistence(String sql, Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        boolean isExist = resultSet.isBeforeFirst();

        statement.close();
        resultSet.close();
        return isExist;
    }

    public void checkTableExistence(String tableSchema, String tableName, Connection connection) throws SQLException, JAXBException {
        if (!tableExist(tableSchema, tableName, connection)) {
            createTable(tableSchema, tableName, connection);
        }
    }

    private void createTable(String tableSchema, String tableName, Connection connection) throws JAXBException, SQLException {
        Table table = getTableFromXml(tableSchema, tableName);

        if (table.getForeignKeys() != null && !table.getForeignKeys().isEmpty()) {
            for (ForeignKey dependentTableForeignKey : table.getForeignKeys()) {
                if (!tableExist(dependentTableForeignKey.getDependentTableSchema(), dependentTableForeignKey.getDependentTable(), connection)) {
                    createTable(dependentTableForeignKey.getDependentTableSchema(), dependentTableForeignKey.getDependentTable(), connection);
                }
            }
        }
        for (Column column : table.getColumns()) {
            if (column.getConstraints() != null && !column.getConstraints().isEmpty()) {
                for (ColumnConstraint columnConstraint : column.getConstraints()) {
                    if (columnConstraint.getDependent() != null && !columnConstraint.getDependent().isEmpty()) {
                        if (!sequenceExist(columnConstraint.getDependent(), connection)) {
                            createSequence(columnConstraint.getDependent(), connection);
                        }
                    }
                }
            }
        }
        ObjectToSqlTableMapper mapper = new ObjectToSqlTableMapper();
        String sql = mapper.convert(table);
        Statement statement = connection.createStatement();
        statement.execute(sql);
        statement.close();
    }

    private void createSequence(String dependentSequence, Connection connection) throws JAXBException, SQLException {
        ObjectToSqlSequenceMapper mapper = new ObjectToSqlSequenceMapper();

        Sequence sequence = getSequenceFromXml(dependentSequence);

        String sql = mapper.convert(sequence);

        Statement statement = connection.createStatement();
        statement.execute(sql);
        statement.close();
    }

    private Sequence getSequenceFromXml(String sequenceName) throws JAXBException {
        String resourcesSequencesPathXml = "sequences.xml.path";
        Sequences sequences = (Sequences) parser.getObject(Paths.get(configurationManager.getProperty(resourcesSequencesPathXml)).toFile(), Sequences.class);

        for (Sequence sequence : sequences.getSequences()) {
            if (sequence.getName().equals(sequenceName)) {
                return sequence;
            }
        }
        String resourcesSequenceNotFoundXmlMessage = "sequences.xml.found.error";
        throw new IllegalArgumentException(messageManager.getProperty(resourcesSequenceNotFoundXmlMessage).replace("%1", sequenceName));
    }

    private Table getTableFromXml(String tableSchema, String tableName) throws JAXBException {
        String resourcesTablesPathXml = "tables.xml.path";
        Tables tables = (Tables) parser.getObject(Paths.get(configurationManager.getProperty(resourcesTablesPathXml)).toFile(), Tables.class);

        for (Table table : tables.getTables()) {
            if (table.getName().equals(tableName) && table.getSchema().equals(tableSchema)) {
                return table;
            }
        }
        String resourcesTableNotFoundXmlMessage = "tables.xml.found.error";
        throw new IllegalArgumentException(messageManager.getProperty(resourcesTableNotFoundXmlMessage).replace("%1", tableName).replace("%2", tableSchema));
    }
}
