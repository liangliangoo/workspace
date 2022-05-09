package com.xiaoxiong.learning.jdbc;

import java.sql.*;

/**
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2022/5/9  12:07
 */
public class DriverTest {

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/demo?serverTimezone=UTC",
                "root", "xiongliang")) {
            PreparedStatement preparedStatement = connection.prepareStatement("select 1");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
