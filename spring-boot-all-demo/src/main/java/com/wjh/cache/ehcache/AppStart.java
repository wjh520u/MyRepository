package com.wjh.cache.ehcache;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = "com.wjh.cache.ehcache")
@EnableCaching
public class AppStart {
	public static void main(String args[]) {
		  new SpringApplicationBuilder(AppStart.class)
			.properties("spring.config.location=classpath:/resources/cache/ehcache/yml.yml").run(args);
	  }
}
