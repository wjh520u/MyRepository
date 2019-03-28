package com.wjh.mysql.mybatis_annotation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * spring boot启动类.
 * @author Angel -- 守护天使
 * @version v.0.1
 * @date 2017年8月16日
 */
@SpringBootApplication(scanBasePackages = {"com.wjh.mysql.mybatis_annotation"})
@MapperScan("com.wjh.mysql.mybatis_annotation.mapper") //需要指定包名。
//@MapperScan({"com.kfit.*.mapper","org.kfit.*.mapper"})
public class App {
	public static void main(String[] args) {
		new SpringApplicationBuilder(App.class)
		.properties("spring.config.location=classpath:/resources/mysql/mybatis_annotation/yml.yml").run(args);
	}
}
