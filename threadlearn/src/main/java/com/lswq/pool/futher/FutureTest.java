package com.lswq.pool.futher;


import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * Created by zhangsw on 2017/3/6.
 */
public class FutureTest {


    /**
     * executor执行runnable接口
     */
    @Test
    public void runnableTest() {
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(() -> {
            System.err.println("测试");
            System.err.println("应用");
        });
        executor.shutdown();
    }

    /**
     * 由于通过execute提交task，可以直接通过task获取执行的结果
     * <p>
     * 因此不需要通过submit进行提交，获取返回值，此时的返回值不可以获取任务结果
     * <p>
     * 结论：当提交的是task任务时，可以任务execute进行执行任务
     * <p>
     * 执行任务
     */
    @Test
    public void futureTaskExecuteUse() {
        ExecutorService executor = Executors.newCachedThreadPool();
        MyCallable callable = new MyCallable();
        MyFutureTask<String> task = new MyFutureTask(callable);
        //  执行的是一个task任务，可以通过task任务获取执行结果
        executor.execute(task);
        executor.shutdown();


        try {
            System.err.println("future task exec result is === " + task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.err.println("future use over");
    }

    class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.err.println("这是一个callable接口");
            TimeUnit.MICROSECONDS.sleep(1000);
            return Thread.currentThread().getName();
        }
    }

    /**
     * FutureTask是一个防止Future接口黑核的处理方案
     *
     * @param <T>
     */
    class MyFutureTask<T> extends FutureTask<T> {

        public MyFutureTask(Callable<T> callable) {
            super(callable);
        }

        @Override
        protected void done() {
            try {
                TimeUnit.MICROSECONDS.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.println("Future task exec over,the thread name is " + Thread.currentThread().getName());
        }
    }


    /**
     * 通过提交线程，并在提交线程后获取Future获取执行结果
     * <p>
     * 此时没有办法手动处理线程执行的过程
     */
    @Test
    public void futureUse() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        ArchiveSearcher searcher = target -> {
            TimeUnit.SECONDS.sleep(1L);
            System.err.println("获取参数为：" + target);
            return target;
        };
        //  执行的是一个callable，把执行的结果传递给Future，通过Future获取执行的结果信息
        Future<String> future = executor.submit(() -> searcher.search("测试"));
        try {
            System.err.println("future result is " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.err.println("future task test over");
    }
}
