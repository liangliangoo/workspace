package com.xiaoxiong.utils;

import lombok.Getter;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/30  13:56
 */
public class KafkaUtils {

    @Getter
    private static final Properties kafkaSetting = new Properties();

    static {
        kafkaSetting.put("bootstrap.servers", "192.168.44.128:9092");

        /**
         *  这里设置的是kafka的应答机制
         *  0 ：表示发送请求后，无论broker是否落盘，直接发送ack 。 会导致数据丢失
         *  1 ：表示等leader落盘之后，发送ack, 如果follower同步之前leader发生故障也会导致数据丢失
         *  -1：表示 leader 和 follower 全部落盘成功才会发送ack,但是在落盘之前leader挂了，重新选举，会导致数据重复
         */
        kafkaSetting.put("acks", "all");
        // kafkaSetting.put("retries", 0);
        kafkaSetting.put("batch.size", 16384);
        // kafkaSetting.put("linger.ms", 1);
        kafkaSetting.put("key.serializer", StringSerializer.class.getName());
        kafkaSetting.put("value.serializer", StringSerializer.class.getName());
    }

    public static AdminClient getAdminClient() {
        return KafkaAdminClient.create(kafkaSetting);
    }

}
