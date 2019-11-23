package com.example.webdemo.utils;


import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@Slf4j
public class OracleConnTest {//创建test类，保证文件名与类名相同


    public static Connection getConnection(String ip, String tableName, String userCode, String password) {  //建立返回值为Connection的方法
        Connection conn = null;//声明Connection对象

        try {        //加载数据库驱动类
            Class.forName("oracle.jdbc.OracleDriver");
            log.info("数据库驱动加载成功");  //返回加载驱动成功信息
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection("jdbc:oracle:" + "thin:@" + ip + ":1521:" + tableName, userCode, password);//通过访问数据库的URL获取数据库连接对象 ，这里后两个参数分别是数据库的用户名及密码
            log.info("数据库连接成功");  //返回连接成功信息
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;//按方法要求返回一个Connection对象
    }
}
