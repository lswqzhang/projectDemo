package com.lswq;

import com.lswq.spring.Person;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhangsw on 2017/4/4.
 */
public class BeanLifeCycle {

    /**
     * 所有的Bean生成都有个顺序: 定义 --> 创建 --> 初始化.
     * <p>
     * <p>
     * BeanDefinitionRegistryPostProcessor的postProcessBeanDefinitionRegistry方法在Bean被定义但还没被创建的时候执行.
     * <p>
     * <p>
     * BeanFactoryPostProcessor的postProcessBeanFactory方法在Bean被创建但还没被初始化的时候执行
     * <p>
     * BeanPostProcessor
     * <p>
     * postProcessBeforeInitialization: 在Bean初始化之 前 做一些操作
     * <p>
     * postProcessAfterInitialization: 在Bean初始化之 后 做一些操作
     *
     * @param args
     * @throws BeansException
     */
    public static void main(String[] args) throws BeansException {


        System.out.println("现在开始初始化容器");

        ApplicationContext factory = new ClassPathXmlApplicationContext("beans.xml");
        System.out.println("容器初始化成功");
        //得到Preson，并使用
        Person person = factory.getBean("person", Person.class);
        System.out.println(person);

        System.out.println("现在开始关闭容器！");
        ((ClassPathXmlApplicationContext) factory).registerShutdownHook();
    }
}
