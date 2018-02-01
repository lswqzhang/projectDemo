package com.lswq.jvm.linuxcommand;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class HoldIOMain {
    public static class HoldIOTask implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    FileOutputStream fos = new FileOutputStream(new File("temp"));
                    // 大量写操作
                    for (int i = 0; i < 10000; i++) {
                        fos.write(i);
                    }
                    fos.close();
                    FileInputStream fis = new FileInputStream(new File("temp"));
                    // 大量读操作
                    while (fis.read() != -1) ;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static class LazyTask implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    // 空闲线程
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new HoldIOTask()).start();
        new Thread(new LazyTask()).start();
        new Thread(new LazyTask()).start();
        new Thread(new LazyTask()).start();
    }
}
