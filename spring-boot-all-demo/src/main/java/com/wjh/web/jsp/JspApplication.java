package com.wjh.web.jsp;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


/**
 * 建议使用
 * mvn clean spring-boot:run
 * 来启动项目
 */
@SpringBootApplication(scanBasePackages= "com.wjh.web.jsp")
public class JspApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(JspApplication.class)
		.properties("spring.config.location=classpath:/resources/web/jsp/yml.yml").run(args);
	}
}
