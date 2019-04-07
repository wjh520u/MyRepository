package com.forezp.finchley.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import com.forezp.finchley.provide.ServiceHiApplication;

@SpringBootApplication(scanBasePackages = "com.forezp.finchley.zuul")
@EnableZuulProxy
@EnableEurekaClient
@EnableDiscoveryClient
public class ServiceZuulApplication {

    public static void main(String[] args) {
    	new SpringApplicationBuilder(ServiceZuulApplication.class) 
		.properties("spring.config.location=classpath:/resources/finchley/zuul/application.yml").run(args);
    }
}