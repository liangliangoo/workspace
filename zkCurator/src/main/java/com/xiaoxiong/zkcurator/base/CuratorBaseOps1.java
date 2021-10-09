package com.xiaoxiong.zkcurator.base;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/9/23  17:24
 */
public class CuratorBaseOps1 {

    public static void main(String[] args) {
        try (CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("119.3.140.38:2181",
                new ExponentialBackoffRetry(1000,10))) {
            curatorFramework.start();

            String s = curatorFramework.create().forPath("/child2", "mydata".getBytes());

            System.out.println(s);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

}
