package com.rustedbrain.web.model.jaxb.table;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Table {

    @XmlElement(name = "schema")
    private String schema;

    @XmlElement(name = "name")
    private String name;

    @XmlElementWrapper(name = "columns")
    @XmlElement(name = "column")
    private List<Column> columns;

    @XmlElementWrapper(name = "table-constraints")
    @XmlElement(name = "constraint")
    private List<TableConstraint> tableConstraints;

    @XmlElementWrapper(name = "foreign-keys")
    @XmlElement(name = "foreign-key")
    private List<ForeignKey> foreignKeys;

    @XmlElement(name = "namespace")
    private String namespace;

    @XmlElement(name = "oids")
    private boolean oids;

    @XmlElementWrapper(name = "owners")
    @XmlElement(name = "owner")
    private List<String> owners;

    public List<String> getOwners() {
        return owners;
    }

    public void setOwners(List<String> owners) {
        this.owners = owners;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public List<ForeignKey> getForeignKeys() {
        return foreignKeys;
    }

    public void setForeignKeys(List<ForeignKey> foreignKeys) {
        this.foreignKeys = foreignKeys;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public boolean isOids() {
        return oids;
    }

    public void setOids(boolean oids) {
        this.oids = oids;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public List<TableConstraint> getTableConstraints() {
        return tableConstraints;
    }

    public void setTableConstraints(List<TableConstraint> tableConstraints) {
        this.tableConstraints = tableConstraints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Table table = (Table) o;

        return name.equals(table.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Table{" +
                "name='" + name + '\'' +
                ", columns=" + columns +
                ", owners=" + owners +
                '}';
    }
}
