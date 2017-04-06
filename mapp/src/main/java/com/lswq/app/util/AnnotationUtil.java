package com.lswq.app.util;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

public class AnnotationUtil {

    /**
     * 支持6种基本类型
     */
    public static Object transferValueType(Field field, String value) {
        Object result;
        String typeName = field.getType().getSimpleName();
        if (StringUtils.equals(typeName, "String")) {
            result = value;
        } else if (StringUtils.equals(typeName, "int") || StringUtils.equals(typeName, "Integer")) {
            result = Integer.valueOf(value);
        } else if (StringUtils.equals(typeName, "long") || StringUtils.equals(typeName, "Long")) {
            result = Long.valueOf(value);
        } else if (StringUtils.equals(typeName, "float") || StringUtils.equals(typeName, "Float")) {
            result = Float.valueOf(value);
        } else if (StringUtils.equals(typeName, "double") || StringUtils.equals(typeName, "Double")) {
            result = Double.valueOf(value);
        } else if (StringUtils.equals(typeName, "boolean") || StringUtils.equals(typeName, "Boolean")) {
            result = Boolean.valueOf(value);
        } else {
            return "";
        }
        return result;
    }
}
