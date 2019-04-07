package com.forezp.finchley.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.autoconfigure.ConfigurationPropertiesRebinderAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forezp.finchley.client.ribbon.ServiceRibbonApplication;

/**
 * 去掉pom.xml的  pring-cloud-config-server, 需要Pom.xml支持,请到对应目录.
 * 
 * @author Administrator
 *
 */
@SpringBootApplication(scanBasePackages = "com.forezp.finchley.client.config")
@RestController
@EnableEurekaClient
@EnableDiscoveryClient
@RefreshScope
public class ConfigClientApplication {

	public static void main(String[] args) throws Exception {
		new SpringApplicationBuilder(ConfigClientApplication.class) 
		.properties("spring.config.location=classpath:/resources/finchley/client/config_client/application.yml").run(args);
    }

	
	//@Bean
	 public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
	  PropertySourcesPlaceholderConfigurer c = new PropertySourcesPlaceholderConfigurer();
	  c.setIgnoreUnresolvablePlaceholders(true);
	  return c;
	 }

	@Value("${foo}")
	String foo;
	@RequestMapping(value = "/hi")
	public String hi(){
		return foo;
	}
}
