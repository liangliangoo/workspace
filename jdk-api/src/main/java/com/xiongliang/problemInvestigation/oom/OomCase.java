package com.xiongliang.problemInvestigation.oom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.System.out;

/**
 * oom 场景
 * @Author 六月
 * @Date 2023/6/3 14:51
 * @Version 1.0
 */
public class OomCase {

    public static void main(String[] args) throws IOException {
        OomCase oomCase = new OomCase();
        oomCase.mockOomCase1();
        System.in.read();
    }

    /**
     * jvm 启动参数： -Xms16m -Xmx32m
     * 同一个进程内的某个线程OOM，那么其他线程还能够正常运行吗？
     */
    public void mockOomCase1() {

        new Thread(() -> {
            List<byte[]> list = new ArrayList<>();
            while (true) {
                out.println(new Date().toString() + Thread.currentThread() + "==");
                byte[] b = new byte[1024 * 1024];
                list.add(b);
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                out.println(new Date().toString() + Thread.currentThread() + "==");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
