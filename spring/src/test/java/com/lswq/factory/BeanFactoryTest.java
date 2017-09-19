package com.lswq.factory;

import org.junit.Test;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class BeanFactoryTest {

    private static final String FILE_PATH = "beanFactoryTest.xml";


    /**
     * {@link DefaultListableBeanFactory} 实现了 {@link BeanDefinitionRegistry}  接口
     * {@link XmlBeanDefinitionReader#XmlBeanDefinitionReader(BeanDefinitionRegistry)}
     */
    @Test
    public void testSimpleLoadRegistry() {
        String beanName = "testBean";
        DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(bf);
        Resource resource = new ClassPathResource(FILE_PATH);
        boolean b = bf.containsSingleton(beanName);
        System.err.println(beanName + " singleton :" + b);
        reader.loadBeanDefinitions(resource);
        TestBean testBean = bf.getBean(beanName, TestBean.class);
        System.err.println("test bean test str is :" + testBean.getTestStr());
    }
    
    
    @Test
    public void springTest() throws InterruptedException {
        DefaultNamespaceHandlerResolver resolver = new DefaultNamespaceHandlerResolver();
        System.err.println("resolver = " + resolver.handlerMappings);
    }
}
