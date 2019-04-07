package com.wjh.ext.queue.rabbitmq.rabbitMQ.listener;


import org.springframework.amqp.rabbit.annotation.RabbitListener;

import com.wjh.ext.queue.rabbitmq.po.Mail;

public class QueueListener2 {
	
	@RabbitListener(queues = "myqueue")
	public void displayMail(Mail mail) throws Exception {
		System.out.println("队列监听器2号收到消息"+mail.toString());
	}
}
