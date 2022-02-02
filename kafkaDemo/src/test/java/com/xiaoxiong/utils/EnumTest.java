package com.xiaoxiong.utils;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Properties;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/12/2  10:08
 */
public class EnumTest {

    public static void main(String[] args) {
        Properties prop = KSet.CONSUMER.prop;
        System.out.println(prop);
    }
}

enum KSet {

    CONSUMER("consumer"),
    PROVIDER("provider");
    String name;

    KSet(String name) {
        this.name = name;
        initProperties(name);
    }

    public Properties prop = new Properties();

    // 这里设置一些属性
    private void initProperties(String s) {
        switch (s) {
            case "CONSUMER":
                prop.put(ConsumerConfig.GROUP_ID_CONFIG,"G1");
                break;
            case "PRIVATER":
                prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.44.128:9092");
                break;
            default:
                throw new IllegalArgumentException("Unknown type:" + this.getClass().getName());
        }

    }

}
