package com.xiaoxiong.utils;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/30  15:47
 */
public class KafkaSetting {

    public static final Properties CONSUMER = new Properties();
    public static final Properties PRIVATER = new Properties();

    static {
        {
            CONSUMER.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.44.128:9092");
            CONSUMER.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
            CONSUMER.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
            //会话响应的时间，超过这个时间kafka可以选择放弃消费或者消费下一条消息
            CONSUMER.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG,"30000");
            //false:非自动提交偏移量
            CONSUMER.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"false");
            //自动提交偏移量周期
            CONSUMER.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"1000");
            //earliest：拉取最早的数据
            //latest：拉取最新的数据
            //none：报错
            CONSUMER.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
            //一个消费者组G1里只有一个消费者
            CONSUMER.put(ConsumerConfig.GROUP_ID_CONFIG,"G1");
        }

        {
            //kafka连接地址
            PRIVATER.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.44.128:9092");
            //用于实现Serializer接口的密钥的串行器类。
            PRIVATER.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            PRIVATER.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            //生产者数据安全
            PRIVATER.put(ProducerConfig.ACKS_CONFIG,"all");
            PRIVATER.put(ProducerConfig.BATCH_SIZE_CONFIG, "16384");
        }
    }

}
