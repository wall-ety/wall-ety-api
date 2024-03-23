package com.hei.wallet.wallety.fjpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ReflectAttribute {
    private String columnName;
    private String fieldName;
    private boolean required;
    private boolean isRelation;
    private boolean isId;
    private Method setter;
    private Method getter;
    private Class<?> clazz;
}
