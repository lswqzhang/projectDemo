package com.lswq.app.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 
 * 兼容多种数据类型
 * 
 * Created by zhangsw on 2016/12/29.
 */
public class ObjectMappingCustomer extends ObjectMapper {
    /**
     * @Fields serialVersionUID : 设置新的json类型
     */

    private static final long serialVersionUID = -1677605738482132273L;

    public ObjectMappingCustomer() {
        super();
        //  允许单引号
        this.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        //  字段和值都加引号
        this.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        //  可以包含特殊字符
        this.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        
        this.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        //  数字也加引号
        this.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);
        this.configure(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS, true);

        //  空值处理为空串
        SerializerProvider sp = this.getSerializerProvider();
        sp.setNullValueSerializer(new JsonSerializer<Object>() {

            @Override
            public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeString("");
            }

        });
        
    }
}
