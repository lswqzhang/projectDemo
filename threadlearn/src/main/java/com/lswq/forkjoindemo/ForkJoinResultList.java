package com.lswq.forkjoindemo;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public class ForkJoinResultList extends RecursiveTask<List<Integer>> {
    
    /**
     * 数据分页对象
     */
    private List<List<Integer>> partition;

    public ForkJoinResultList(List<List<Integer>> partition) {
        this.partition = partition;
    }

    @Override
    protected List<Integer> compute() {
        System.err.println("当前数组的长度为：" + partition.size());
        if (partition.size() <= 2) {
            List<Integer> result = Lists.newArrayList();
            for (List<Integer> single : partition) {
                result.addAll(single);
            }
            return result;
        } else {
            List<List<List<Integer>>> partition = Lists.partition(this.partition, 2);
            System.err.println("分隔后的数组为" + partition.size());
            List<Integer> result;
            ForkJoinResultList taskLeft = new ForkJoinResultList(partition.get(0));
            ForkJoinResultList taskRight = new ForkJoinResultList(partition.get(1));
            taskLeft.fork();
            taskRight.fork();
            taskLeft.join().addAll(taskRight.join());
            result = taskLeft.join();
            return result;
        }
    }
}
