package com.xiaoxiong.hbase.ddl;

import com.xiaoxiong.hbase.utils.HbaseUtils;
import lombok.Getter;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/23  20:01
 */
public class NameSpaceDemo {

    public static final Logger logger = LoggerFactory.getLogger(TableDemo.class);
    @Getter
    private Admin admin;
    @Getter
    private Connection connection;

    public NameSpaceDemo() throws IOException {
        init();
    }

    public boolean createNameSpace(String nameSpace) {
        NamespaceDescriptor namespaceDescriptor = NamespaceDescriptor.create(nameSpace).build();
        try {
            admin.createNamespace(namespaceDescriptor);
        } catch (IOException e) {
            logger.error("fail to createNameSpace or the {} already existed,because by:{}", nameSpace, e.getMessage());
            return false;
        } finally {

        }
        return true;
    }


    private void init() throws IOException {
        connection = HbaseUtils.getConnection();
        admin = connection.getAdmin();
    }

}
