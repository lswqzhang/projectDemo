package com.lswq.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyUtil implements InvocationHandler {

    /**
     * 被真实代理的对象
     */
    private Object target;


    public ProxyUtil(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("do something before");
        // 调用被代理对象的方法并得到返回值
        Object result = method.invoke(target, args);
        System.out.println("do something after");
        return result;
    }

    /**
     * 通过{@link Proxy}生成动态代理对象
     *
     * @param proxyInterface 代理的接口类
     * @param target         真实的代理对象
     * @param <T>            类类型
     * @return
     */
    public static <T> T create(Class<T> proxyInterface, Object target) {
        return (T) Proxy.newProxyInstance(
                ProxyUtil.class.getClassLoader(),
                new Class[]{proxyInterface},
                new ProxyUtil(target)
        );
    }
}
