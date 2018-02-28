package com.lswq.theadlocal;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自定义ThreadLocal
 * <p>
 * jdk1.3之前的实现方式
 *
 * @param <T>
 * @author zhangsw
 */
public class ThreadLocal1_3<T> {


    private Map<Thread, T> map = new ConcurrentHashMap<>();


    /**
     * 初始化本地变量的值
     *
     * @return
     */
    protected T initialValue() {
        return null;
    }

    /**
     * 设置初始化值
     *
     * @return
     */
    private T setInitialValue() {
        T value = initialValue();
        Thread t = Thread.currentThread();
        T v = map.get(t);
        if (null == v) {
            set(value);
        } else {
            value = v;
        }
        return value;
    }

    /**
     * 手动设置值
     *
     * @param t
     */
    void set(T t) {
        map.put(Thread.currentThread(), t);
    }


    /**
     * 获取ThreadLocal1_3本地的私有变量
     *
     * @return
     */
    T get() {
        T t = map.get(Thread.currentThread());
        if (null == t) {
            t = setInitialValue();
        }
        return t;
    }

}
