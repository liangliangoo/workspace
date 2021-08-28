package com.xiaoxiong.jeager.demo;

import io.jaegertracing.Configuration;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/8/28 21:45
 */
public class JeagerDemo1 {

    public static void main(String[] args) throws Exception {
        Configuration config = new Configuration("Hello Jaeger1");

        Configuration.SenderConfiguration sender = new Configuration.SenderConfiguration();
        sender.withAgentHost("119.3.140.38");
        sender.withAgentPort(5775);
        config.withReporter(new Configuration.ReporterConfiguration().withSender(sender)
                .withFlushInterval(100)
                .withLogSpans(false));

        config.withSampler(new Configuration.SamplerConfiguration().withType("const").withParam(1));

        Tracer tracer = config.getTracer();
        System.out.println(tracer.toString());
        GlobalTracer.register(tracer);

        Tracer.SpanBuilder spanBuilder = GlobalTracer.get().buildSpan("hello");
        Span parent = spanBuilder.start();
        parent.log(100, "haha");


        Tracer.SpanBuilder spanBuilder2 = GlobalTracer.get().buildSpan("world").asChildOf(parent);
        Span child = spanBuilder2.start();
        child.log("haha2");
        child.finish();

        parent.finish();

        Thread.sleep(30000);
    }

}
