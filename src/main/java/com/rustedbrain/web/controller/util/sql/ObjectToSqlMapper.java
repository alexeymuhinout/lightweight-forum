package com.rustedbrain.web.controller.util.sql;

public abstract class ObjectToSqlMapper<T> {

    public abstract String convert(T entity);

}
