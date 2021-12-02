package com.xiaoxiong.consumer;

import com.xiaoxiong.utils.KafkaSetting;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/30  17:51
 */
public class Consumer1 {

    public void consume(String topicName){
        //创建kafka消费者对象
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(KafkaSetting.CONSUMER);
        //设置自动分配topic与消费者对象
        consumer.subscribe(Collections.singleton(topicName));

        while (true){
            //消费数据,一次10条
            ConsumerRecords<String, String> poll = consumer.poll(10);
            //遍历输出
            for (ConsumerRecord<String, String> record : poll) {
                System.out.println(record.offset() + "\t" + record.key() + "\t" + record.value());
            }
            consumer.commitSync();
        }
    }
}
