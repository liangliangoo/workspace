package com.xiaoxiong.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 小熊不会写代码
 * @date 2023/9/29
 */
public class ThreadPoolDemo {

    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 10, 100L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        threadPoolExecutor.submit(() -> System.out.println("hello"));
        System.out.println("COUNT_BITS " + COUNT_BITS);
        System.out.println("CAPACITY " + CAPACITY);
        System.out.println("RUNNING " + RUNNING);
        System.out.println("SHUTDOWN " + SHUTDOWN);
        System.out.println("TIDYING " + TIDYING);
        System.out.println("TERMINATED " + TERMINATED);
    }

}
