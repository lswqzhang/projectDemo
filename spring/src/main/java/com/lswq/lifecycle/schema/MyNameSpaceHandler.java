package com.lswq.lifecycle.schema;


import com.lswq.lifecycle.spring.bean.Pig;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * 命名空间解析
 * 
 * @author zhangsw 
 */
public class MyNameSpaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("pig", new PigBeanDefinitionParser(Pig.class));
    }
}
