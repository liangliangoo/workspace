package com.xiaoxiong.hbase.ddl;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.junit.Test;

import java.io.IOException;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/23  13:14
 */
public class TableTest {

    @Test
    public void test_isExistTable() {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum","hadoop1,hadoop2,hadoop3");
        try(Connection connection = ConnectionFactory.createConnection(configuration)) {
            Admin admin = connection.getAdmin();
            System.out.println(admin.tableExists(TableName.valueOf("a")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
