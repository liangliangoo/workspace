package com.xiaoxiong.algorithm.leetcode.thread.pcmodel.case1;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

/**
 * @author 小熊不会写代码
 * @date 2023/9/2
 */
public class Consumer implements DataProcessor<String>,Runnable {

    private Semaphore producerSemaphore;
    private Semaphore consumerSemaphore;
    private BlockingQueue<List<String>> queue;

    public Consumer(Semaphore producerSemaphore, Semaphore consumerSemaphore, BlockingQueue<List<String>> queue) {
        this.producerSemaphore = producerSemaphore;
        this.consumerSemaphore = consumerSemaphore;
        this.queue = queue;
    }

    @Override
    public boolean process(String data) {
        System.out.println(data);
        return true;
    }

    @Override
    public void run() {
        while (true) {
            try {
                consumerSemaphore.acquire();
                consumer(queue.take());
                producerSemaphore.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void consumer(List<String> data) {
        data.forEach(this::process);
    }
}
