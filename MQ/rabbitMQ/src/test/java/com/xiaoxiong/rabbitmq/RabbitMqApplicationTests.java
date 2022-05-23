package com.xiaoxiong.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@SpringBootTest
class RabbitMqApplicationTests {

    @Test
    void contextLoads() {
    }


    @Test
    public void test_connectRabbitMq() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        final String QUEUE_NAME = "hello";
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setHost("192.168.44.10");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello World!";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        channel.close();
        connection.close();
    }

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test_rabbitTemplate() {
        rabbitTemplate.convertAndSend("mq-exchange-test",
                "",
                "hello rabbit mq");
    }

}
