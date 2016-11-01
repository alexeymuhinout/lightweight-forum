package com.rustedbrain.web.model.jaxb.table;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class TableConstraint {

    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "definition")
    private String definition;

    public TableConstraint() {
    }

    public TableConstraint(String name, String definition) {
        this.name = name;
        this.definition = definition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    @Override
    public String toString() {
        return "TableConstraint{" +
                "name='" + name + '\'' +
                ", definition='" + definition + '\'' +
                '}';
    }
}
