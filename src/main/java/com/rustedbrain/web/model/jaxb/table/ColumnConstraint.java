package com.rustedbrain.web.model.jaxb.table;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ColumnConstraint {

    @XmlElement(name = "definition")
    private String definition;
    @XmlElement(name = "dependent")
    private String dependent;

    public ColumnConstraint() {
    }

    public ColumnConstraint(String definition) {
        this.definition = definition;
    }

    public ColumnConstraint(String definition, String dependent) {
        this.definition = definition;
        this.dependent = dependent;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getDependent() {
        return dependent;
    }

    public void setDependent(String dependent) {
        this.dependent = dependent;
    }
}
