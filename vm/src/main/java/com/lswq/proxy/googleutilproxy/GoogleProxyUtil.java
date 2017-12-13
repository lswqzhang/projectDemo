package com.lswq.proxy.googleutilproxy;

import com.google.common.reflect.AbstractInvocationHandler;
import com.google.common.reflect.Reflection;

import java.lang.reflect.Method;

public class GoogleProxyUtil extends AbstractInvocationHandler {

    /**
     * 真实的代理对象
     */
    private Object target;

    public GoogleProxyUtil(Object target) {
        this.target = target;
    }

    @Override
    protected Object handleInvocation(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("do something before");
        // 调用被代理对象的方法并得到返回值
        Object invoke = method.invoke(target, args);
        System.out.println("do something after");
        return invoke;
    }

    /**
     * 调用的是jdk的{@link java.lang.reflect.Proxy}的实现
     *
     * @param proxyInterface 类的接口类
     * @param target         真实的代理对象
     * @param <T>            类
     * @return
     */
    public static <T> T create(Class<T> proxyInterface, Object target) {
        return Reflection.newProxy(proxyInterface, new GoogleProxyUtil(target));
    }
}
