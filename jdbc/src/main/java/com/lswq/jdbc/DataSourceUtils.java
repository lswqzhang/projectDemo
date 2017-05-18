package com.lswq.jdbc;

/**
 * Created by zhangshaowei on 2017/5/12.
 */

import com.mchange.v2.c3p0.ComboPooledDataSource;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;

public class DataSourceUtils {

    private static DataSource ds;

    static {
        /*
         * 从配置文件读取配置信息 <?xml version="1.0" encoding="UTF-8"?> <c3p0-config> <!--
         * 默认配置，如果没有指定则使用这个配置 --> <default-config> <property
         * name="checkoutTimeout">30000</property> <property
         * name="idleConnectionTestPeriod">30</property> <property
         * name="initialPoolSize">3</property> <property
         * name="maxIdleTime">30</property> <property
         * name="maxPoolSize">10</property> <property
         * name="minPoolSize">3</property> <property
         * name="maxStatements">50</property> <property
         * name="acquireIncrement">3</property><!-- 如果池中数据连接不够时一次增长多少个 -->
         * <property name="driverClass">com.mysql.jdbc.Driver</property>
         * <property name="jdbcUrl">
         * <![CDATA[jdbc:mysql://127.0.0.1:3306/project?useUnicode=true&
         * characterEncoding=UTF-8]]> </property> <property
         * name="user">root</property> <property
         * name="password">789123</property> </default-config> </c3p0-config>
         *
         * ds = // 默认的读取c3p0-config.xml中默认配置 new ComboPooledDataSource();
         */
        ComboPooledDataSource cpds = null;
        try {
            cpds = new ComboPooledDataSource();
            cpds.setCheckoutTimeout(30000);
            cpds.setIdleConnectionTestPeriod(30);
            cpds.setInitialPoolSize(3);
            cpds.setMaxIdleTime(30);
            cpds.setMaxPoolSize(70);
            cpds.setMaxStatementsPerConnection(100);
            cpds.setMinPoolSize(3);
            cpds.setMaxStatements(75);
            cpds.setAcquireIncrement(3);
            cpds.setDriverClass(GlobalConstant.CONNECTION_DRIVER);
            cpds.setJdbcUrl(GlobalConstant.CONNECTION_URL);
            cpds.setUser(GlobalConstant.CONNECTION_USER);
            cpds.setPassword(GlobalConstant.CONNECTION_PASSWORD);
            ds = cpds;
        } catch (PropertyVetoException e) {
            System.out.println("与MySQL数据库连接失败！");
        }
    }

    private DataSourceUtils() {

    }

    public static DataSource getDatasSource() {
        return ds;
    }

    public static Connection getConnection() {
        Connection con = null;
        try {
            con = ds.getConnection();// 每一次从ds中获取一个新的连接
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}

