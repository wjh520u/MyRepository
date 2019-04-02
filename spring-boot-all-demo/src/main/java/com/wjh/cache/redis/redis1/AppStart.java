package com.wjh.cache.redis.redis1;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@SpringBootApplication(scanBasePackages = "com.wjh.cache.redis.redis1")
public class AppStart {
	public static void main(String args[]) {
		  new SpringApplicationBuilder(AppStart.class)
			.properties("spring.config.location=classpath:/resources/cache/redis/redis1/yml.yml").run(args);
	  }
	// 加载YML格式自定义配置文件
		@Bean
		public static PropertySourcesPlaceholderConfigurer properties() {
			PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
			YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
			yaml.setResources(new PathMatchingResourcePatternResolver().getResource("classpath:resources/cache/redis/redis1/config/redis.yml"));//File引入
//			yaml.setResources(new ClassPathResource("youryml.yml"));//class引入
			configurer.setProperties(yaml.getObject());
			return configurer;
		}
}
