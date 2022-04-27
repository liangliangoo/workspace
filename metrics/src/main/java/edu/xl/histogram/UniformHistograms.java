package edu.xl.histogram;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.UniformReservoir;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/6/13 21:17
 */
public class UniformHistograms {

    private static final MetricRegistry register = new MetricRegistry();
    private static final ConsoleReporter consoleReporter = ConsoleReporter
            .forRegistry(register)
            .convertDurationsTo(TimeUnit.SECONDS)
            .convertRatesTo(TimeUnit.SECONDS)
            .build();

    private static final Histogram histogram = new Histogram(new UniformReservoir());

    public static void main(String[] args) {
        register.register("histogram", histogram);
        consoleReporter.start(10,TimeUnit.SECONDS);
        while (true) {
            doSearch();
        }
    }

    private static void doSearch() {
        histogram.update(ThreadLocalRandom.current().nextInt(10));
        randomSleep();
    }

    private static void randomSleep() {
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
