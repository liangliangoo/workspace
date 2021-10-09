package com.xiaoxiong.zkcurator.base;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/9/23  19:17
 *
 * 本地的zk  version:3.5.9
 */
public class ZkClient {

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {

        Watcher watcher = new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println(watchedEvent.getPath());
                if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
                    System.out.println(false);
                }
            }
        };

        ZooKeeper zk = new ZooKeeper("127.0.0.1:2181", 10, watcher);

        String s = zk.create("/child2", "hello zk".getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        System.out.println(s);

        zk.setData("/child2", "hello zk".getBytes(StandardCharsets.UTF_8), -1);

        Stat stat = new Stat();

        byte[] child2s = zk.getData("child2", watcher, stat);

        System.out.println(new String(child2s));
    }

}
