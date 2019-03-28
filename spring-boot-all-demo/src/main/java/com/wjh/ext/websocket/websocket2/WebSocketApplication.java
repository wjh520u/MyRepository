package com.wjh.ext.websocket.websocket2;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@EnableWebSocket
@SpringBootApplication(scanBasePackages = "com.wjh.ext.websocket.websocket2")
public class WebSocketApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(WebSocketApplication.class)
		.properties("spring.config.location=classpath:/resources/ext/websocket/websocket2/yml.yml").run(args);
	}

	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}
}
