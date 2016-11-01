package com.rustedbrain.web.model.jaxb.table;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Column {

    @XmlElement(name = "column-name")
    private String name;
    @XmlElement(name = "data-type")
    private String type;

    @XmlElementWrapper(name = "column-constraints")
    @XmlElement(name = "constraint")
    private List<ColumnConstraint> constraints;

    public Column() {
    }

    public Column(String name, String type, List<ColumnConstraint> constraints) {
        this.name = name;
        this.type = type;
        this.constraints = constraints;
    }

    public Column(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public List<ColumnConstraint> getConstraints() {
        return constraints;
    }

    public void setConstraints(List<ColumnConstraint> constraints) {
        this.constraints = constraints;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Column{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", constraints='" + constraints + '\'' +
                '}';
    }
}
