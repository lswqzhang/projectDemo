package com.lswq.forkjoindemo;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ForkJoinPool;

/**
 * Fork Join 测试
 *
 * @author zhangshaowei
 */
public class ForkJoinTest {


    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        List<List<Integer>> tasks = new ArrayList<>();
        List<Integer> one = Lists.newArrayList(1, 2, 3, 4);
        List<Integer> two = Lists.newArrayList(5, 6, 7, 8);
        List<Integer> three = Lists.newArrayList(9, 10, 11, 12);
        List<Integer> four = Lists.newArrayList(13, 14, 15, 16);
        tasks.add(one);
        tasks.add(two);
        tasks.add(three);
        tasks.add(four);
        List<Integer> result = new CopyOnWriteArrayList<>();
        pool.invoke(new ForkJoinTaskTest(tasks, result));
        pool.shutdown();
        System.err.println("======" + result);
    }
}
