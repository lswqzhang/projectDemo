package com.lswq.myclass;

public class TestMyClassLoader
{
    public static void main(String[] args) throws Exception
    {
        MyClassLoader mcl = new MyClassLoader();
        Class<?> c1 = Class.forName("com.xrq.classloader.Person", true, mcl);
        Object obj = c1.newInstance();
        System.out.println(obj);
        System.out.println(obj.getClass().getClassLoader());
    }
}
