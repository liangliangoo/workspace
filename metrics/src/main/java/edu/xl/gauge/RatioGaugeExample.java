package edu.xl.gauge;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.RatioGauge;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/6/12 14:32
 */
public class RatioGaugeExample {

    private final static MetricRegistry registry = new MetricRegistry();

    private final static ConsoleReporter reporter = ConsoleReporter
            .forRegistry(registry)
            .convertDurationsTo(TimeUnit.SECONDS)
            .convertRatesTo(TimeUnit.SECONDS)
            .build();

    private final static Meter totalMeter = new Meter();
    private final static Meter successMeter = new Meter();


    public static void main(String[] args) {
        registry.gauge("success-rate", () -> new RatioGauge() {
            @Override
            protected Ratio getRatio() {
                return Ratio.of(successMeter.getCount(), totalMeter.getCount());
            }
        });
        reporter.start(3, TimeUnit.SECONDS);

        while (true) {
            business();
        }

    }

    private static void business() {
        totalMeter.mark();
        try {
            int a = 1 / ThreadLocalRandom.current().nextInt(4);
            successMeter.mark();
            randomSleep();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void randomSleep() {
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

