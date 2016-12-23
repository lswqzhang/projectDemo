package com.lswq.semaphorelearn;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Created by zhangsw on 2016/12/23.
 */
public class ConnectPoolSemaphore {


    private final List<Conn> pool = new ArrayList(3);
    private final Semaphore semaphore = new Semaphore(3);


    private class Conn {

    }

    /**
     * 初始化分配3个连接
     */
    public ConnectPoolSemaphore() {
        pool.add(new Conn());
        pool.add(new Conn());
        pool.add(new Conn());
    }


    /**
     * 请求分配连接
     *
     * @return
     * @throws InterruptedException
     */
    public Conn getConn() throws InterruptedException {
        semaphore.acquire();
        Conn c = null;
        synchronized (pool) {
            c = pool.remove(0);
        }
        System.out.println(Thread.currentThread().getName() + " get a conn " + c);
        return c;
    }


    /**
     * 释放连接
     *
     * @param c
     */
    public void release(Conn c) {
        pool.add(c);
        System.out.println(Thread.currentThread().getName() + " release a conn " + c);
        semaphore.release();
    }



    public static void main(String[] args) {

        final ConnectPoolSemaphore pool = new ConnectPoolSemaphore();

        /**
         * 第一个线程占用1个连接3秒
         */
        new Thread()
        {
            public void run()
            {
                try
                {
                    Conn c = pool.getConn();
                    Thread.sleep(3000);
                    pool.release(c);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
        }.start();
        /**
         * 开启3个线程请求分配连接
         */
        for (int i = 0; i < 3; i++)
        {
            new Thread()
            {
                public void run()
                {
                    try
                    {
                        Conn c = pool.getConn();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }

    }

}
