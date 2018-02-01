package com.lswq.proxy.proxy.dynamicproxy.jdk;

import com.lswq.proxy.proxy.staticproxy.PerformanceMonitor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//①实现InvocationHandler
public class PerformanceHandler implements InvocationHandler {

    //②target为目标的业务类
    private Object target;

    public PerformanceHandler(Object target) { //②target为目标的业务类  
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        PerformanceMonitor.begin(target.getClass().getName() + "." + method.getName());
        Object obj = method.invoke(target, args);// ③-2通过反射方法调用业务类的目标方法
        PerformanceMonitor.end();
        return obj;
    }


    public static <T> T newInstance(T t) {
        //③根据编织了目标业务类逻辑和性能监视横切逻辑的InvocationHandler实例创建代理实例  
        return (T) Proxy.newProxyInstance(
                t.getClass().getClassLoader(),
                t.getClass().getInterfaces(),
                new PerformanceHandler(t));
    }
}
