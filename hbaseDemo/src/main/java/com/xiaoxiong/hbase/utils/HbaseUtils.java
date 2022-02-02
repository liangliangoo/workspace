package com.xiaoxiong.hbase.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/23  13:31
 */
public class HbaseUtils {


    private  static final Logger logger = LoggerFactory.getLogger(HbaseUtils.class);
    private static Configuration configuration = HBaseConfiguration.create();
    static {
        configuration.set("hbase.zookeeper.quorum", "hadoop1,hadoop2,hadoop3");
    }
    public static Admin getAdmin() {

        try (Connection connection = ConnectionFactory.createConnection(configuration)) {
            return connection.getAdmin();
        } catch (Exception e) {
            logger.error("fail to connection hbase,case by {}", e.getMessage(),e);
        }
        return null;
    }

    public static Connection getConnection() throws IOException {
        return ConnectionFactory.createConnection(configuration);
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (IOException e) {
                logger.error("close connection fail,because of :{}", e.getMessage());
            }
        }
    }

    public static void closeConnectionAndAdmin(Connection connection, Admin admin) {
        if (admin != null) {
            try {
                admin.close();
                if (connection != null) {
                    connection.close();
                }
            } catch (IOException e) {
                logger.error("closeConnectionAndAdmin fail,because of: {}", e.getMessage());
            }
        }
    }

}
