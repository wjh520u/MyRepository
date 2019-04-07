package com.wjh.ext.queue.rabbitmq.service;

import com.wjh.ext.queue.rabbitmq.po.Mail;

public interface Producer {
	public void sendMail(String queue,Mail mail);//向队列queue发送消息
}
