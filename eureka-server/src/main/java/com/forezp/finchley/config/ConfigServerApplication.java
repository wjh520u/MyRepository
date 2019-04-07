package com.forezp.finchley.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.forezp.finchley.eureka.EurekaServerApplication;

@SpringBootApplication(scanBasePackages = "com.forezp.finchley.config")
@EnableConfigServer
@Configuration
@EnableEurekaServer
public class ConfigServerApplication {

	@Value("${spring.cloud.config.server.git.uri}")
	private String uri;
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(ConfigServerApplication.class) 
		.properties("spring.config.location=classpath:/resources/finchley/config/application.yml").run(args);
		
	}
	
	@Bean
	public Object name() {
		System.err.println(uri);
		return new Object();
	}
	
}