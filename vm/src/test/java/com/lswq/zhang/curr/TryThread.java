package com.lswq.zhang.curr;

import java.util.concurrent.TimeUnit;

public class TryThread {

    public static void main(String[] args) {
        try {
            TimeUnit.MINUTES.sleep(3L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
