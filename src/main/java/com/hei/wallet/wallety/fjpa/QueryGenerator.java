package com.hei.wallet.wallety.fjpa;

import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class QueryGenerator<T> {
    private final ReflectEntity<T> reflectEntity;
    private static List<String> usedRelations = new ArrayList<>();
    private static List<Relation> relations = new ArrayList<>();

    public String getSqlFormat(ReflectAttribute attribute, boolean isUpdate){
        return isUpdate ? attribute.getSqlColumnName() : String.format("%s.%s", attribute.getSqlTableName(), attribute.getSqlColumnName());
    }

    public String configure(String query){
        return configure(query, false);
    }

    public String configure(String query, boolean isUpdate){
        query = query
                    .replace("@entity.", "@")
                    .replace("@entity", reflectEntity.getSqlTableName())
                    .replace("@id", getSqlFormat(reflectEntity.getIdAttribute(), isUpdate));

        for(ReflectAttribute attribute: reflectEntity.getAttributes()) {
            if (!query.contains("@")) {
                break;
            }
            query = query.replace(
                String.format("@%s", attribute.getFieldName()),
                getSqlFormat(attribute, isUpdate)
            );
        }

        return query;
    }

    public String getFields(){
        return reflectEntity
                .getAttributes()
                .stream().filter(attribute -> (
                    !usedRelations.contains(getRelationName(attribute)) &&
                    !usedRelations.contains(getReverseRelationName(attribute))
                )).map(attribute -> {
                    StringBuilder simpleFields = new StringBuilder();

                    simpleFields.append(String.format(
                        "%s.%s as \"%s.%s\"",
                        attribute.getSqlTableName(),
                        attribute.getSqlColumnName(),
                        attribute.getOriginalTableName(),
                        attribute.getOriginalColumnName()
                    ));

                    if(attribute.isRelation()){
                        final int lastIndex = relations.size();
                        ChildGenerator childGenerator = getAttributeQueryGenerator(attribute);
                        simpleFields.append(" , ");
                        simpleFields.append(childGenerator.getQueryGenerator().getFields());

                        relations.add(lastIndex > 0 ? lastIndex - 1 : 0,
                            Relation
                                .builder()
                                .destination(childGenerator.getReflectEntity().getOriginalTableName())
                                .sqlDestination(childGenerator.getReflectEntity().getSqlTableName())
                                .attribute(attribute)
                                .build()
                        );
                    }
                    return simpleFields.toString();
                }).collect(Collectors.joining(" , "));
    }

    public String selectAll(){
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT ");
        queryBuilder.append(getFields());
        queryBuilder.append(" FROM ");
        queryBuilder.append(reflectEntity.getSqlTableName());

        relations.forEach(relation -> {
            String query = String.format(
                " INNER JOIN %s ON %s.\"%s\" = %s.%s",
                relation.getSqlDestination(),
                relation.getSqlDestination(),
                relation.getAttribute().getRefColumnName(),
                relation.getAttribute().getSqlTableName(),
                relation.getAttribute().getSqlColumnName()
            );
            queryBuilder.append(query);
        });
        usedRelations = new ArrayList<>();
        relations = new ArrayList<>();
        return queryBuilder.toString();
    }

    public ChildGenerator getAttributeQueryGenerator(ReflectAttribute attribute){
        ReflectEntity<?> childEntity = new ReflectEntity<>(attribute.getClazz());
        usedRelations.add(getRelationName(attribute));
        usedRelations.add(getReverseRelationName(attribute));

        QueryGenerator<?> childGenerator = new QueryGenerator<>(childEntity);
        return ChildGenerator
                .builder()
                .queryGenerator(childGenerator)
                .reflectEntity(childEntity)
                .build();
    }

    public static String getRelationName(ReflectAttribute attribute){
        return attribute.getOriginalTableName() + attribute.getFieldName();
    }

    public static String getReverseRelationName(ReflectAttribute attribute){
        return attribute.getFieldName() + attribute.getOriginalTableName();
    }
}