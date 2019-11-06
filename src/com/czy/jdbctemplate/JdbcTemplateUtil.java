package com.czy.jdbctemplate;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcTemplateUtil {

    private static DataSource dataSource;

    static {
        Properties pro=new Properties();
        InputStream in =JdbcTemplateUtil.class.getResourceAsStream("/jdbctemplate.properties");
        try {
            pro.load(in);
            dataSource= DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static DataSource getDataSource(){
        return dataSource;
    }

    public static Connection getConnection() {
        Connection conn=null;

        try {
            conn=dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public static void close(Connection conn, Statement stat, ResultSet rs){
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (stat != null) {
                stat.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
