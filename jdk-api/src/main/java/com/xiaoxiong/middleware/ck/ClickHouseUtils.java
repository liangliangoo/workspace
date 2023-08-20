package com.xiaoxiong.middleware.ck;

import ru.yandex.clickhouse.ClickHousePreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author 六月
 * @Date 2023/8/18 12:15
 * @Version 1.0
 */
public class ClickHouseUtils {

    public Connection connection(ConnEntiy connEntiy) {
        Connection conn = null;
        try {
            Class.forName(connEntiy.getDriverName());
            conn = DriverManager.getConnection(connEntiy.getUrl(), connEntiy.getUser(), connEntiy.getPassword());
        } catch (Exception e) {
            System.out.println("connection fail ,please check your entities");
        }
        return conn;
    }

    public void close(AutoCloseable... closes) {
        for (AutoCloseable close : closes) {
            if (close != null) {
                try {
                    close.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    close = null;
                }
            }
        }
    }

    public boolean insert(Connection connection, String sql, String... params) {
        boolean b = false;
        ClickHousePreparedStatement pst = null;
        if (connection == null) {
            System.out.println("connection is empty");
            System.exit(-1);
        }
        try {
            pst = (ClickHousePreparedStatement) connection.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pst.setObject(i + 1, params[i]);
                }
            }
            b = pst.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            // close(pst, connection);
        }

        return b;
    }

    public boolean delete(Connection connection, String sql, String... params) {
        boolean b = false;
        ClickHousePreparedStatement pst = null;
        if (connection == null) {
            System.out.println("connection is empty");
            System.exit(-1);
        }
        try {
            pst = (ClickHousePreparedStatement) connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                pst.setObject(i + 1, params[i]);
            }
            b = pst.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(pst, connection);
        }

        return b;
    }

    public ResultSet queryResultSet(Connection connection, String sql, String... params) {
        ResultSet rst = null;
        ClickHousePreparedStatement pst = null;
        if (connection == null) {
            System.out.println("connection is empty");
            System.exit(-1);
        }
        try {
            pst = (ClickHousePreparedStatement) connection.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pst.setObject(i + 1, params[i]);
                }
            }
            rst = pst.executeQuery();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(rst, pst, connection);
        }
        return rst;
    }


}
