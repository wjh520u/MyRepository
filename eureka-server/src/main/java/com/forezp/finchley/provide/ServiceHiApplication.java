package com.forezp.finchley.provide;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = "com.forezp.finchley.provide")
@EnableEurekaClient
@RestController
@EnableAspectJAutoProxy
public class ServiceHiApplication {

    public static void main(String[] args) {
    	new SpringApplicationBuilder(ServiceHiApplication.class) 
		.properties("spring.config.location=classpath:/resources/finchley/provide/application.yml").run(args);
    }

   
}