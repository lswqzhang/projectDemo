package com.lswq.stringv;

/**
 * Created by zhangsw on 2017/1/10.
 */
public class StringIntern {

    public static void main(String[] args) {
        
        //  str1返回true是引用他们指向的都是str1对象（堆中）（池中不存在，返回原引用）
        String str1 = new StringBuilder("chaofan").append("wei").toString();
        System.out.println(str1.intern() == str1);

        
        //  str2返回false是因为池中已经存在"java"了（关键词），所以返回的池的对象，因此不相等
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }
}
