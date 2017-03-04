package test.com.lswq.app.util;

import org.junit.Test;

import java.util.Objects;

/**
 * Created by zhangsw on 2017/2/2.
 */
public class SimpleTest {

    @Test
    public void test() {
        Integer a = 1;
        Integer b = 1;
        Objects.equals(a, b);
    }
}
