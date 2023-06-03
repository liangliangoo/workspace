package com.xiaoxiong.interview.thread_exec_order;

/**
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2022/5/23  18:35
 */
public class ThreadDemo1 {


    public static void main(String[] args) throws InterruptedException {

        String a = "a";
        String b = "b";
        String c = "c";

        MyThread t1 = new MyThread("A", c, a);
        MyThread t2 = new MyThread("B", a, b);
        MyThread t3 = new MyThread("C", b, c);

        // 依赖线程启动的顺序
        new Thread(t1).start();
        Thread.sleep(50);
        new Thread(t2).start();
        Thread.sleep(50);
        new Thread(t3).start();
        Thread.sleep(50);
    }


}

class MyThread implements Runnable {

    private String name;
    private String prev;
    private String self;

    public MyThread(String name, String prev, String self) {
        this.name = name;
        this.prev = prev;
        this.self = self;
    }

    @Override
    public void run() {
        int count = 0;
        while (count < 3) {
            synchronized (prev) {
                synchronized (self) {
                    checker(count);
                    count++;
                    //用完了 释放self锁
                    self.notify();
                }
                try {
                    if (count != 3) {
                        // 最后一次 不需要wait了 如果wait会导致卡在这
                        prev.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void checker(int count) {
        System.out.println(name + ":   A" + count + "   B" + count + "   C" + count);
    }

}
