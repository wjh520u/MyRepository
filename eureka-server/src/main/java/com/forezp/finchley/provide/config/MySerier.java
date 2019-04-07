package com.forezp.finchley.provide.config;

import java.util.List;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class MySerier  implements WebMvcConfigurer {
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.clear();
		converters.add(new MyMessageConverters());
	}
	
	//@Bean
	public HttpMessageConverters name() {
		 HttpMessageConverter<?> converter = new MyMessageConverters();
		return new HttpMessageConverters(converter);
	}
}
