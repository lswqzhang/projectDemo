package com.lswq.app.util.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by zhangsw on 2016/12/30.
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    /**
     * Spring应用上下文环境
     */
    private static ApplicationContext applicationContext;

   
    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        SpringContextUtil.applicationContext = context;
    }


    /**
     * 获取String的ApplicationContext对象
     *
     * @return ApplicationContext
     * @author lifangyu
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取spring容器中的对象
     * <p>获得注入的bean 需要在注解的时候指定名称[@Service("beanname")，对应获取 (接口Service) SpringContextUtil.getBean("beanname")],否则可能会出异常NoSuchBeanDefinitionException<p>
     *
     * @param beanName
     * @return T
     * @author lifangyu
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanName) {
        return (T) applicationContext.getBean(beanName);
    }

    /**
     * 如果applicationContext包含一个与所给名称匹配的bean定义，则返回true
     *
     * @param beanName
     * @return boolean
     * @author lifangyu
     */
    public static boolean containsBean(String beanName) {
        return applicationContext.containsBean(beanName);
    }

    /**
     * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。
     * 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
     *
     * @param beanName
     * @return
     * @throws NoSuchBeanDefinitionException boolean
     * @author lifangyu
     */
    public static boolean isSingleton(String beanName) throws NoSuchBeanDefinitionException {
        return applicationContext.isSingleton(beanName);
    }

    /**
     * Class 注册对象的类型
     *
     * @param beanName
     * @return
     * @throws NoSuchBeanDefinitionException Class
     * @author lifangyu
     */
    @SuppressWarnings("rawtypes")
    public static Class getType(String beanName) throws NoSuchBeanDefinitionException {
        return applicationContext.getType(beanName);
    }

    /**
     * 如果给定的bean名字在bean定义中有别名，则返回这些别名
     *
     * @param beanName
     * @return
     * @throws NoSuchBeanDefinitionException String[]
     * @author lifangyu
     */
    public static String[] getAliases(String beanName) throws NoSuchBeanDefinitionException {
        return applicationContext.getAliases(beanName);
    }

}
