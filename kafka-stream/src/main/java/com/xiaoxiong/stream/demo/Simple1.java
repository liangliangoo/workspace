package com.xiaoxiong.stream.demo;

import com.xiaoxiong.config.KafkaConfig;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;

import java.util.concurrent.CountDownLatch;

import static com.xiaoxiong.config.KafkaConfig.TOPIC_IN;
import static com.xiaoxiong.config.KafkaConfig.TOPIC_OUT;

/**
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2022/4/16  18:27
 */
public class Simple1 {

    public static void main(String[] args) {

        //创建流构造器
        StreamsBuilder builder = new StreamsBuilder();

        //构建好builder，将myStreamIn topic中的数据写入到myStreamOut topic中
        builder.stream(TOPIC_IN).to(TOPIC_OUT);

        final Topology topology = builder.build();
        final KafkaStreams streams = new KafkaStreams(topology, KafkaConfig.getStreamProperties());

        final CountDownLatch latch = new CountDownLatch(1);
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
