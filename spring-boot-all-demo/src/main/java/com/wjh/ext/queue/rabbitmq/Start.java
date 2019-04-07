package com.wjh.ext.queue.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.wjh.ext.swagger.SwaggerApplication;

@SpringBootApplication(scanBasePackages = "com.wjh.ext.queue.rabbitmq")
public class Start {
	
	public static void main(String[] args) throws Exception {
		new SpringApplicationBuilder(Start.class)
		.properties("spring.config.location=classpath:/resources/ext/queue/rabbitmq/yml.yml").run(args);
	}


}
