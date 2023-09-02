package com.xiaoxiong.algorithm.leetcode.thread.pcmodel.case1;

import lombok.SneakyThrows;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

/**
 * @author 小熊不会写代码
 * @date 2023/9/2
 */
public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        Semaphore producer = new Semaphore(1);
        Semaphore consumer = new Semaphore(0);
        BlockingQueue<List<String>> queue = new ArrayBlockingQueue<>(1);
        new Thread(new Consumer(producer,consumer,queue)).start();
        new Thread(new Producer(producer,consumer,queue)).start();
        System.in.read();
    }

}
