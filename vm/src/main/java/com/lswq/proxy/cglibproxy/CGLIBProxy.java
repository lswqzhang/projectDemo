package com.lswq.proxy.cglibproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGLIBProxy implements MethodInterceptor {

    /**
     * 创建代理对象
     *
     * @param proxyInterface 真实代理的对象
     * @param <T>            代理对象的类的类
     * @return
     */
    public static <T> T getProxy(Class<T> proxyInterface) {
        Enhancer enhancer = new Enhancer();
        //设置父类
        enhancer.setSuperclass(proxyInterface);
        enhancer.setCallback(new CGLIBProxy());
        //通过字节码技术动态创建子类实例  
        return (T) enhancer.create();
    }


    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("cglib实现的前置代理");

        //通过代理类调用父类中的方法  
        Object result = proxy.invokeSuper(obj, args);

        System.out.println("cglib实现的后置代理");
        return result;
    }
}
