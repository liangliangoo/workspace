package com.demo.jdbc;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/6/30 9:49
 * 传统jdbc是如何解决插入问题的
 */
@Slf4j
public class JdbcBatchInsert {

    public static final String SQL = "insert into user(name,age,email) values(?,?,?)";

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis_soursecode?serverTimezone=UTC",
                "root", "xiongliang")) {
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            for (int i = 0; i < 10; i++) {
                preparedStatement.setString(1, "小熊-" + i);
                preparedStatement.setInt(2, i + 18);
                preparedStatement.setString(3, "xiaoxiong" + i + "@163.com");
                preparedStatement.addBatch();
            }
            preparedStatement.execute();
            connection.commit();

        } catch (SQLException e) {
            log.error("JdbcBatchInsert error,caused by:{},{}", e.getMessage());
            e.printStackTrace();
        }
    }

}
