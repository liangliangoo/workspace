package com.xiaoxiong.thread.communication;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author 小熊不会写代码
 * @date 2023/8/9
 */
public class FooBar {

    private int n;
    private CyclicBarrier cb = new CyclicBarrier(2);
    volatile boolean fin = true;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while (!fin) {

            }
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            fin = false;
            try {
                cb.await();
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            cb.wait();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            fin = true;
        }
    }

}
