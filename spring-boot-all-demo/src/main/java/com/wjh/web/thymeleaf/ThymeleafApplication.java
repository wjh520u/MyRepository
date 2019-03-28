package com.wjh.web.thymeleaf;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(scanBasePackages= "com.wjh.web.thymeleaf")
public class ThymeleafApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ThymeleafApplication.class)
		.properties("spring.config.location=classpath:/resources/web/thymeleaf/yml.yml").run(args);
	}

}
