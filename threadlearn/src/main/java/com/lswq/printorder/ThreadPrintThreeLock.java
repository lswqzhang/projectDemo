package com.lswq.printorder;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPrintThreeLock {

    private static Lock lock = new ReentrantLock();//通过JDK5中的锁来保证线程的访问的互斥


    private static Condition A = lock.newCondition();
    private static Condition B = lock.newCondition();
    private static Condition C = lock.newCondition();
    

    private static volatile int INT_CHAR = 97;


    
    static class ThreadA extends Thread {
        @Override
        public void run() {
            lock.lock();
            try {
                while (INT_CHAR < 122) {
                    printA_Z();
                    A.await();                      //  阻塞A线程
                    B.signal();                     //  唤醒B线程
                }
            } catch (InterruptedException e) {

            } finally {
                    lock.unlock();

            }

        }
    }
    
    static class ThreadB extends Thread {
        @Override
        public void run() {
            lock.lock();
            try {
                while (INT_CHAR < 122) {
                    printA_Z();
                    B.await();                  //  阻塞B线程
                    C.signal();                 //  唤醒C线程
                }
            } catch (InterruptedException e) {

            } finally {
                lock.unlock();

            }

        }
    }
    
    static class ThreadC extends Thread {
        @Override
        public void run() {
            lock.lock();
            try {
                while (INT_CHAR < 122) {
                    printA_Z();
                    A.signal();             //  唤醒A线程
                    C.await();              //  阻塞C线程
                }
            } catch (InterruptedException e) {

            } finally {
                lock.unlock();

            }
        }
    }



    public static void printA_Z () {
        for (int j = 0; j < 3; j++) {
            if (INT_CHAR > 122) {
                break;
            }
            System.out.println(Thread.currentThread().getName() + (char)(INT_CHAR));
            INT_CHAR++;
        }
    }
    
    public static void main(String[] args) throws InterruptedException {

        new Thread(new ThreadA(), "threadA-->").start();
        new Thread(new ThreadB(), "threadB-->").start();
        new Thread(new ThreadC(), "threadC-->").start();
    }
}
