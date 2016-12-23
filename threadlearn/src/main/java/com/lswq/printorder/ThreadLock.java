package com.lswq.printorder;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadLock {

    private static Lock lock = new ReentrantLock();//通过JDK5中的锁来保证线程的访问的互斥
    
    private static int state = 0;
    
    private static volatile int INT_CHAR = 97;
    
    
    static class ThreadA extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10;) {
                lock.lock();
                if (state % 3 == 0) {
                    System.out.println("ThreadA --> " + (char)(INT_CHAR + state));
                    state++;
                    i++;
                }
                lock.unlock();
            }
        }
    }
    
    static class ThreadB extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10;) {
                lock.lock();
                if (state % 3 == 1) {
                    System.out.println("ThreadB --> " + (char)(INT_CHAR + state));
                    state++;
                    i++;
                }
                lock.unlock();
            }
        }
    }
    
    static class ThreadC extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10;) {
                lock.lock();
                if (state % 3 == 2) {
                    System.out.println("ThreadC --> " + (char)(INT_CHAR + state));
                    state++;
                    i++;
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
