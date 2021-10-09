package com.xiaoxiong.zkcurator.base;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

import java.nio.charset.StandardCharsets;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/9/23  11:10
 */
public class CuratorBaseOps {

    private final int A = 1000;

    // 连接超时时间
    private static final int CONNECTION_TIMEOUT = 3 * 1000;

    //会话超时时间
    private static final int SESSION_TIMEOUT = 30 * 1000;

    // zk地址
    private static final String CONNECT_ADDR = "47.112.159.14:2181";


    public static void main(String[] args) throws Exception {

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1, 10);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .retryPolicy(retryPolicy)
                .connectString(CONNECT_ADDR)
                .sessionTimeoutMs(SESSION_TIMEOUT)
                .connectionTimeoutMs(CONNECTION_TIMEOUT)
                .build();

        // 开启一个连接
        client.start();

       client.create().forPath("/child11", "/try to use child1".getBytes());
       System.out.println(client.getData().forPath("/curator"));

        byte[] bytes = client.getData().forPath("/child11").toString().getBytes(StandardCharsets.UTF_8);

        // System.out.println(new String(bytes));

        //测试检查某个节点是否存在
        Stat stat1 = client.checkExists().forPath("/child11");
        System.out.println(stat1 == null);
        System.out.println();
    }

}
