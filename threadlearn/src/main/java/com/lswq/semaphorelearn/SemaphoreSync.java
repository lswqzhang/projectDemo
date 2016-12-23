package com.lswq.semaphorelearn;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * Created by zhangsw on 2016/12/23.
 * <p>
 * <p>
 * 通过以下两种方式获得许可
 * <p>
 * acquire()
 * tryAcquire()
 */
public class SemaphoreSync {


    private final Set<Integer> set;

    private final Semaphore sem;

    public SemaphoreSync(int bound) {
        //  使用同步的set
        this.set = Collections.synchronizedSet(new HashSet<Integer>());
        sem = new Semaphore(bound);
    }

    public boolean add(Integer o) throws InterruptedException {
        sem.acquire(); //如果信号量已被用光阻塞
        boolean wasAdded = false;
        try {
            wasAdded = set.add(o);
            return wasAdded;
        } finally {
            if (!wasAdded) {
                sem.release();
            }
        }
    }

    public boolean addNoAwait(Integer o) throws InterruptedException {
        boolean wasAdded = false;
        if (sem.tryAcquire()) {
            try {
                wasAdded = set.add(o);
            } finally {
                if (!wasAdded) {
                    sem.release();
                }
            }
        }
        return wasAdded;
    }

    public boolean remove(Integer o) {
        boolean wasRemoved = set.remove(o);
        if (wasRemoved) {
            sem.release();
        }
        return wasRemoved;
    }

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        SemaphoreSync test = new SemaphoreSync(5);
        for (int i = 0; i < 10; i++) {
            System.err.println("add " + i + "=" + test.add(i));
            System.err.println("add " + i + "=" + test.addNoAwait(i));
        }
    }

}
