package com.forezp.finchley.client.feign;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages = "com.forezp.finchley.client.feign")
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
@EnableWebMvc
public class ServiceFeignApplication {

    public static void main(String[] args) {
    	new SpringApplicationBuilder(ServiceFeignApplication.class) 
		.properties("spring.config.location=classpath:/resources/finchley/client/feign/application.yml").run(args);
    }
}