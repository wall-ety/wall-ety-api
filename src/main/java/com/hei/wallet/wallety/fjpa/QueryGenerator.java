package com.hei.wallet.wallety.fjpa;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QueryGenerator<T> {
    private final ReflectEntity<T> reflectEntity;

    public String configure(String query){
        query = query
                    .replace("@entity.", "@")
                    .replace("@entity", reflectEntity.getTableName())
                    .replace("@id", reflectEntity.getIdAttribute().getColumnName());

        for(ReflectAttribute attribute: reflectEntity.getAttributes()) {
            if (!query.contains("@")) {
                break;
            }
            query = query.replace(
                    String.format("@%s", attribute.getFieldName()),
                    attribute.getColumnName()
            );
        }

        return query;
    }
}
