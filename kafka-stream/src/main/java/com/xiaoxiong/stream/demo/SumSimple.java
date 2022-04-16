package com.xiaoxiong.stream.demo;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;

import java.util.concurrent.CountDownLatch;

import static com.xiaoxiong.config.KafkaConfig.getStreamProperties;

/**
 * @author xiongliang
 * @version 1.0
 * @description 求和案例
 * @since 2022/4/16  20:46
 */
public class SumSimple {

    public static void main(String[] args) {
        //创建流构造器
        StreamsBuilder builder = new StreamsBuilder();
        KStream<Object, Object> source = builder.stream("suminput");
        KTable<String, String> sum1 = source.map((key, value) ->
                new KeyValue<>("sum", value.toString())
        ).groupByKey().reduce((x, y) -> {
            System.out.println("x: " + x + "    " + "y: " + y);
            Integer sum = Integer.valueOf(x) + Integer.valueOf(y);
            System.out.println("sum: " + sum);
            return sum.toString();
        });
        sum1.toStream().to("sumout");

        Topology topology = builder.build();
        KafkaStreams streams = new KafkaStreams(topology, getStreamProperties());

        CountDownLatch latch = new CountDownLatch(1);
        Runtime.getRuntime().addShutdownHook(new Thread("kafka-stream") {
            @Override
            public void run() {
                streams.close();
                latch.countDown();
            }
        });
        try {
            streams.start();
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

}
