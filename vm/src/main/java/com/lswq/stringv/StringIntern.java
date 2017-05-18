package com.lswq.stringv;

/**
 * Created by zhangsw on 2017/1/10.
 */
public class StringIntern {

    public static void main(String[] args) {

        int a = 1;
        int b = 1;

        System.err.println(a == b);

        Integer aI = 1;
        Integer bI = 1;

        System.err.println(aI == bI);


        Integer aI128 = 128;
        Integer bI128 = 128;
        System.err.println(aI128 == bI128);

        //  字符串常量，编译期就被确定了
        String s0="kvill";
        //  字符串常量，编译期就被确定了
        String s1="kvill";
        //  字符串常量，编译期就被确定了
        String s2="kv" + "ill";
        //  true
        System.out.println( s0==s1 );
        //  true
        System.out.println( s0==s2 );
        //  程序编译期，
        //      编译程序先去字符串常量池检查，是否存在“myString”,如果不存在，则在常量池中开辟一个内存空间存放“myString”；
        //                                                 如果存在的话，则不用重新开辟空间，保证常量池中只有一个“myString”常量，节省内存空间。
        //  然后在内存堆中开辟一块空间存放new出来的String实例，在栈中开辟一块空间，命名为“s1”，存放的值为堆中String实例的内存地址，这个过程就是将引用s1指向new出来的String实例

        String s1New = new String("myString");

        //  在程序编译期，
        //      编译程序先去字符串常量池检查，是否存在“myString”，如果不存在，则在常量池中开辟一个内存空间存放“myString”；
        //                                                  如果存在的话，则不用重新开辟空间。
        //  然后在栈中开辟一块空间，命名为“s1”，存放的值为常量池中“myString”的内存地址
        String s1Str = "";
        
        //  str1返回true是引用他们指向的都是str1对象（堆中）（池中不存在，返回原引用）
        String str1 = new StringBuilder("chaofan").append("wei").toString();
        System.out.println(str1.intern() == str1);

        
        //  str2返回false是因为池中已经存在"java"了（关键词），所以返回的池的对象，因此不相等
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }
}
