package com.rustedbrain.web.model;

public abstract class DBEntity implements Cloneable {

    private int id;

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

        DBEntity dbEntity = (DBEntity) o;

        return id == dbEntity.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "DBEntity{" +
                "id=" + id +
                '}';
    }
}
