package com.xiaoxiong.hbase.dml;

import com.xiaoxiong.hbase.utils.HbaseUtils;
import lombok.Getter;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/23  20:46
 */
public class DmlDemo {

    @Getter
    private Connection connection;

    public DmlDemo() {
        try {
            connection = HbaseUtils.getConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void put(String tableName, String rowKey, String cf, String clm, String value) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Put put = new Put(rowKey.getBytes(StandardCharsets.UTF_8));
        put.addColumn(cf.getBytes(StandardCharsets.UTF_8), clm.getBytes(StandardCharsets.UTF_8), value.getBytes(StandardCharsets.UTF_8));
        table.put(put);
        table.close();
    }
}
