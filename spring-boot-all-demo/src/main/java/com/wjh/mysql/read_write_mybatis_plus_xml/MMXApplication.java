package com.wjh.mysql.read_write_mybatis_plus_xml;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.wjh.mysql.read_write_mybatis_plus_xml"})
@EnableTransactionManagement 
public class MMXApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(MMXApplication.class)
		.properties("spring.config.location=classpath:/resources/mysql/read_write_mybatis_plus_xml/yml.yml").run(args);
	}
}
