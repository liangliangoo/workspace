package com.xiaoxiong.hbase.dml;

import com.xiaoxiong.hbase.utils.HbaseUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/23  20:52
 */
public class DmlDemoTest {

    @Test
    public void put_test() throws IOException {
        DmlDemo dmlDemo = new DmlDemo();
        dmlDemo.put("student","3","info","name","xiaojiang");
        HbaseUtils.closeConnection(dmlDemo.getConnection());
    }
}
