package com.lswq.semaphorelearn;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by zhangsw on 2016/12/23.
 */
public class TestSemaphore {


    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();

        final Semaphore semp = new Semaphore(5);

        for (int i = 0; i < 20; i++) {
            final int NO = i;
            Runnable run = new Runnable() {
                @Override
                public void run() {

                    try {

                        // 获取许可
                        semp.acquire();

                        System.out.println("Accessing: " + NO);

                        Thread.sleep((long) (Math.random() * 10000));

                        // 访问完后，释放
                        semp.release();

                        System.out.println("-----------------" + semp.availablePermits());

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            exec.execute(run);
        }
        // 退出线程池
        exec.shutdown();
    }


}
