package com.xiaoxiong.thread.communication;

import com.google.common.collect.Sets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 生产者消费者模式
 * @author 小熊不会写代码
 * @date 2023/8/8
 */
public class PCMain {

    public static void main(String[] args) throws IOException {
        List<String> dataList = new ArrayList<>();
        new Thread(new Consumer(dataList)).run();
        new Thread(new Producer(dataList)).run();
        System.in.read();
    }

}

//评测题目:
//有一个场景源源不断产生需要被处理的数据，
//假设数据简单存储成行，假设数据读取的速度和数据处理的速度都比较慢，
//限定当前已提供了数据批量读取的接口和单条数据处理的接口，
//请使用生产者消费者模式编码实现数据处理的功能，需考虑代码健壮性和处理性能。

//测试时间30分钟

/**
 * 数据提取接口，假设类实现已完成，此处不需要关注，以接口形式使用即可
 *
 */
interface DataLoader<T>  {

    List<T> loadData();
}


/**
 *单条数据的处理接口，假设类实现已完成，此处不需要关注，以接口形式使用即可
 *
 */
interface DataProcessor<T> {

    boolean process(T data);

}


class Consumer implements DataProcessor<String>,Runnable{

    private List<String> dataList;

    public Consumer(List<String> dataList) {
        this.dataList = dataList;
    }

    @Override
    public boolean process(String data) {
        System.out.println("消费: " + data);
        // 业务处理
        return false;
    }


    @Override
    public void run() {
        synchronized (dataList) {
            if (dataList.size() == 0) {
                dataList.notifyAll();
            }
            dataList.forEach(item -> process(dataList.remove(0)));
            try {
                dataList.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}

class Producer implements DataLoader<String>, Runnable {

    private List<String> dataList;
    public static final Integer max_size = 2;

    public Producer(List<String> dataList) {
        this.dataList = dataList;
    }

    @Override
    public List<String> loadData() {
        System.out.println("加载消息");
        dataList.addAll(Sets.newHashSet("1", "2"));
        return this.dataList;
    }

    @Override
    public void run() {
        synchronized (dataList) {
            while (dataList.size() == max_size) {
                try {
                    dataList.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            loadData();
            try {
                dataList.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

