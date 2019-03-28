package com.wjh.ext.CommandLineRunner;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(scanBasePackages= "com.wjh.ext.CommandLineRunner")
public class start {
	
	public static void main(String[] args) throws Exception {
		System.out.println("The service to start.");
		new SpringApplicationBuilder(start.class)
			.properties("spring.config.location=classpath:/resources/ext/CommandLineRunner/yml.yml").run(args);
		System.out.println("The service has started.");
	}
	
}
