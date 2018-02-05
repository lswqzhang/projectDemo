package com.lswq.classloader;

import java.io.File;

import static java.lang.Thread.sleep;

public class MyClassLoaderTest {

    public static void main(String[] args) throws InterruptedException {

        String path = "/Users/zhangsw/Desktop";

        File f = new File("/Users/zhangsw/Desktop");

        File[] files = f.listFiles();

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
                    classLoader.loadJar("/Users/zhangsw/Desktop", jarFile.getName());
                    System.err.println(classLoader.getUrlStr());
                    classLoader.addPath("/Users/zhangsw/Desktop/MyClass.jar", "");
                    Class<?> cls = classLoader.findClass("com.lswq.classloader.MyClass");
                    System.err.println(cls);
                    Proxy proxy = (Proxy) cls.newInstance();
                    proxy.sayHello();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }).start();


        }

        sleep(1000);


    }
}
