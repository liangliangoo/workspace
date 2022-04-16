package com.xiaoxiong.consumer;

import com.google.common.collect.ImmutableSet;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Iterator;

import static com.xiaoxiong.config.KafkaConfig.*;

/**
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2022/4/16  18:49
 */
public class Consumer1 {

    public static void main(String[] args) {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(getKafkaClientProperties());
        consumer.subscribe(ImmutableSet.of(TOPIC_IN));
        for (; ; ) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(5));
            Iterator<ConsumerRecord<String, String>> iterator = records.iterator();
            while (iterator.hasNext()) {
                ConsumerRecord<String, String> record = iterator.next();
                System.out.println(record.toString());
            }
        }
    }

}
