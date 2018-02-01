package com.lswq.jdk8stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamJdk8 {

    private List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10);

    public void streamTest() {
        numbers.stream().filter(x -> (x % 2 == 0)).map(x -> x * x).forEach(System.out::println);
        long count1 = numbers.stream().distinct().filter(x -> (x % 2 == 0)).map(x -> x * x).max((x, y) -> (x.compareTo(y))).get();
        System.err.println(count1);
        List<Integer> collect = numbers.stream().distinct().collect(Collectors.toList());
        System.err.println(collect);
        long count2 = numbers.stream().distinct().filter(x -> (x % 2 == 0)).map(x -> x * x).max(Comparator.comparing(x -> x)).get();
        System.err.println(count2);
        Integer reduce = numbers.stream().distinct().reduce(0, (ar, sum) -> sum + ar);
        System.err.println(reduce);
        Integer reduce2 = numbers.stream().distinct().reduce(0, Integer::sum);
        System.err.println(reduce2);
        Integer reduce3 = numbers.stream().distinct().reduce(0, StreamJdk8::count);
        System.err.println(reduce3);
        List<Integer> collect1 = numbers.stream().distinct().flatMap(x -> Stream.of(x * x * 2)).collect(Collectors.toList());
        System.err.println(collect1);
    }
    
    
    public void parallelStream() {
        numbers.parallelStream().distinct().map(x -> ((x + 2) * 2)).forEach(x ->System.out.println("parallel:" + x));
    }


    public static int count(Integer x, Integer y) {
        return y + x;
    }


    public static void main(String[] args) {
        StreamJdk8 streamJdk8 = new StreamJdk8();
        streamJdk8.streamTest();
        streamJdk8.parallelStream();
    }

}
