package com.xiaoxiong.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.StreamsConfig;

import java.util.Properties;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;
import static org.apache.kafka.clients.producer.ProducerConfig.*;

/**
 * @author xiongliang
 * @version 1.0
 * @description kafka配置类
 * @since 2022/4/16  16:58
 */
public class KafkaConfig {

    public static final String TOPIC_IN                 = "topic-stream-in";
    public static final String TOPIC_OUT                = "topic-stream-out";
    public static final String TOPIC_WORD_COUNT_INPUT   = "topic-wordcount-input";
    public static final String TOPIC_WORD_COUNT_OUTPUT  = "topic-wordcount-output";


    public static Properties getKafkaServerProperties() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.44.20:9092");
        properties.put(ACKS_CONFIG, "all");
        // 开启幂等性这个参数必须配置
        properties.put(RETRIES_CONFIG, 3);
        properties.put(BATCH_SIZE_CONFIG, 16384);
        // 是否延迟提交
        properties.put(LINGER_MS_CONFIG, 5000);
        properties.put(BUFFER_MEMORY_CONFIG, 33554432);
        properties.put(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // 配置自定义分区策略
        // properties.put(PARTITIONER_CLASS_CONFIG, MyPartitioner.class.getName());
        return properties;
    }

    public static Properties getKafkaClientProperties() {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.44.20:9092");
        properties.put(GROUP_ID_CONFIG, "kafka-stream-G1");
        properties.put(ENABLE_AUTO_COMMIT_CONFIG, "true");
        properties.put(AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        properties.put(AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(SESSION_TIMEOUT_MS_CONFIG, "30000");
        properties.put(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        return properties;
    }

    public static Properties getStreamProperties() {
        Properties prop =new Properties();
        prop.put(StreamsConfig.APPLICATION_ID_CONFIG,"kafka-stream");
        prop.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.44.20:9092");
        prop.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG,3000);  // 提交时间设置为3秒
        prop.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass()); //输入key的类型
        prop.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,Serdes.String().getClass());  //输入value的类型
        return prop;
    }

}
