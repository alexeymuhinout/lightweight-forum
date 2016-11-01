package com.rustedbrain.web.controller.util.sql;

import com.rustedbrain.web.controller.util.jaxb.JaxbParser;
import com.rustedbrain.web.controller.util.jaxb.Parser;
import com.rustedbrain.web.model.jaxb.table.*;

import javax.xml.bind.JAXBException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class ObjectToSqlTableMapper extends ObjectToSqlMapper<Table> {

    private final String foreignKeyString = "FOREIGN KEY";
    private final String constraintString = "CONSTRAINT";
    private final String referencesString = "REFERENCES";

    public static void main(String[] args) throws JAXBException {
        ObjectToSqlTableMapper mapper = new ObjectToSqlTableMapper();
        Parser parser = new JaxbParser();
        Tables tables = (Tables) parser.getObject(Paths.get("C:\\Work\\IdeaProjects\\lightweight-forum\\resources\\tables.xml").toFile(), Tables.class);

        for (Table table : tables.getTables()) {
            System.out.println(mapper.convert(table));
        }
    }

    @Override
    public String convert(Table entity) throws IllegalArgumentException {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE").append(" ").append(entity.getSchema()).append(".\"").append(entity.getName()).append("\"");
        builder.append("\n").append("(").append("\n");

        StringJoiner joiner = new StringJoiner(",\n");

        Optional<List<Column>> optionalColumns = Optional.ofNullable(entity.getColumns());
        if (optionalColumns.isPresent()) {
            fillJoinerByColumns(optionalColumns.get(), joiner);
        }

        Optional<List<TableConstraint>> optionalTableConstraints = Optional.ofNullable(entity.getTableConstraints());
        if (optionalTableConstraints.isPresent()) {
            fillJoinerByConstraints(optionalTableConstraints.get(), joiner);
        }

        Optional<List<ForeignKey>> optionalForeignKeys = Optional.ofNullable(entity.getForeignKeys());
        if (optionalForeignKeys.isPresent()) {
            fillJoinerByForeignKeys(optionalForeignKeys.get(), joiner);
        }

        builder.append(joiner.toString());

        builder.append("\n").append(")").append("\n");
        builder.append("WITH (OIDS = ").append(String.valueOf(entity.isOids()).toUpperCase()).append(") ").append("\n");
        builder.append("TABLESPACE").append(" ").append(entity.getNamespace()).append(";\n");

        Optional<List<String>> optionalOwners = Optional.ofNullable(entity.getOwners());
        if (optionalOwners.isPresent()) {
            builder.append(createUsersPrivilegesSql(entity.getOwners(), entity));
        }

        return builder.toString();
    }

    @SuppressWarnings("all")
    private void fillJoinerByForeignKeys(List<ForeignKey> foreignKeys, StringJoiner joiner) throws IllegalArgumentException {
        Optional<ForeignKey> nonNullConstraints = foreignKeys.stream().filter(Objects::isNull).findAny();
        throwExceptionIfNotPresent(!nonNullConstraints.isPresent(), "Foreign key cannot be null");

        for (ForeignKey foreignKey : foreignKeys) {
            StringBuilder tableSqlBuilder = new StringBuilder();

            Optional<String> optionalForeignKeyName = Optional.ofNullable(foreignKey.getName());
            throwExceptionIfNotPresent(optionalForeignKeyName.isPresent(), "Foreign key name cannot be null");

            Optional<String> optionalForeignKeyLocalColumn = Optional.ofNullable(foreignKey.getLocalColumn());
            throwExceptionIfNotPresent(optionalForeignKeyLocalColumn.isPresent(), "Foreign key local column cannot be null");

            Optional<String> optionalForeignKeyDependentTable = Optional.ofNullable(foreignKey.getDependentTable());
            throwExceptionIfNotPresent(optionalForeignKeyDependentTable.isPresent(), "Foreign key dependent table cannot be null");

            Optional<String> optionalForeignKeyDependentColumn = Optional.ofNullable(foreignKey.getDependentColumn());
            throwExceptionIfNotPresent(optionalForeignKeyDependentColumn.isPresent(), "Foreign key dependent column cannot be null");

            Optional<String> optionalForeignKeyRestriction = Optional.ofNullable(foreignKey.getRestriction());
            throwExceptionIfNotPresent(optionalForeignKeyRestriction.isPresent(), "Foreign key restriction cannot be null");

            tableSqlBuilder.append(constraintString).append(" ").append(optionalForeignKeyName.get()).append(" ")
                    .append(foreignKeyString).append(" (").append(optionalForeignKeyLocalColumn.get()).append(") ")
                    .append(referencesString).append(" ");

            if (foreignKey.getDependentTableSchema() != null) {
                tableSqlBuilder.append(foreignKey.getDependentTableSchema()).append(".");
            }

            tableSqlBuilder.append("\"").append(optionalForeignKeyDependentTable.get()).append("\" (")
                    .append(optionalForeignKeyDependentColumn.get())
                    .append(") ").append(optionalForeignKeyRestriction.get());

            joiner.add(tableSqlBuilder.toString());
        }
    }

    private String createUsersPrivilegesSql(List<String> owners, Table table) {

        String usersPrivileges = "";
        Optional<String> optionalNonNullOwners = owners.stream().filter(Objects::nonNull).findAny();
        if (optionalNonNullOwners.isPresent()) {

            List<String> nonNullOwners = owners.stream().filter(Objects::nonNull).collect(Collectors.toList());
            StringBuilder ownerStringBuilder = new StringBuilder();

            for (String owner : nonNullOwners) {
                ownerStringBuilder.append("\n").append("ALTER TABLE").append(" ").append(table.getSchema()).append(".\"").append(table.getName()).append("\" ").append("OWNER to").append(" ").append(owner).append(";");
            }

            usersPrivileges = ownerStringBuilder.toString();
        }

        return usersPrivileges;
    }

    private void fillJoinerByColumns(List<Column> columns, StringJoiner joiner) throws IllegalArgumentException {
        Optional<Column> nullColumn = columns.stream().filter(Objects::isNull).findAny();
        throwExceptionIfNotPresent(!nullColumn.isPresent(), "Table columns cannot be null");

        for (Column column : columns) {

            Optional<String> optionalColumnName = Optional.ofNullable(column.getName());
            throwExceptionIfNotPresent(optionalColumnName.isPresent(), "Column name cannot be null");

            Optional<String> optionalColumnType = Optional.ofNullable(column.getType());
            throwExceptionIfNotPresent(optionalColumnName.isPresent(), "Column type cannot be null");

            StringBuilder tableSqlBuilder = new StringBuilder();

            tableSqlBuilder.append(column.getName()).append(" ").append(column.getType());

            Optional<List<ColumnConstraint>> optionalConstraints = Optional.ofNullable(column.getConstraints());

            if (optionalConstraints.isPresent()) {
                List<ColumnConstraint> nonNullColumnConstraints = optionalConstraints.get().stream().filter(Objects::nonNull).collect(Collectors.toList());
                for (ColumnConstraint constraint : nonNullColumnConstraints) {
                    tableSqlBuilder.append(" ").append(constraint.getDefinition());
                }
            }
            joiner.add(tableSqlBuilder.toString());
        }
    }

    private void fillJoinerByConstraints(List<TableConstraint> tableConstraints, StringJoiner joiner) {
        Optional<TableConstraint> optionalNullTableConstraint = tableConstraints.stream().filter(Objects::isNull).findAny();
        throwExceptionIfNotPresent(!optionalNullTableConstraint.isPresent(), "Table constraints cannot be null");

        for (TableConstraint tableConstraint : tableConstraints) {
            StringBuilder tableSqlBuilder = new StringBuilder();

            Optional<String> optionalConstraintName = Optional.ofNullable(tableConstraint.getName());
            throwExceptionIfNotPresent(optionalConstraintName.isPresent(), "Constraint name cannot be null");

            Optional<String> optionalConstraintDefinition = Optional.ofNullable(tableConstraint.getDefinition());
            throwExceptionIfNotPresent(optionalConstraintDefinition.isPresent(), "Constraint definition cannot be null");

            tableSqlBuilder.append(constraintString).append(" ").append(tableConstraint.getName()).append(" ")
                    .append(tableConstraint.getDefinition());
            joiner.add(tableSqlBuilder.toString());
        }
    }

    private void throwExceptionIfNotPresent(Boolean isPresent, String errorMessage) {
        if (!isPresent)
            throw new IllegalArgumentException(errorMessage);
    }
}
