package com.wjh.mysql.jdbc_template;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * <p>
 * 启动类
 * </p>
 *
 * @package: com.xkcoding.orm.jdbctemplate
 * @description: 启动类
 * @author: yangkai.shen
 * @date: Created in 2018/10/15 9:50 AM
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@SpringBootApplication(scanBasePackages = "com.wjh.mysql.jdbc_template")
public class SpringBootDemoOrmJdbctemplateApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(SpringBootDemoOrmJdbctemplateApplication.class)
		.properties("spring.config.location=classpath:/resources/mysql/jdbc_template/yml.yml").run(args);
	}
}
