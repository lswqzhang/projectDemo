package com.lswq.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * jdk lambda predicate 的使用
 *
 * @author zhangshaowei
 */
public class LambdaPredicateTest {

    public static void main(String[] args) {

        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        // 单个predicate
        System.err.println("print language start with \"J\"");
        filter1(languages, (str) -> str.startsWith("J"));
        System.err.println("print all language!");
        filter1(languages, (str) -> true);
        System.err.println("print no language!");
        filter1(languages, (str) -> false);


        multiCondition(languages);

    }


    public static void filter1(List<String> names, Predicate<String> condition) {
        for (String name : names) {
            if (condition.test(name)) {
                System.out.println(name + "\t");
            }
        }
    }

    /**
     * 使用jdk的stream进行操作
     *
     * @param names
     * @param condition
     */
    public static void filter2(List<String> names, Predicate<String> condition) {
        names.stream().filter(str -> condition.test(str)).forEach(str -> System.out.println(str));
    }


    /**
     * 调用无参的构造方法
     *
     * @param names
     * @param condition
     */
    public static void filter3(List<String> names, Predicate<String> condition) {
        names.stream().filter(condition::test).forEach(System.out::println);
    }

    public static void multiCondition(List<String> names) {
        //多个Predicate
        Predicate<String> startWithJ = (str) -> str.startsWith("J");
        Predicate<String> fourLetterLong = (str) -> str.length() == 4;
        names.stream().filter(startWithJ.and(fourLetterLong)).forEach(System.out::println);
    }

}
