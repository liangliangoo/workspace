package edu.xl.gauge;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.JmxAttributeGauge;
import com.codahale.metrics.MetricRegistry;

import javax.management.ObjectName;
import java.util.concurrent.TimeUnit;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/6/12 11:12
 */
public class JmxAttributeGaugeExample {

    private static final MetricRegistry registry = new MetricRegistry();

    private static final ConsoleReporter consoleReporter = ConsoleReporter.forRegistry(registry)
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.SECONDS)
            .build();

    public static void main(String[] args) throws Exception {
        registry.register(MetricRegistry.name(JmxAttributeGaugeExample.class,"HeapMemoryUsage"),
                new JmxAttributeGauge(new ObjectName("java.lang:type=Memory"),
                        "HeapMemoryUsage"));
        consoleReporter.start(1,TimeUnit.SECONDS);
        Thread.currentThread().join();
    }

}
