package com.hei.wallet.wallety.fjpa;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QueryGenerator<T> {
    private final ReflectEntity<T> reflectEntity;

    public String configure(String query){
        query = query.replaceAll("@entity.", "@");
        for(ReflectAttribute attribute: reflectEntity.getAttributes()){
            if(!query.contains("@")){
                break;
            }

            query = query.replaceAll(
                String.format("@%s", attribute.getFieldName()),
                attribute.getColumnName()
            );
        }

        return query
                .replaceAll("@entity", reflectEntity.getTableName())
                .replaceAll("@id", reflectEntity.getIdAttribute().getColumnName());
    }
}
