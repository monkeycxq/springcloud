package com.example.userservice;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @authoe cxq
 * @date 2021/4/25
 */
@SpringBootTest
public class RabbitmqTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;  // 注入一个RabbitMQ的模板对象，操作消息队列的对象

    // 发送一条点对点（Direct）的消息，又称为直连
    @Test
    public void sendQueue(){
        System.out.println("开始向队列中发送一条消息！");
        // 参数1：管理中的队列名  参数2：发送的消息
        rabbitTemplate.convertAndSend("wk0","message:这是一条消息！");
        System.out.println("消息发送完毕！");
    }

}
