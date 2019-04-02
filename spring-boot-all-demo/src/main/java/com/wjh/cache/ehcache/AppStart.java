package com.wjh.cache.ehcache;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(scanBasePackages = "com.wjh.cache.ehcache")
public class AppStart {
	public static void main(String args[]) {
		  new SpringApplicationBuilder(AppStart.class)
			.properties("spring.config.location=classpath:/resources/cache/ehcache/yml.yml").run(args);
	  }
}
