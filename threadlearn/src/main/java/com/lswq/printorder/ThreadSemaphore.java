package com.lswq.printorder;

import java.util.concurrent.Semaphore;

public class ThreadSemaphore {
    
    private static volatile int INT_CHAR = 97;
    
    private static Semaphore A = new Semaphore(1);
    private static Semaphore B = new Semaphore(1);
    private static Semaphore C = new Semaphore(1);
    
    static class ThreadA implements Runnable {

        public void run() {
            try {
                while (INT_CHAR < 122) {
                    A.acquire();
                    printA_Z();
                    B.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    }
    
    static class ThreadB implements Runnable {

        public void run() {
            try {
                while (INT_CHAR < 122) {
                    B.acquire();
                    printA_Z();
                    C.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    }
    
    static class ThreadC implements Runnable {

        public void run() {
            try {
                while (INT_CHAR < 122) {
                    C.acquire();
                    printA_Z();
                    A.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    private static void printA_Z() {
        if (INT_CHAR > 122) {
            return;
        }
        System.out.print(Thread.currentThread().getName());
        for (int j = 0; j < 3; j++) {
            if (INT_CHAR > 122) {
                break;
            }
            System.out.print((char)INT_CHAR++);
        }
        System.out.println();
    }
    
    public static void main(String[] args) throws InterruptedException {
        B.acquire();     // 同一个时间只有一个信号量，因此可以看作是互斥的，必须释放以后才可以进行下步操作
        C.acquire();     // 开始只有A可以获取, BC都不可以获取, 保证了A最先执行
        

        new Thread(new ThreadA(), "A-->").start();
        new Thread(new ThreadB(), "B-->").start();
        new Thread(new ThreadC(), "C-->").start();
    }
}
