package com.wjh.ext.logback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.wjh.ext.logback.service.MyService;

@SpringBootApplication(scanBasePackages= "com.wjh.ext.logback ")
public class Application implements CommandLineRunner {

  @Autowired private MyService myService;

  public static void main(String args[]) {
	  new SpringApplicationBuilder(Application.class)
		.properties("spring.config.location=classpath:/resources/ext/logback/application.properties").run(args);
  }

  @Override
  public void run(final String args[]) {
  	myService.doStuff("value");
	}
}
