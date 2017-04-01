package com.lswq.joda;

import org.junit.Test;

import java.util.Date;

/**
 * Created by zhangshaowei on 2017/3/31.
 */
public class JodaUtilsTest {


    @Test
    public void jodaTests() {
        Date theDate = new Date(1488297600000L);
        Date theOtherDate = new Date(1488383999000L);
        boolean b = JodaUtils.theSameDay(theDate, theOtherDate);
        System.err.println("the same day is " + b);
    }
}
