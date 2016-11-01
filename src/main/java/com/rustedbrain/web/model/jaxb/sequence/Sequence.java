package com.rustedbrain.web.model.jaxb.sequence;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Sequence {

    @XmlElement(name = "schema")
    private String schema;

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "increment")
    private int incrementByValue;

    @XmlElement(name = "start")
    private int startValue;

    @XmlElement(name = "min-value")
    private int minValue;

    @XmlElement(name = "max-value")
    private long maxValue;

    @XmlElement(name = "cache")
    private int cacheValue;

    @XmlElementWrapper(name = "owners")
    @XmlElement(name = "owner")
    private List<String> owners;

    public List<String> getOwners() {
        return owners;
    }

    public void setOwners(List<String> owners) {
        this.owners = owners;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIncrementByValue() {
        return incrementByValue;
    }

    public void setIncrementByValue(int incrementByValue) {
        this.incrementByValue = incrementByValue;
    }

    public int getStartValue() {
        return startValue;
    }

    public void setStartValue(int startValue) {
        this.startValue = startValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public long getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(long maxValue) {
        this.maxValue = maxValue;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public int getCacheValue() {
        return cacheValue;
    }

    public void setCacheValue(int cacheValue) {
        this.cacheValue = cacheValue;
    }

    @Override
    public String toString() {
        return "Sequence{" +
                "schema='" + schema + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
