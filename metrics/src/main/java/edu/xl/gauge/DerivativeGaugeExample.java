package edu.xl.gauge;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.DerivativeGauge;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/6/12 15:08
 */
public class DerivativeGaugeExample {

    private static final LoadingCache<String,String> cache = CacheBuilder
            .newBuilder().maximumSize(10)
            .expireAfterAccess(5, TimeUnit.SECONDS)
            .recordStats()
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    return key.toUpperCase();
                }
            });

    private static final MetricRegistry registry = new MetricRegistry();
    private static final ConsoleReporter reporter = ConsoleReporter
            .forRegistry(registry)
            .convertDurationsTo(TimeUnit.SECONDS)
            .convertRatesTo(TimeUnit.SECONDS)
            .build();

    public static void main(String[] args) {

        Gauge<CacheStats> cacheGauge = registry.gauge("DerivativeGauge", () -> cache::stats);
        registry.register("missCount", new DerivativeGauge<CacheStats,Long>(cacheGauge) {
            @Override
            protected Long transform(CacheStats cacheStats) {
                return cacheStats.missCount();
            }
        });
        reporter.start(2,TimeUnit.SECONDS);
        while (true) {
            business();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void business() {
        cache.getUnchecked("xiaoxiong");
    }
}
