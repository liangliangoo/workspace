package com.xiaoxiong.hbase.utils;

import org.apache.hadoop.hbase.client.Admin;
import org.junit.Test;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/23  13:38
 */
public class HbaseUtilsTest {

    @Test
    public void test_getAdmin() {
        Admin admin = HbaseUtils.getAdmin();
    }
}
