package com.rustedbrain.web.model;

import java.util.Date;

public class Entity implements Comparable<Entity> {

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

        Entity entity = (Entity) o;

        return getId() == entity.getId();

    }

    @Override
    public int hashCode() {
        return getId();
    }

    public int compareTo(Entity o) {
        return this.getCreationDate().compareTo(o.getCreationDate());
    }
}
