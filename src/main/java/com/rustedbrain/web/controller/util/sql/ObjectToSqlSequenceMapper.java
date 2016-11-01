package com.rustedbrain.web.controller.util.sql;

import com.rustedbrain.web.model.jaxb.sequence.Sequence;

public class ObjectToSqlSequenceMapper extends ObjectToSqlMapper<Sequence> {

    @Override
    public String convert(Sequence entity) throws IllegalArgumentException {
        checkArguments(entity);

        StringBuilder builder = new StringBuilder();

        builder.append("CREATE SEQUENCE").append(" ").append(entity.getSchema()).append(".").append(entity.getName()).append(" ").append("\n");
        builder.append("INCREMENT").append(" ").append(entity.getIncrementByValue()).append(" ").append("\n");
        builder.append("START").append(" ").append(entity.getStartValue()).append(" ").append("\n");
        builder.append("MINVALUE").append(" ").append(entity.getMinValue()).append(" ").append("\n");
        builder.append("MAXVALUE").append(" ").append(entity.getMaxValue()).append(" ").append("\n");
        builder.append("CACHE").append(" ").append(entity.getCacheValue()).append(";");

        for (String owner : entity.getOwners()) {
            builder.append("\n").append("ALTER SEQUENCE").append(" ").append(entity.getSchema()).append(".").append(entity.getName())
                    .append(" ").append("OWNER TO").append(" ").append(owner).append(";");
        }

        return builder.toString();
    }

    private void checkArguments(Sequence sequence) throws IllegalArgumentException {
        if (sequence.getName() == null) {
            throw new IllegalArgumentException("Sequence name cannot be null");
        }
        if (sequence.getMaxValue() <= 0) {
            throw new IllegalArgumentException("Sequence max value cannot be less then zero");
        }
        if (sequence.getStartValue() >= sequence.getMaxValue()) {
            throw new IllegalArgumentException("Sequence max value cannot be less then start value");
        }
        if (sequence.getIncrementByValue() == 0) {
            throw new IllegalArgumentException("Sequence increment value cannot be zero");
        }
    }
}
