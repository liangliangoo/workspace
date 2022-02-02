package com.xiaoxiong.utils;

import org.apache.kafka.clients.admin.AdminClient;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/30  14:10
 */
public class KafkaUtilsTest {

    public static void main(String[] args) {
        AdminClient client = KafkaUtils.getAdminClient();
        client.close();
    }



}
