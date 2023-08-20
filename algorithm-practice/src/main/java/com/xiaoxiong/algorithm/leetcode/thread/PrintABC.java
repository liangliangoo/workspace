package com.xiaoxiong.algorithm.leetcode.thread;

import lombok.SneakyThrows;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 建立三个线程A、B、C，A线程打印10次字母A，B线程打印10次字母B,C线程打印10次字母C，但是要求三个线程同时运行，并且实现交替打印，即按照ABCABCABC的顺序打印。
 *
 * @author 小熊不会写代码
 * @date 2023/8/20
 */
public class PrintABC {

    private static Lock lock = new ReentrantLock();
    private static int state = 0;// 确定打印顺序

    public static void main(String[] args) {
        // synchronized + obj
        // synchronizedObject();

        lockAndState();
    }


    @SneakyThrows
    private static void synchronizedObject() {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        ThreadPrinter at = new ThreadPrinter("A", c, a);
        ThreadPrinter bt = new ThreadPrinter("B", a, b);
        ThreadPrinter ct = new ThreadPrinter("C", b, c);
        new Thread(at).start();
        Thread.sleep(10);
        new Thread(bt).start();
        Thread.sleep(10);
        new Thread(ct).start();
        Thread.sleep(10);
    }

    private static void lockAndState() {
        new LockPrintA().start();
        new LockPrintB().start();
        new LockPrintC().start();
    }


    static class ThreadPrinter implements Runnable {

        private String name;
        private Object prev;
        private Object self;

        public ThreadPrinter(String name, Object prev, Object self) {
            this.name = name;
            this.prev = prev;
            this.self = self;
        }

        @Override
        public void run() {
            int count = 10;
            while (count > 0) {// 多线程并发，不能用if，必须使用whil循环
                synchronized (prev) { // 先获取 prev 锁
                    synchronized (self) {// 再获取 self 锁
                        System.out.print(name);// 打印
                        count--;
                        self.notifyAll();// 唤醒其他线程竞争self锁，注意此时self锁并未立即释放。
                    }
                    // 此时执行完self的同步块，这时self锁才释放。
                    try {
                        if (count == 0) {// 如果count==0,表示这是最后一次打印操作，通过notifyAll操作释放对象锁。
                            prev.notifyAll();
                        } else {
                            prev.wait(); // 立即释放 prev锁，当前线程休眠，等待唤醒
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class LockPrintA extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; ) {
                try {
                    lock.lock();
                    while (state % 3 == 0) {
                        System.out.print("A");
                        state++;
                        i++;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    static class LockPrintB extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; ) {
                try {
                    lock.lock();
                    while (state % 3 == 1) {
                        System.out.print("B");
                        state++;
                        i++;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    static class LockPrintC extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; ) {
                try {
                    lock.lock();
                    while (state % 3 == 2) {
                        System.out.print("C");
                        state++;
                        i++;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }

}
