package com.lswq.proxy.proxy.dynamicproxy.cglib;

import com.lswq.proxy.proxy.staticproxy.PerformanceMonitor;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLib采用动态创建子类的方式生成代理对象，所以不能对目标类中的final方法进行代理。
 */
public class CglibProxy implements MethodInterceptor {


    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class clazz) {
        //① 设置需要创建子类的类
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        //②通过字节码技术动态创建子类实例
        return enhancer.create();

    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        //③-1
        PerformanceMonitor.begin(obj.getClass().getName() + "." + method.getName());
        //③-2
        Object result = proxy.invokeSuper(obj, args);
        //③-1通过代理类调用父类中的方法
        PerformanceMonitor.end();
        return result;
    }
}
