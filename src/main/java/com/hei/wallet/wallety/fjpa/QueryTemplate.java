package com.hei.wallet.wallety.fjpa;

public class QueryTemplate {
    public static String getSelectAllQuery(String tableName){
        return String.format("SELECT * FROM %s", tableName);
    }

    public static String getSelectWithConditionQuery(String tableName, String condition){
        return String.format("SELECT * FROM %s WHERE %s", tableName, condition);
    }

    public static String getUpdateQuery(String tableName, String columnValues, String condition){
        return String.format("UPDATE %s SET %s WHERE %s", tableName, columnValues, condition);
    }

    public static String getInsertQuery(String tableName, String columns,String values){
        return String.format("INSERT INTO %s(%s) VALUES ( %s )", tableName, columns, values);
    }
}