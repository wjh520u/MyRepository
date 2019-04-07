package com.wjh.ext.queue.rabbitmq.rabbitMQ.listenerDemo;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 
 * @RabbitListener可以标注在类上面，当使用在类上面的时候，需要配合@RabbitHandler注解一起使用，
 * @RabbitListener标注在类上面表示当有收到消息的时候，就交给带有@RabbitHandler的方法处理，
 * 具体找哪个方法处理，需要跟进MessageConverter转换后的java对象。
 * @author Administrator
 */

@Component
@RabbitListener(queues ="zhihao.miao.order")
public class LisnerHandler {
 
 
    @RabbitHandler
    public void handleMessage(byte[] message){
        System.out.println("====消费消息handleMessage");
        System.out.println(new String(message));
    }
 
    @RabbitHandler
    public void handleMessage2(String message){
        System.out.println("====消费消息===handleMessage2");
        System.out.println(message);
    }
