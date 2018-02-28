package com.lswq.theadlocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadLocalDemo
 * 
 * @author zhangsw 
 */
public class ThreadLocalSimpleDateFormat {

    /**
     * 定义一个ThreadLocal
     */
    private static ThreadLocal<SimpleDateFormat> thread = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    /**
     * 内部类用于调用ThreadLocal并进行时间解析
     */
    public static class ParseDate implements Runnable {

        public int i;

        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                //  从ThreadLocal中获取SimpleDateFormat并使用
                Date t = thread.get().parse("2015-03-09");
                System.err.println(i + ":" + t);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1; i++) {
            es.execute(new ParseDate(i));
        }
    }

}
