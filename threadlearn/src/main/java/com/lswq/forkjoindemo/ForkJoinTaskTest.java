package com.lswq.forkjoindemo;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class ForkJoinTaskTest extends RecursiveAction {
    /**
     * 数据分页对象
     */
    private List<List<Integer>> partition;

    private List<Integer> result;


    public ForkJoinTaskTest(List<List<Integer>> partition, List<Integer> result) {
        this.partition = partition;
        this.result = result;
    }


    @Override
    protected void compute() {
        List<List<List<Integer>>> partition = Lists.partition(this.partition, 1);
        if (partition.size() == 1) {
            result.addAll(partition.get(0).get(0));
            return;
        }
        List<ForkJoinTaskTest> tasks = new ArrayList<>();
        for (List<List<Integer>> singleTask : partition) {
            ForkJoinTaskTest forkJoinTaskTest = new ForkJoinTaskTest(singleTask, result);
            tasks.add(forkJoinTaskTest);
        }
        invokeAll(tasks);
        return;
    }
}
