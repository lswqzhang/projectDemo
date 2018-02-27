package com.lswq.classloader;

import java.io.File;

import static java.lang.Thread.sleep;

public class MyClassLoaderTest {

    public static void main(String[] args) throws InterruptedException {

        String path = "/Users/zhangshaowei/Desktop/jar";

        File f = new File("/Users/zhangshaowei/Desktop/jar");

        File[] files = f.listFiles();

        System.err.println(Thread.currentThread().getContextClassLoader());

        for (File jarFile : files) {


            if (!(jarFile.isFile() && jarFile.getName().endsWith("jar"))) {
                continue;
            }

            System.err.println(jarFile.getName());


            new Thread(() -> {
                try {

                    MyClassLoader classLoader = new MyClassLoader();
                    // 循环依赖模块，为本模块设置父亲classloader
                    classLoader.getParentClassLoaders().put(jarFile.getAbsolutePath(), classLoader);
                    classLoader.loadJar(path, jarFile.getName());
                    System.err.println(classLoader.getUrlStr());
                    classLoader.addPath(jarFile.getAbsolutePath(), "");
                    Class<?> cls = classLoader.findClass("com.lswq.classloader.MyClass");
                    System.err.println(cls);
                    Proxy proxy = (Proxy) cls.newInstance();
                    proxy.sayHello();

                    /*
                    Model model = new Model();
                    model.setMainClass("com.lswq.classloader.MyClass");
                    model.setClassLoader(Thread.currentThread().getContextClassLoader());
                    loadJar(path, model);

                    Proxy instance = (Proxy) model.getInstance();

                    instance.sayHello();
                    */

                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }).start();


        }

        sleep(1000);


    }


    /**
     * 加载模块Jar包，设置模块依赖关系，初步初始化
     *
     * @param dir   模块文件所在位置
     * @param model 模块
     * @return true:成功，false:失败
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private static boolean loadJar(String dir, Model model) throws InstantiationException, IllegalAccessException {

        // 返回值
        boolean isLoadSuccess = true;


        // 为当前模块创建一个新的ClassLoader
        MyClassLoader classLoader = new MyClassLoader(model.getClassLoader());


        // 将当前模块的jar包，引入到自己的 classLoader url 中
        classLoader.addPath(dir, model.getMainClass());

        // 获得模块接入容器的入口类
        Class<?> cls = classLoader.findClass(model.getMainClass());

        // jar 加载结束

        if (cls == null) {
            throw new RuntimeException();
        } else {

            System.err.println("实例化接入容器入口类: {}" + model.getMainClass());


            // 实例化入口类
            Proxy proxy = (Proxy) cls.newInstance();

            // 注入到模块中
            model.setInstance(proxy);

            // 设置模块的classloader
            model.setClassLoader(classLoader);
        }
        return isLoadSuccess;
    }
}
