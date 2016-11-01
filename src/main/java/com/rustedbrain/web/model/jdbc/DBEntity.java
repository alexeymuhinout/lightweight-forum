package com.rustedbrain.web.model.jdbc;

public abstract class DBEntity implements Cloneable {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
