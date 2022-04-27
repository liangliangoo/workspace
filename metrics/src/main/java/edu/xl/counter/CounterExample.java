package edu.xl.counter;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/6/12 15:55
 */
public class CounterExample {

    private static final MetricRegistry register = new MetricRegistry();
    private static final ConsoleReporter reporter = ConsoleReporter
            .forRegistry(register)
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.SECONDS)
            .build();

    private static final BlockingDeque<Long> queue = new LinkedBlockingDeque<>(10);

    public static void main(String[] args) {

        Counter counter = register.counter("queue-counter", Counter::new);

        reporter.start(2, TimeUnit.SECONDS);
        new Thread(() -> {
            // queue.add(ThreadLocalRandom.current().nextLong(10));
            queue.offer(ThreadLocalRandom.current().nextLong(10));
            counter.inc();
        }).start();
        new Thread(() -> {
            queue.poll();
            counter.dec();
        }).start();
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
