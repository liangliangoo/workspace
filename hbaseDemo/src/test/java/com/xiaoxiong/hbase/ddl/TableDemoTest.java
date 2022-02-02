package com.xiaoxiong.hbase.ddl;

import com.xiaoxiong.hbase.utils.HbaseUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/23  15:56
 */
public class TableDemoTest {

    @Test
    public void create_table_test() throws IOException {
        TableDemo createTable = new TableDemo();
        System.out.println(createTable.createTable("teacher1", "info1", "info2"));
        HbaseUtils.closeConnectionAndAdmin(createTable.getConnection(), createTable.getAdmin());
    }

    @Test
    public void drop_table_test() throws IOException {
        TableDemo tableDemo = new TableDemo();
        tableDemo.dropTable("teacher1");
        HbaseUtils.closeConnectionAndAdmin(tableDemo.getConnection(), tableDemo.getAdmin());
    }

}
