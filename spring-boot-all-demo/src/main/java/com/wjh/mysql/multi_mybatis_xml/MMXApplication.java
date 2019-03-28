package com.wjh.mysql.multi_mybatis_xml;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(scanBasePackages = {"com.wjh.mysql.multi_mybatis_xml"})
public class MMXApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(MMXApplication.class)
		.properties("spring.config.location=classpath:/resources/mysql/multi_mybatis_xml/yml.yml").run(args);
	}
}
