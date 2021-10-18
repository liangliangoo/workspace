package com.xiaoxiong.zkcurator.base;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.nio.charset.StandardCharsets;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/16  16:54
 */
public class CuratorBaseOps2 {

    public static void main(String[] args) {
        RetryPolicy retryPolicy;
        try (CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(Parameter.ADDRESS,
                new ExponentialBackoffRetry(10000, 10, 10000));) {
            curatorFramework.start();
            // String s = curatorFramework.create().forPath("/test", "test".getBytes(StandardCharsets.UTF_8));
            // 创建临时节点
            curatorFramework.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL)
                    .forPath("/test/expire", "EPHEMERAL是短暂的意思".getBytes(StandardCharsets.UTF_8));
            Thread.sleep(30000);

            // System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

interface Parameter {

    String ADDRESS = "192.168.44.128:2181";

}
