package com.lswq.reenter;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhangsw on 2016/12/23.
 */
public class PrintServer {
    private Lock lock = new ReentrantLock();

    private Condition aLock = lock.newCondition();
    private Condition bLock = lock.newCondition();
    private Condition cLock = lock.newCondition();

    private static volatile int INT_CHAR = 97;


    public void printMethod() {
        lock.lock();
        for (int i = 0; i < 3; i++) {
            if (INT_CHAR > 122) {
                break;
            }
            System.err.println("Thread name is " + Thread.currentThread().getName() + (" " + (char) (INT_CHAR ++)));
        }
        lock.unlock();
    }


    private void lockManage(Condition await, Condition single) {
        while (INT_CHAR < 122) {
            lock.lock();
            printMethod();
            try {
                await.await();
                single.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }
    }


    class MyThreadA extends Thread {


        @Override
        public void run() {
            lockManage(aLock, bLock);
        }



    }

    class MyThreadB extends Thread {


        @Override
        public void run() {
            lockManage(bLock, cLock);
        }

    }

    class MyThreadC extends Thread {


        @Override
        public void run() {
            while (INT_CHAR < 122) {
                lock.lock();
                printMethod();
                try {
                    aLock.signal();
                    cLock.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();
            }
        }

    }

    public void runThread () {
        MyThreadA thread1 = new MyThreadA();
        MyThreadB thread2 = new MyThreadB();
        MyThreadC thread3 = new MyThreadC();
        new Thread(thread1, "Thread A").start();
        new Thread(thread2, "Thread B").start();
        new Thread(thread3, "Thread C").start();
    }

    public static void main(String[] args) {
        PrintServer server = new PrintServer();
        server.runThread();
    }
}
