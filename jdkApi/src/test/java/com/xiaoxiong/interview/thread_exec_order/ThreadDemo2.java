package com.xiaoxiong.interview.thread_exec_order;

import lombok.SneakyThrows;

import java.util.concurrent.Semaphore;

/**
 * @author xiongliang
 * @version 1.0
 * @description 信号量
 * @since 2022/5/23  18:56
 */
public class ThreadDemo2 {

    @SneakyThrows
    public static void main(String[] args) {
        Semaphore a = new Semaphore(0);
        Semaphore b = new Semaphore(0);
        // 关键点
        Semaphore c = new Semaphore(1);
        new Thread(new MyThreadSemaphore(a, b, "B")).start();
        new Thread(new MyThreadSemaphore(b, c, "C")).start();
        Thread.sleep(500);
        new Thread(new MyThreadSemaphore(c, a, "A")).start();
    }
}

class MyThreadSemaphore implements Runnable {

    Semaphore pre, self;
    String name;

    public MyThreadSemaphore(Semaphore pre, Semaphore self, String name) {
        this.pre = pre;
        this.self = self;
        this.name = name;
    }

    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                pre.acquire();
                checker(i);
                self.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private void checker(int count) {
        System.out.println(name + ":   A" + count + "   B" + count + "   C" + count);
    }

}
