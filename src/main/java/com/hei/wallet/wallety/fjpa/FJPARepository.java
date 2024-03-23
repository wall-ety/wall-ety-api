package com.hei.wallet.wallety.fjpa;

import lombok.Getter;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class FJPARepository <T>{
    private final ReflectEntity<T> reflectEntity;
    private final QueryGenerator<T> queryGenerator;
    private final StatementWrapper statementWrapper;
    private final ResultSetMapper<T> resultSetMapper;
    private final String SELECT_ALL_QUERY;

    @SuppressWarnings("unchecked")
    public FJPARepository(Connection connection) {
        this.reflectEntity = (ReflectEntity<T>) new ReflectEntity<>(this.getClazz());
        this.statementWrapper = new StatementWrapper(connection);
        this.queryGenerator = new QueryGenerator<>(reflectEntity);
        this.resultSetMapper = new ResultSetMapper<>(reflectEntity);
        this.SELECT_ALL_QUERY = queryGenerator.configure(QueryTemplate.selectAll());
    }

    // to get Class<T>
    private Class<?> getClazz(){
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<?>) parameterizedType.getActualTypeArguments()[0];
    }

    public List<T> findAll() throws SQLException {
        return statementWrapper.select(SELECT_ALL_QUERY,null, this.resultSetMapper::mapResultSetToInstance);
    }

    public List<T> findAll(String suffix, List<Object> values) throws SQLException {
        String query = queryGenerator.configure(String.format("%s %s ;", SELECT_ALL_QUERY, suffix));
        return statementWrapper.select(query, values, this.resultSetMapper::mapResultSetToInstance);
    }

    public List<T> findByField(String fieldName, Object fieldValue) throws SQLException {
        return findByField(fieldName, fieldValue, List.of());
    }

    public List<T> findByField(String fieldName, Object fieldValue, List<Class<?>> excludes) throws SQLException {
        return findByField(fieldName, fieldValue, excludes, "");
    }

    protected List<T> findByField(String fieldName, Object fieldValue, List<Class<?>> excludes, String suffix) throws SQLException {
        String query = queryGenerator.configure(
            String.format("%s WHERE %s = ? %s", SELECT_ALL_QUERY , fieldName, suffix)
        );
        return statementWrapper.select(query,List.of(fieldValue), resultSet -> this.resultSetMapper.mapResultSetToInstance(resultSet, excludes));
    }

    public T findById(Object id) throws SQLException {
        return findById(id, List.of());
    }

    public T findById(Object id, List<Class<?>> excludes) throws SQLException {
        List<T> lists = findByField(reflectEntity.getIdAttribute().getColumnName(), id, excludes);
        return lists.isEmpty() ? null : lists.get(0);
    }

    public T saveOrUpdate(T toSave) throws SQLException {
        final Object idValue = reflectEntity.invokeGetters(toSave, reflectEntity.getIdAttribute());
        if(idValue == null)
            return null;

        final boolean isCreate = findById(idValue) == null;

        String query;
        if(isCreate){
            query = QueryTemplate.insert(
                    this.joinAttributesNames(","),
                    "? " + ",?".repeat(reflectEntity.getAttributes().size() - 1)
            );
        }else{
            query = QueryTemplate.updateByCondition(
                joinAttributesNamesWithoutId(" = ? , ") + " = ?",
                reflectEntity.getIdAttribute().getColumnName() + " = ?"
            );
        }

        List<Object> values;
        if(isCreate){
            values = reflectEntity.getAttributes()
                    .stream()
                    .map(attribute -> reflectEntity.invokeGetters(toSave, attribute))
                    .collect(Collectors.toList());
        }else{
            values = reflectEntity.getAttributes()
                    .stream()
                    .filter(attribute -> !attribute.isId())
                    .map(attribute -> reflectEntity.invokeGetters(toSave, attribute))
                    .collect(Collectors.toList());
            values.add(reflectEntity.invokeGetters(toSave, reflectEntity.getIdAttribute()));
        }

        ResultSet resultSet = statementWrapper.update(
            queryGenerator.configure(query),
            values
        );

        if(!resultSet.next())
            return null;
        return resultSetMapper.mapResultSetToInstance(resultSet);
    }

    public List<T> saveOrUpdateAll(List<T> toSaves) throws SQLException {
        List<T> result = new ArrayList<>();
        for(T toSave: toSaves){
            result.add(saveOrUpdate(toSave));
        }
        return result;
    }

    public String joinAttributesNames(String limiter) {
        return reflectEntity.getAttributes()
                .stream()
                .map(ReflectAttribute::getColumnName)
                .collect(Collectors.joining(limiter));
    }

    public String joinAttributesNamesWithoutId(String limiter){
        return reflectEntity.getAttributes()
                .stream()
                .filter(attribute-> !attribute.isId())
                .map(ReflectAttribute::getColumnName)
                .collect(Collectors.joining(limiter));
    }
}