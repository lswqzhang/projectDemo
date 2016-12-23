package com.lswq.safetrhread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by zhangsw on 2016/12/22.
 */
public class ArrayListAddTest {

    public static void main(String[] args) throws InterruptedException {

        ArrayListAddTest test = new ArrayListAddTest();
        test.testAddList();

    }


    /**
     * 测试多线程增加元素操作
     * @throws InterruptedException
     */
    public void testAddList() throws InterruptedException {
        List<Object> list = new ArrayList();
        int threadCount = 10000;
        CountDownLatch latch = new CountDownLatch(threadCount);


        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new ListAddThread(list, latch));
            thread.start();
        }

        // 等待线程结束
        latch.await();


        // List的size
        System.out.println(list.size());
    }


    class ListAddThread implements Runnable {




        private List<Object> list;
        private CountDownLatch latch;

        public ListAddThread(List<Object> list, CountDownLatch latch) {
            this.list = list;
            this.latch = latch;
        }

        @Override
        public void run() {
            //  每个线程添加100个元素
            for (int i = 0; i < 100; i++) {
                list.add(new Object());
            }
            //  完成一个子线程后，减一
            latch.countDown();
        }
    }
}
