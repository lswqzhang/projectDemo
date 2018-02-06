package com.lswq.classloader;

public class Model {

    /**
     * 统一接口实现类 - 取自配置文件
     *
     * 容器加载模块后会实例化此入口类
     */
    private String mainClass = "";


    /**
     * 实例化后的模块入口类 - 由容器设置
     */
    private Object instance;
    
    /**
     * 加载本模块的classLoader - 由容器设置
     */
    private ClassLoader classLoader;

    public String getMainClass() {
        return mainClass;
    }

    public void setMainClass(String mainClass) {
        this.mainClass = mainClass;
    }

    public Object getInstance() {
        return instance;
    }

    public void setInstance(Object instance) {
        this.instance = instance;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }
}
