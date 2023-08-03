package com.xiaoxiong.concurrent;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author 六月
 * @Date 2023/8/2 12:10
 * @Version 1.0
 */
public class ThreadPoolTest {

    @Test
    public void theadPoolDealWithException() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(()-> {
            throw new NullPointerException();
        });
    }

    class MyRunnable implements Runnable {

        @Override
        public void run() {
            throw new NullPointerException();
        }
    }

    class MyCallable implements Callable {
        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public Object call() throws Exception {
            return null;
        }
    }


}
