package com.lswq.jdbc;

import org.junit.Test;

import java.sql.Connection;

/**
 * Created by zhangshaowei on 2017/5/12.
 */
public class JDBCTests {

    @Test
    public void getConnect() {
        Connection connection = DataSourceUtils.getConnection();
    }
}
