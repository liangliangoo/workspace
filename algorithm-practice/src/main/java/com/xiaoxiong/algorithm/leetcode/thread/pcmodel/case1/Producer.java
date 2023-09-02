package com.xiaoxiong.algorithm.leetcode.thread.pcmodel.case1;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

/**
 * @author 小熊不会写代码
 * @date 2023/9/2
 */
public class Producer implements DataLoader<String>, Runnable {

    private Semaphore producerSemaphore;
    private Semaphore consumerSemaphore;
    private BlockingQueue<List<String>> queue;

    public Producer(Semaphore producerSemaphore, Semaphore consumerSemaphore, BlockingQueue<List<String>> queue) {
        this.producerSemaphore = producerSemaphore;
        this.consumerSemaphore = consumerSemaphore;
        this.queue = queue;
    }

    @Override
    public List<String> loadData() {
        return Lists.newArrayList("1", "2", "3");
    }

    @Override
    public void run() {
        for (; ; ) {
            try {
                producerSemaphore.acquire();
                queue.put(loadData());
                consumerSemaphore.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
