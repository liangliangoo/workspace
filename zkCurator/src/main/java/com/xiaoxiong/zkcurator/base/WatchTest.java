package com.xiaoxiong.zkcurator.base;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/17  10:24
 */
public class WatchTest {


    private final String ADDRESS = "192.168.44.128:2181";

    private CuratorFramework getInstance() {
        CuratorFramework client = CuratorFrameworkFactory.newClient(ADDRESS, new ExponentialBackoffRetry(1000, 10));
        return client;
    }

    @Test
    public void test_watch1() {
        CuratorFramework client = getInstance();
        client.start();
        //Watch监听机制(NodeCache,PathChildrenCache,TreeCache)
        //NodeCache监听本节点
        //PathChildrenCache监听子节点
        //TreeCache监听本节点加子节点
        TreeCache treeCache = new TreeCache(client, "/test");
        try {
            treeCache.start();
            ChildData currentData = treeCache.getCurrentData("/test");
            Stat stat = currentData.getStat();
            System.out.println(treeCache.getCurrentData("/test"));
            treeCache.getListenable().addListener(new TreeCacheListener() {
                @Override
                public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                    TreeCacheEvent.Type type = event.getType();
                    if (type.equals(TreeCacheEvent.Type.NODE_UPDATED)) {
                        System.out.println("update");
                        // System.out.println(treeCache.getCurrentData("/test").getData());
                    }
                }
            });
            TimeUnit.SECONDS.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            treeCache.close();
            client.close();
        }
    }


    @Test
    public void test_watch2() {
        CuratorFramework client = getInstance();
        client.start();
        PathChildrenCache pathChildrenCache = new PathChildrenCache(client, "/test", true);
        try {
            pathChildrenCache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
            List<ChildData> currentData = pathChildrenCache.getCurrentData();
            for (ChildData cd : currentData) {
                System.out.println(new String(cd.getData(), StandardCharsets.UTF_8));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                pathChildrenCache.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            client.close();
        }



    }

}
