package com.hei.wallet.wallety.fjpa;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Attribute {
    private String columnName;
    private String fieldName;
    private boolean required;
    private Class<?> fieldType;
    private boolean isRelation;
}