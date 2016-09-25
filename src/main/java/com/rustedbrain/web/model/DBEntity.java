package com.rustedbrain.web.model;

import java.util.Date;

public class DBEntity implements Comparable<DBEntity> {

    private int id;
    private Date creationDate;

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DBEntity entity = (DBEntity) o;

        return getId() == entity.getId();

    }

    @Override
    public int hashCode() {
        return getId();
    }

    public int compareTo(DBEntity o) {
        return this.getCreationDate().compareTo(o.getCreationDate());
    }
}
