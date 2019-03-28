
package com.wjh.ext.swagger;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication(scanBasePackages= "com.wjh.ext.swagger")
public class SwaggerApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(SwaggerApplication.class)
		.properties("spring.config.location=classpath:/resources/ext/swagger/yml.yml").run(args);
	}

}
