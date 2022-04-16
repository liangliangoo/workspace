package com.xiaoxiong.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.concurrent.TimeUnit;

import static com.xiaoxiong.config.KafkaConfig.TOPIC_IN;
import static com.xiaoxiong.config.KafkaConfig.getKafkaServerProperties;

/**
 * @author xiongliang
 * @version 1.0
 * @description 简单案例中的生产者
 * @since 2022/4/16  18:33
 */
public class SimpleProducer {

    public static void main(String[] args) {
        KafkaProducer<String, String> producer = new KafkaProducer<>(getKafkaServerProperties());
        try {
            for (int i = 0; i < 100; i++) {
                TimeUnit.SECONDS.sleep(1);
                ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_IN, String.valueOf(i), "message of " + i);
                producer.send(record);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }

}
