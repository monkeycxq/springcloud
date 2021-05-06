package com.example.webcustomer.controller;

import com.example.common.domain.UserParam;
import com.example.webcustomer.service.HelloService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author cxq
 * @date 2021/4/25
 * 消费 rabitmq 消息监听器
 */
@Component  // 需要注入到Spring容器中
@RabbitListener(queues = "wk0")   // 指定监听的队列名
public class RabbitmqConsumer {

    @Autowired
    HelloService helloService;

    @RabbitHandler  // 消息接收处理
    public void showMSG(String message){    // 得到我们producer中发送的Object数据，此处可根据传过来的类型来选择接收，否则抛出异常
        System.out.println("wk0收到的消息内容为：" + message);
    }

    @RabbitHandler  // 消息接收处理
    public void getAddUser(UserParam userParam){    // 得到我们producer中发送的Object数据，此处可根据传过来的类型来选择接收，否则抛出异常
        helloService.addUser(userParam);
    }

}
