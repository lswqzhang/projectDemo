package com.lswq.model.creater.singleton;

/**
 * Created by zhangsw on 2016/12/23.
 */
public class SyncSafeSingleton {

    private static volatile SyncSafeSingleton instance = null;

    private SyncSafeSingleton() {
        System.err.println("创建的单例对象");
    }

    public static SyncSafeSingleton getInstance() {
        if (instance == null) {
            synchronized (SyncSafeSingleton.class) {
                if (instance == null) {
                    instance = new SyncSafeSingleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        getInstance();
    }

}
