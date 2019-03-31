package com.wjh.mysql.mybatis_plus_xml;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.wjh.mysql.mybatis_plus_xml"})
@EnableTransactionManagement 
@MapperScan("com.wjh.mysql.mybatis_plus_xml.dao")
public class MMXApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(MMXApplication.class)
		.properties("spring.config.location=classpath:/resources/mysql/mybatis_plus_xml/yml.yml").run(args);
	}
}
