package com.forezp.finchley.client.ribbon;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

import com.forezp.finchley.client.ribbon.config.MySelfRule;

@SpringBootApplication(scanBasePackages = "com.forezp.finchley.client.ribbon")
@EnableEurekaClient
@EnableDiscoveryClient
@EnableHystrix
@RibbonClient(name = "service-hi", configuration = MySelfRule.class)
public class ServiceRibbonApplication {

    public static void main(String[] args) {
    	new SpringApplicationBuilder(ServiceRibbonApplication.class) 
		.properties("spring.config.location=classpath:/resources/finchley/client/ribbon/application.yml").run(args);
    }
    

}