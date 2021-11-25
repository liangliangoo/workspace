package com.xiaoxiong.hbase.ddl;

import com.xiaoxiong.hbase.utils.HbaseUtils;
import lombok.Getter;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/23  15:02
 */
public class TableDemo {

    public static final Logger logger = LoggerFactory.getLogger(TableDemo.class);
    @Getter
    private Admin admin;
    @Getter
    private Connection connection;

    public TableDemo() throws IOException {
        init();
    }

    public boolean createTable(String tableName, String... columnNames) throws IOException {

        if (columnNames.length == 0) {
            logger.error("column Family counts must be ge 1");
            return false;
        }

        if (admin.tableExists(TableName.valueOf(tableName))) {
            return false;
        }

        ArrayList<ColumnFamilyDescriptor> columnFamilyDescriptors = Arrays.asList(columnNames).stream().collect(() -> new ArrayList<ColumnFamilyDescriptor>(), (list, cln) -> {
            list.add(ColumnFamilyDescriptorBuilder.newBuilder(cln.getBytes(StandardCharsets.UTF_8)).build());
        }, (list, tmp) -> list.addAll(tmp));
        TableDescriptor tableDescriptor = TableDescriptorBuilder.newBuilder(TableName.valueOf(tableName))
                .setColumnFamilies(columnFamilyDescriptors).build();
        try {
            admin.createTable(tableDescriptor);
        } catch (IOException e) {
            logger.error("fail to create table,cased by {}", e.getMessage(), e);
            return false;
        }

        return true;
    }

    public void dropTable(String tableName) throws IOException {
        admin.disableTable(TableName.valueOf(tableName));
        admin.deleteTable(TableName.valueOf(tableName));
    }

    private void init() throws IOException {
        connection = HbaseUtils.getConnection();
        admin = connection.getAdmin();
    }

}
