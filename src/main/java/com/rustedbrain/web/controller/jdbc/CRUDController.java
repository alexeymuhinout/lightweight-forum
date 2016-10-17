package com.rustedbrain.web.controller.jdbc;

import java.sql.SQLException;
import java.util.List;

public interface CRUDController<T> {

    void insert(T entity) throws SQLException;

    void update(T oldEntity, T newEntity) throws SQLException;

    void delete(T entity) throws SQLException;

    List<T> getAllEntities();
}
