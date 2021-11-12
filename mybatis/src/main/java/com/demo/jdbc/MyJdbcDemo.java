package com.demo.jdbc;

import org.springframework.util.Assert;

import java.sql.*;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/12  9:05
 */
public class MyJdbcDemo {

    private static PreparedStatement preparedStatement;
    private static Connection connection;

    public static void main(String[] args) {

        try {
            Class<?> driver = Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis_soursecode?serverTimezone=UTC", "root", "xiongliang");

            preparedStatement = connection.prepareStatement("select * from user");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                // 这边需要注意：下标位置是从1开始的
                String str = resultSet.getString(2);
                System.out.println(str);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                Assert.notNull(preparedStatement,"");
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
