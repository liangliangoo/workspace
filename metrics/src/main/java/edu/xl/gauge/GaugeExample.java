package edu.xl.gauge;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/6/12 10:45
 */
public class GaugeExample {

    private static final MetricRegistry metricRegistry = new MetricRegistry();

    private static final ConsoleReporter consoleReporter = ConsoleReporter.forRegistry(metricRegistry)
            .convertDurationsTo(TimeUnit.SECONDS)
            .convertRatesTo(TimeUnit.SECONDS)
            .build();

    private static final BlockingDeque<Long> queue = new LinkedBlockingDeque<>(1000);
    public static void main(String[] args) {
        metricRegistry.register("gauge", new Gauge<Integer>() {
            @Override
            public Integer getValue() {
                return queue.size();
            }
        });

        consoleReporter.start(3,TimeUnit.SECONDS);

        new Thread(()->{
            for (; ; ) {
                randomSleep();
                queue.add(System.nanoTime());
            }
        }).start();

        new Thread(()->{
            for (; ; ) {
                randomSleep();
                queue.poll();
            }
        }).start();

    }

    public static void randomSleep() {
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
