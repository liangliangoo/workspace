package com.xiaoxiong.counter;

import io.micrometer.core.instrument.FunctionCounter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2022/4/27  10:32
 */
public class FunctionCounterDemo {

    public static void main(String[] args) throws Exception {

        // SimpleMeterRegistry 会将数据放入JVM内存中
        MeterRegistry registry = new SimpleMeterRegistry();

        AtomicInteger n = new AtomicInteger(0);
        /**
         * 使用的一个明显的好处是，我们不需要感知FunctionCounter实例的存在，
         * 实际上我们只需要操作作为FunctionCounter实例构建元素之一的AtomicInteger实例即可，这种接口的设计方式在很多主流框架里面可以看到。
         */
        //这里ToDoubleFunction匿名实现其实可以使用Lambda表达式简化为AtomicInteger::get
        FunctionCounter.builder("functionCounter", n, value -> value.get()).baseUnit("function")
                .description("functionCounter")
                .tag("createOrder", "CHANNEL-A")
                .register(registry);
        //下面模拟三次计数
        n.incrementAndGet();
        n.incrementAndGet();
        n.incrementAndGet();
    }

}
