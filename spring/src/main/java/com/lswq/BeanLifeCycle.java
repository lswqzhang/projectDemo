package com.lswq;

import com.lswq.spring.MyService;
import com.lswq.spring.Person;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

/**
 * Created by zhangsw on 2017/4/4.
 */
public class BeanLifeCycle {

    /**
     * 所有的Bean生成都有个顺序: 配置 --> 注册 --> 创建 --> 初始化.
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

        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        //  获取资源信息
        Resource res = ctx.getResource("beans.xml");
        System.err.println("file name is "  + res.getFilename());
        System.err.println("file description is " + res.getDescription());
        System.out.println("容器初始化成功");
        //得到Preson，并使用
        Person person = ctx.getBean("person", Person.class);
        System.out.println(person);

        MyService myBeanName = ctx.getBean("myBeanName", MyService.class);
        myBeanName.say();
        
        
        System.out.println("现在开始关闭容器！");
        ((ClassPathXmlApplicationContext) ctx).registerShutdownHook();
    }
}
