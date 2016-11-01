package com.rustedbrain.web.model.jaxb.table;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ForeignKey {

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "local-column")
    private String localColumn;

    @XmlElement(name = "dependent-table-schema")
    private String dependentTableSchema;

    @XmlElement(name = "dependent-table")
    private String dependentTable;

    @XmlElement(name = "dependent-column")
    private String dependentColumn;

    @XmlElement(name = "restriction")
    private String restriction;

    public ForeignKey() {
    }

    public ForeignKey(String name, String localColumn, String dependentTableSchema, String dependentTable, String dependentColumn, String restriction) {
        this.name = name;
        this.localColumn = localColumn;
        this.dependentTableSchema = dependentTableSchema;
        this.dependentTable = dependentTable;
        this.dependentColumn = dependentColumn;
        this.restriction = restriction;
    }

    public String getDependentTableSchema() {
        return dependentTableSchema;
    }

    public void setDependentTableSchema(String dependentTableSchema) {
        this.dependentTableSchema = dependentTableSchema;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocalColumn() {
        return localColumn;
    }

    public void setLocalColumn(String localColumn) {
        this.localColumn = localColumn;
    }

    public String getDependentTable() {
        return dependentTable;
    }

    public void setDependentTable(String dependentTable) {
        this.dependentTable = dependentTable;
    }

    public String getDependentColumn() {
        return dependentColumn;
    }

    public void setDependentColumn(String dependentColumn) {
        this.dependentColumn = dependentColumn;
    }

    public String getRestriction() {
        return restriction;
    }

    public void setRestriction(String restriction) {
        this.restriction = restriction;
    }
}
