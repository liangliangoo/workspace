package com.xiaoxiong.provider;

import com.xiaoxiong.utils.KafkaSetting;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/30  18:00
 */
public class Provider1 {

    public void provide(String topicName) {
        // kafkaSetting 配置问题倒是消息发送不出去，而KafkaUtils.getKafkaSetting()确实正常的
        KafkaProducer<String, String> producer = new KafkaProducer<>(KafkaSetting.PRIVATER);
        //生成10条数据
        for (int i = 0; i <10; i++) {
            //创建消息对象
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topicName, "hello world" + i);
            //调用生产者消息发送方法
            producer.send(producerRecord);
        }
        producer.close();
    }
}
