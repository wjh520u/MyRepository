package com.wjh.ext.mail;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(scanBasePackages= "com.wjh.ext.mail ")
public class MailApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(MailApplication.class)
		.properties("spring.config.location=classpath:/resources/ext/mail/yml.yml").run(args);
	}
}
