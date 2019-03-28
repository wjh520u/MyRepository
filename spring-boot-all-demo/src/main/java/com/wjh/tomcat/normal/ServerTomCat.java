package com.wjh.tomcat.normal;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(scanBasePackages= "com.wjh.tomcat.normal")
public class ServerTomCat {
	
	public static void main(String[] args) throws Exception {
		new SpringApplicationBuilder(ServerTomCat.class)
			.properties("spring.config.location=classpath:/resources/tomcat/normal.yml").run(args);
	}
	
}
