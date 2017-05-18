package com.lswq.jdbc;

import java.sql.*;

/**
 * Created by zhangshaowei on 2017/5/12.
 */
public class JDBCUtil {

    private JDBCUtil() {

    }

    /**
     * 获取JDBC的数据库连接
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(GlobalConstant.CONNECTION_DRIVER);
            conn = DriverManager.getConnection(GlobalConstant.CONNECTION_URL, GlobalConstant.CONNECTION_USER, GlobalConstant.CONNECTION_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*
         * try { conn = DataSourceService.getConnection(); } catch (SQLException
         * e) { e.printStackTrace(); }
         */
        return conn;
    }

    private static void close(ResultSet rs, Statement sm, Connection conn) {
        try {
            if (rs != null)
                rs.close();
            if (sm != null)
                sm.close();
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭数据库的结果集
     *
     * @param rs
     *            需要关闭的结果集
     */
    public static void close(ResultSet rs) {
        try {
            if (rs != null)
                rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将一个标准的sql查询语句在数据库中查询,并且返回一个对应的结果集。
     *
     * @param sql
     *            标准的sql查询语句
     */
    public static ResultSet doQuery(String sql) {
        Connection conn = getConnection();
        if (conn == null)
            return null;
        Statement sm = null;
        ResultSet rs = null;
        try {
            sm = conn.createStatement();
            rs = sm.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs == null && sm != null) {
                close(null, sm, conn);
            }
            // close(null, sm, conn);这句肯定不行
        }
        return rs;
    }

    /**
     * 将一个更新语句和对应的参数进行查询,并返回一个整形结果
     *
     * @param sql
     *            数据库中的更新语句
     * @param paras
     *            sql中对应的参数
     */
    public static int doUpdate(String sql, String[] paras) {
        Connection conn = getConnection();
        if (conn == null)
            return 0;
        PreparedStatement psm = null;
        int result = 0;
        try {
            psm = conn.prepareStatement(sql);
            for (int i = 0; i < paras.length; i++) {
                psm.setString(i + 1, paras[i]);
            }
            result = psm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(null, psm, conn);
        }
        return result;
    }
}