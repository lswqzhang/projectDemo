package com.lswq.lifecycle.schema;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * bean 解析
 *
 * @author zhangsw
 */
public class PigBeanDefinitionParser implements BeanDefinitionParser {


    private final Class<?> beanClass;

    public PigBeanDefinitionParser(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        return parse(element, parserContext, beanClass);
    }

    private BeanDefinition parse(Element element, ParserContext parserContext, Class<?> beanClass) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(beanClass);
        beanDefinition.setLazyInit(false);
        String name = element.getAttribute("name");
        String age = element.getAttribute("age");
        String id = element.getAttribute("id");
        parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
        if (StringUtils.hasText(name)) {
            beanDefinition.getPropertyValues().addPropertyValue("name", name);
        }
        if (StringUtils.hasText(age)) {
            beanDefinition.getPropertyValues().addPropertyValue("age", Integer.valueOf(age));
        }
        return beanDefinition;
    }
}
