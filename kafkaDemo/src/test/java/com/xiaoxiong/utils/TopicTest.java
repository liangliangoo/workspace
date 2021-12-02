package com.xiaoxiong.utils;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/30  14:26
 */
public class TopicTest {

    @Test
    public void create_topic_test() {
        AdminClient client = KafkaUtils.getAdminClient();
        NewTopic topic = new NewTopic("kafka_demo", 2, (short) 2);
        CreateTopicsResult result = client.createTopics(Arrays.asList(topic));
        client.close();
    }

    @Test
    public void insertMsg_test() {
        KafkaProducer<String, String> producer = new KafkaProducer<>(KafkaUtils.getKafkaSetting());
        //异步发送20条消息
        for (int i = 1; i <= 20; i++){
            ProducerRecord<String, String> record = new ProducerRecord<>("test", "key" + i, "message" + i);
            producer.send(record);
        }

        producer.close();
    }

    @Test
    public void consume_test() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.44.128:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group-1");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000"); //会话响应时间超过了这个时间，kafka可以选择放弃消费或者消费下一条消息
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);//自动提交offset
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000"); //自动提交偏移量周期
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        //消费test1主题
        consumer.subscribe(Arrays.asList("test"));
        while (true){
            System.out.println("consumer is polling");
            //5秒等待
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(5000));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(String.format("offset=%d，key=%s，value=%s",
                        record.offset(), record.key(), record.value()));
            }
            //同步提交，失败会重试
            consumer.commitSync();
            //异步提交，失败不会重试
            //consumer.commitAsync();
        }
    }
}
