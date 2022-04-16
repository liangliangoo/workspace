package com.xiaoxiong.stream.wordcount;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KTable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static com.xiaoxiong.config.KafkaConfig.*;

/**
 * @author xiongliang
 * @version 1.0
 * @description word count
 * @since 2022/4/16  20:21
 */
public class WordCountStream {

    public static void main(String[] args) {
        //创建流构造器
        //hello world
        //hello java
        StreamsBuilder builder = new StreamsBuilder();
        KTable<String, Long> count = builder.stream(TOPIC_WORD_COUNT_INPUT) //从kafka中一条一条取数据
                .flatMapValues(                //返回压扁后的数据
                        (value) -> {           //对数据按空格进行切割，返回List集合
                            String[] split = value.toString().split(" ");
                            List<String> strings = Arrays.asList(split);
                            return strings;
                        })  //null hello,null world,null hello,null java
                .map((k, v) -> new KeyValue<>(v, "1"))
                .groupByKey()
                .count(); // 通过key累加
        count.toStream().foreach((k, v) -> {
            //为了测试方便，我们将kv输出到控制台
            System.out.println("key:" + k + "   " + "value:" + v);
        });

        count.toStream().map((x, y) -> {
            return new KeyValue<>(x, y.toString());  //注意转成toString类型，我们前面设置的kv的类型都是string类型
        }).to(TOPIC_WORD_COUNT_OUTPUT);

        final Topology topology = builder.build();
        final KafkaStreams streams = new KafkaStreams(topology, getStreamProperties());

        final CountDownLatch latch = new CountDownLatch(1);
        Runtime.getRuntime().addShutdownHook(new Thread("stream") {
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
