package com.xiaoxiong.learning.java8.pool;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/15  21:49
 */
public class MyThreadPool {

    // 默认线程池中的数量
    private static final int WORK_NUM = 5;

    // 默认处理任务的数量
    private static final int TASK_NUM = 100;

    // 等待队列
    private final BlockingQueue<Runnable> taskQueue;

    private final Set<WorkThread> workThreads;

    private int workNumber;
    private int taskNumber;

    public MyThreadPool() {
        this.workThreads = null;
        taskQueue = null;
    }

    public MyThreadPool(int workNumber, int taskNumber) {
        if (taskNumber <= 0) {
            taskNumber = TASK_NUM;
        }

        if (workNumber <= 0) {
            workNumber = WORK_NUM;
        }
        this.workNumber = workNumber;
        this.taskNumber = taskNumber;
        this.taskQueue = new ArrayBlockingQueue<Runnable>(taskNumber);
        workThreads = new HashSet<>();

        for (int i = 0; i < workNumber; i++) {
            WorkThread workThread = new WorkThread("thread_" + i);
            workThread.start();
            workThreads.add(workThread);
        }
    }

    public void execute(Runnable task) {
        try {
            taskQueue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        for (WorkThread workThread : workThreads) {
            workThread.stopWorker();
            workThread = null; // help gc
        }
        workThreads.clear();
    }

    private class WorkThread extends Thread {

        public WorkThread(String name) {
            setName(name);
        }

        @Override
        public void run() {
            while (!interrupted()) {
                try {
                    Runnable task = taskQueue.take();
                    if (task != null) {
                        System.out.println(getName() + "开始执行" + task.toString());
                        task.run();
                    }
                    task = null; // help gc
                } catch (InterruptedException e) {
                    interrupt();
                    e.printStackTrace();
                }
            }
        }

        public void stopWorker() {
            interrupt();
        }
    }
}
