package com.wjh.ext.queue.rabbitmq.rabbitMQ.listenerDemo;

import java.util.Map;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

import com.rabbitmq.client.Channel;

public class AnnotionDemo {
	
	//获取消息的头属性和body属性
    @RabbitListener(queues = "zhihao.miao.order")
    public void handleMessage(@Payload String body, @Headers Map<String,Object> headers){
        System.out.println("====消费消息===handleMessage");
        System.out.println(headers);
        System.out.println(body);
    }
	
    //支持自动声明绑定，声明之后自动监听队列的队列，此时@RabbitListener注解的queue和bindings不能同时指定，否则报错
    @RabbitListener(bindings ={@QueueBinding(value = @Queue(value = "q5",durable = "true"),
            exchange =@Exchange(value = "zhihao.miao.exchange",durable = "true"),key = "welcome")})
    public void handleMessage0(Message message){
        System.out.println("====消费消息"+message.getMessageProperties().getConsumerQueue()+"===handleMessage");
        System.out.println(message.getMessageProperties());
        System.out.println(new String(message.getBody()));
    }
    
    /**
    * @param message :解码后的消息
    * @param delicveryTag :使用@Header接口获取messageProperties中的DELIVERY_TAG属性。
    * @param channel : 接受消息所使用的信道
    */
    @RabbitListener(queues = "sys.topic.login.message")
        public void process1(@Payload String message, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel)throws Exception {
            if(message.equals("1")){
                int i = 1/0;
            }
            channel.basicAck(deliveryTag,false);
            System.out.print("这里是接收者1答应消息： ");
            System.out.println("SYS_TOPIC_ORDER_CALCULATE_ZZ_FEE process1  : " + message);
    }
    
	//指定向多个队列中发送消息
	@RabbitListener(queues ={"zhihao.miao.order","zhihao.info"})
    public void handleMessage(Message message){
        System.out.println("====消费消息"+message.getMessageProperties().getConsumerQueue()+"===handleMessage");
        System.out.println(message.getMessageProperties());
        System.out.println(new String(message.getBody()));
    }
	
	
	//通过配置文件发送消息
    @RabbitListener(queues ={"${zhihao.queue1}","${zhihao.queue2}"})
    public void handleMessage2(Message message){
        System.out.println("====消费消息"+message.getMessageProperties().getConsumerQueue()+"===handleMessage");
        System.out.println(message.getMessageProperties());
        System.out.println(new String(message.getBody()));
    }

}
