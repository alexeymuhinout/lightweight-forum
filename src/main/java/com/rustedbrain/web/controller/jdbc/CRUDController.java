package com.rustedbrain.web.controller.jdbc;

import java.util.List;

public interface CRUDController<T> {

    void create(T entity);

    void update(T oldEntity, T newEntity);

    void delete(T entity);

    List<T> getAllEntities();
}
