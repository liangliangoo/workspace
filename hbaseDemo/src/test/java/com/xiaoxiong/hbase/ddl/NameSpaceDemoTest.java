package com.xiaoxiong.hbase.ddl;

import com.xiaoxiong.hbase.utils.HbaseUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/23  20:08
 */
public class NameSpaceDemoTest {

    @Test
    public void create_nameSpace_test() throws IOException {
        NameSpaceDemo nameSpaceDemo = new NameSpaceDemo();
        System.out.println(nameSpaceDemo.createNameSpace("nameSpaceDemo"));
        HbaseUtils.closeConnectionAndAdmin(nameSpaceDemo.getConnection(), nameSpaceDemo.getAdmin());
    }
}
