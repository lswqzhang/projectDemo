package com.lswq.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.*;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by zhangsw on 2017/4/4.
 * <p>
 * *Aware   完成对IoC容器的感知，获取容器的信息
 * <p>
 * {@link BeanNameAware#setBeanName(String)}
 * 感知在Bean中得到它在IOC容器中的Bean的实例的名字
 * <p>
 * {@link BeanFactoryAware#setBeanFactory(BeanFactory)}
 * 感知在Bean中得到Bean所在的IOC容器，从而直接在Bean中使用IOC容器的服务。
 * <p>
 * {@link ApplicationContextAware#setApplicationContext(ApplicationContext)}
 * 感知在Bean中得到Bean所在的应用上下文，从而直接在Bean中使用上下文的服务。
 * <p>
 * {@link MessageSourceAware#setMessageSource(MessageSource)}，
 * 感知在Bean中可以得到消息源。
 * {@link ApplicationEventPublisherAware#setApplicationEventPublisher(ApplicationEventPublisher)}
 * 在bean中可以得到应用上下文的事件发布器，从而可以在Bean中发布应用上下文的事件。
 * {@link ResourceLoaderAware#setResourceLoader(ResourceLoader)}
 * 在Bean中可以得到ResourceLoader，从而在bean中使用ResourceLoader加载外部对应的Resource资源。
 * <p>
 * 在设置Bean的属性之后，调用初始化回调方法之前，Spring会调用aware接口中的setter方法。
 */
public class Person implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean {


    /**
     * 构造方法调用之后的操作
     */
    @PostConstruct
    public void init() {
        System.out.println("【构造器】类创建之后Person PostConstruct");
    }

    @PreDestroy
    public void perDestroy() {
        System.out.println("Person PerDestroy");
    }


    private String name;
    private String address;
    private String phone;

    private BeanFactory beanFactory;
    private String beanName;

    public Person() {
        System.out.println("【构造器】调用Person的构造器实例化");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("【注入属性】注入属性name");
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        System.out.println("【注入属性】注入属性address");
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        System.out.println("【注入属性】注入属性phone");
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Person [address=" + address + ", name=" + name + ", phone=" + phone + "]";
    }

    // 这是BeanFactoryAware接口方法
    @Override
    public void setBeanFactory(BeanFactory factory) throws BeansException {
        System.out.println("【BeanFactoryAware接口】调用BeanFactoryAware.setBeanFactory()");
        this.beanFactory = factory;
    }

    // 这是BeanNameAware接口方法
    @Override
    public void setBeanName(String name) {
        System.out.println("【BeanNameAware接口】调用BeanNameAware.setBeanName() ==> " + name);
        this.beanName = name;
    }


    /**
     * 这是InitializingBean接口方法
     * <p>
     * afterPropertiesSet方法将在
     * {@link MyBeanPostProcessor#postProcessBeforeInitialization(Object, String)}
     * 和
     * {@link MyBeanPostProcessor#postProcessAfterInitialization(Object, String)}
     * 之间被调用.
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("【InitializingBean接口】调用InitializingBean.afterPropertiesSet()");
    }

    // 这是DiposibleBean接口方法
    @Override
    public void destroy() throws Exception {
        System.out.println("【DiposibleBean接口】调用DiposibleBean.destory()");
    }

    // 通过<bean>的init-method属性指定的初始化方法
    public void myInit() {
        System.out.println("【init-method】调用<bean>的init-method属性指定的初始化方法");
    }

    // 通过<bean>的destroy-method属性指定的初始化方法
    public void myDestroy() {
        System.out.println("【destroy-method】调用<bean>的destroy-method属性指定的初始化方法");
    }
}
