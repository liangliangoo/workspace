package com.xiaoxiong.middleware.ck;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author 六月
 * @Date 2023/8/18 12:15
 * @Version 1.0
 */
public class CkMain {

    @SneakyThrows
    public static void main(String[] args) {
        String driverName="ru.yandex.clickhouse.ClickHouseDriver";
        String url="jdbc:clickhouse://121.4.26.248:8123";
        String user="default";
        String password="xiongliang";
        String sql="select * from user;";
        String[] params={};
        ConnEntiy connEntiy = new ConnEntiy(driverName, url, user, password);
        ClickHouseUtils utils = new ClickHouseUtils();
        Connection conn = utils.connection(connEntiy);
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        int begin = 200000000;
        for (int i1 = 0; i1 < 100; i1++) {
            int finalBegin = begin;
            executorService.execute(() -> {
                for (int i = finalBegin; i < finalBegin + 100000; i++) {
                    System.out.println(Thread.currentThread().getName());
                    utils.insert(conn, String.format("insert into user values (%s, '%s', '%s', %s, '%s')", i, "username" + i, "clazz" + i, 18, "config"), null);
                }
            });
            begin += 10000000;
        }
        System.in.read();
    }

}
