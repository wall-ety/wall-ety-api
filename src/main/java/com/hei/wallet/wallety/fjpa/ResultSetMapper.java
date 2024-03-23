package com.hei.wallet.wallety.fjpa;

import lombok.AllArgsConstructor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class ResultSetMapper<T>{
    private final ReflectEntity<T> reflectEntity;

    public Object retrieveResultSetValue(ResultSet resultSet, ReflectAttribute attribute) throws SQLException {
        switch (attribute.getClazz().getSimpleName()) {
            case "Instant":
                return resultSet.getTimestamp(attribute.getColumnName()).toInstant();
            case "BigDecimal":
                return resultSet.getBigDecimal(attribute.getColumnName());
            case "float":
                return resultSet.getFloat(attribute.getColumnName());
            default:
                if (!attribute.getClazz().isEnum()) {
                    return resultSet.getObject(attribute.getColumnName());
                }
                String enumString = resultSet.getString(attribute.getColumnName());
                return Enum.valueOf((Class<Enum>) attribute.getClazz(), enumString);
        }
    }

    public T mapResultSetToInstance(ResultSet resultSet, List<Class<?>> excludes) {
        Map<ReflectAttribute, Object> values = new HashMap<>();
        for(ReflectAttribute attribute: reflectEntity.getAttributes()){
            if(attribute.isRelation())
                continue;
            try {
                values.put(attribute, retrieveResultSetValue(resultSet, attribute));
            } catch (SQLException error) {
                throw new RuntimeException(error.getMessage());
            }
        };

        return reflectEntity.createInstance(values);
    }

    public T mapResultSetToInstance(ResultSet resultSet) {
        return mapResultSetToInstance(resultSet, List.of());
    }
}
