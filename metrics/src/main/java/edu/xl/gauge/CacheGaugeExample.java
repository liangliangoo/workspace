package edu.xl.gauge;

import com.codahale.metrics.CachedGauge;
import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;

import java.util.concurrent.TimeUnit;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/6/12 14:54
 */
public class CacheGaugeExample {

    private static final MetricRegistry register = new MetricRegistry();
    private static final ConsoleReporter consoleReporter = ConsoleReporter.forRegistry(register)
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.SECONDS)
            .build();

    public static void main(String[] args) {

        register.gauge("cached-db-size",()-> new CachedGauge<Long>(2,TimeUnit.SECONDS) {

            @Override
            protected Long loadValue() {
                return null;
            }
        });
        consoleReporter.start(3,TimeUnit.SECONDS);

    }
}
