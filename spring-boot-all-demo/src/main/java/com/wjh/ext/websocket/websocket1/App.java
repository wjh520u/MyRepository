package com.wjh.ext.websocket.websocket1;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(scanBasePackages = "com.wjh.ext.websocket.websocket1")
public class App 
{
    public static void main( String[] args )
    {
    	new SpringApplicationBuilder(App.class)
		.properties("spring.config.location=classpath:/resources/ext/websocket/websocket1/yml.yml").run(args);
    }
}
