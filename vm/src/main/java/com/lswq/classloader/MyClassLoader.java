package com.lswq.classloader;

import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 创建一个自定义的
 */
public class MyClassLoader extends URLClassLoader {

    /**
     * 父级ClassLoader
     */
    private Map<String, MyClassLoader> parentClassLoaders = new ConcurrentHashMap<>();

    public Map<String, MyClassLoader> getParentClassLoaders() {
        return parentClassLoaders;
    }

    /**
     * 构造
     */
    public MyClassLoader() {
        super(new URL[]{}, Thread.currentThread().getContextClassLoader());
    }

    /**
     * 构造
     */
    public MyClassLoader(ClassLoader cl) {
        super(new URL[]{}, cl);
    }

    /**
     * 引入jar包
     *
     * @param path   jar 包路径
     * @param source 模块名称
     */
    public void addPath(String path, String source) {
        try {
            super.addURL(new File(path).toURI().toURL());
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 查找Class
     *
     * @param className 要查找的class名称
     * @return Class
     */
    @Override
    public Class<?> findClass(String className) {

        // 委托获取Class 
        Class<?> cls = super.findLoadedClass(className);

        // 再委托获取Class
        if (cls == null) {
            try {
                cls = super.findClass(className);
            } catch (Throwable e) {
                // 过滤 jdk 对于使用内省方式获得 Java Bean 时，第一次产生的异常
                if (className.endsWith("BeanInfo") && e instanceof ClassNotFoundException) {
                    for (StackTraceElement ste : e.getStackTrace()) {
                        if (ste.toString().indexOf("java.beans.Introspector.getBeanInfo") >= 0) {
                            return null;
                        }
                    }
                }
            }
        }

        // 如果上面二步没有定位到Class，考虑实现自定义定位Class

        // 去依赖模块中获取Class
        if (cls == null) {
            for (Map.Entry<String, MyClassLoader> entry : parentClassLoaders.entrySet()) {
                cls = entry.getValue().findClass(className);
                if (cls != null) {
                    break;
                }
            }
        }

        return cls;
    }

    /**
     * 根据指定的路径，加载指定的class
     *
     * @param dir       :
     *                  class文件所在路径
     * @param className :
     *                  class名称，不包含后缀
     * @return Class
     */
    public Class<?> loadClass(String dir, String className) {

        // 最终返回的class
        Class<?> cls = null;

        // class文件输入流
        InputStream is = null;

        try {

            // 转换class中的“.”为路径
            String classFileFullName = className.replace('.', File.separatorChar) + ".class";

            // class文件
            File file = new File(dir + File.separatorChar + classFileFullName);

            // class文件字节数组
            byte[] classFileBytes = null;

            // 读取class文件到内存
            if (file.exists()) {
                is = new FileInputStream(file);
                classFileBytes = new byte[is.available()];
                is.read(classFileBytes);
                cls = this.defineClass(className, classFileBytes, 0, classFileBytes.length);// 加载类
            } else {
                cls = null;
            }

        } catch (Throwable e) {
            e.printStackTrace();
            cls = null;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }

        return cls;
    }

    /**
     * 根据指定的路径，加载指定的jar包中的所有Class
     * <p>
     * BUG:
     * 加载class时，有时候会报找不到依赖的class，需要对依赖进行处理
     * 可使用addPath方式，简单
     *
     * @param dir     : class文件所在路径
     * @param jarName : class名称，不包含后缀
     */
    public void loadJar(String dir, String jarName) {

        // class文件输入流
        InputStream is = null;

        // class文件字节数组
        ByteArrayOutputStream memArary = null;
        // jar文件
        File file = new File(dir + File.separatorChar + jarName);

        JarFile jarFile = null;

        // 如果jar文件存在
        if (file.exists()) {
            try {
                // 创建jar文件
                jarFile = new JarFile(file);

                // 列出jar包中的文件列表
                Enumeration<JarEntry> jes = jarFile.entries();
                JarEntry entry;

                // 遍历jar文件中的所有class，加载到jvm
                while (jes.hasMoreElements()) {
                    entry = jes.nextElement();
                    // 如果是class文件
                    if (entry.getName().endsWith(".class")) {
                        // 转换class名称
                        String jarClassName = entry.getName().replaceAll("/", ".");
                        try {
                            // 将class文件读到字节缓冲区中
                            is = jarFile.getInputStream(entry);
                            memArary = new ByteArrayOutputStream();
                            int b = 0;
                            while ((b = is.read()) >= 0) {
                                memArary.write(b);
                            }
                            byte[] bytes = memArary.toByteArray();
                            // 根据字节声明class
                            this.defineClass(null, bytes, 0, bytes.length);
                            System.err.println("Load class complete -> " + jarClassName);
                        } catch (Throwable e) {
                            e.printStackTrace();
                        } finally {
                            if (is != null) is.close();
                            if (memArary != null) memArary.close();
                        }
                    }
                }
            } catch (Throwable e) {
                e.printStackTrace();
            } finally {
                if (jarFile != null) {
                    try {
                        jarFile.close();
                    } catch (IOException e) {
                    }
                }
            }
        }
    }

    /**
     * 获得 classLoader url 列表
     */
    public String getUrlStr() {

        StringBuffer sb = new StringBuffer();
        for (URL u : getURLs()) {
            sb.append(" -> ");
            sb.append(u.getFile());
            sb.append("\n");
        }
        return sb.toString();
    }

}
