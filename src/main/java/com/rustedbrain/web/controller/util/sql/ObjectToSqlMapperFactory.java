package com.rustedbrain.web.controller.util.sql;

import com.rustedbrain.web.model.jaxb.sequence.Sequence;
import com.rustedbrain.web.model.jaxb.table.Table;

public class ObjectToSqlMapperFactory {

    public ObjectToSqlMapper getMapper(Object object) throws IllegalArgumentException {
        if (object instanceof Sequence) {
            return new ObjectToSqlSequenceMapper();
        } else if (object instanceof Table) {
            return new ObjectToSqlTableMapper();
        }
        throw new IllegalArgumentException();
    }

}
