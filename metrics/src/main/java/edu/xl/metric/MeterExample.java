package edu.xl.metric;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/6/12 10:21
 */
public class MeterExample {

    //注册表
    private final static MetricRegistry REGISTRY = new MetricRegistry();

    private final static Meter requestMeter = REGISTRY.meter("qps");

    private final static Meter sizeMeter = REGISTRY.meter("size");

    public static void main(String[] args) {
        ConsoleReporter.Builder builder = ConsoleReporter.forRegistry(REGISTRY);
        ConsoleReporter reporter = builder.convertRatesTo(TimeUnit.MINUTES)
                .convertRatesTo(TimeUnit.MINUTES).build();
        //每多少时长reporter报道一次
        reporter.start(10,TimeUnit.SECONDS);
        for (; ; ) {
            handleRequest(new byte[new Random(10).nextInt(10)]);
        }
    }

    private static void handleRequest(byte[] request) {
        requestMeter.mark();
        sizeMeter.mark(request.length);
        randomSleep();
    }

    private static void randomSleep() {
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
