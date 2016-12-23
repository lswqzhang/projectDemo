package com.lswq.printorder;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPrintThreeLock {

    private static Lock lock = new ReentrantLock();//通过JDK5中的锁来保证线程的访问的互斥
    

    private static volatile int INT_CHAR = 97;


    
    static class ThreadA extends Thread {
        @Override
        public void run() {
            while (INT_CHAR < 122) {
                lock.lock();
                for (int j = 0; j < 3; j++) {
                    System.out.println("Thread--A --> " + (char)(INT_CHAR));
                    INT_CHAR++;
                }
                lock.unlock();
            }
        }
    }
    
    static class ThreadB extends Thread {
        @Override
        public void run() {
            while (INT_CHAR < 122) {
                lock.lock();
                for (int j = 0; j < 3; j++) {
                    System.out.println("Thread--B --> " + (char)(INT_CHAR));
                    INT_CHAR++;
                }
                lock.unlock();
            }
        }
    }
    
    static class ThreadC extends Thread {
        @Override
        public void run() {
            while (INT_CHAR < 122) {
                lock.lock();
                for (int j = 0; j < 3; j++) {
                    System.out.println("Thread--C--> " + (char)(INT_CHAR));
                    INT_CHAR++;
                }
                lock.unlock();
            }
        }
    }
    
    public static void main(String[] args) {
        new ThreadA().start();
        new ThreadB().start();
        new ThreadC().start();
    }
}
