package com.xiaoxiong.stream.window;

import com.xiaoxiong.config.KafkaConfig;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.apache.kafka.streams.kstream.Windowed;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

/**
 * @author xiongliang
 * @version 1.0
 * @description 每隔2秒钟输出一次过去5秒内topicA里的wordcount，结果写入到TopicB
 * @since 2022/4/16  20:48
 */
public class WindowStream {

    public static void main(String[] args) {


        StreamsBuilder builder = new StreamsBuilder();
        KStream<Object, Object> source = builder.stream("topicA");
        KTable<Windowed<String>, Long> countKtable = source.flatMapValues(value -> Arrays.asList(value.toString().split("\\s+")))
                .map((x, y) -> new KeyValue<>(y, "1")).groupByKey()
                //加5秒窗口,按步长2秒滑动  Hopping Time Window
                .windowedBy(TimeWindows.ofSizeWithNoGrace(Duration.ofSeconds(5)).advanceBy(Duration.ofSeconds(2)))
                //.windowedBy(SessionWindows.with(Duration.ofSeconds(15).toMillis()))
                .count();

        //为了方便查看，输出到控制台
        countKtable.toStream().foreach((x, y) ->
                System.out.println("x: " + x + "  y: " + y)
        );

        countKtable.toStream().map((x, y) -> new KeyValue<>(x.toString(), y.toString())).to("topicB");

        final Topology topology = builder.build();
        final KafkaStreams streams = new KafkaStreams(topology, KafkaConfig.getStreamProperties());

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
