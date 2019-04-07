package com.forezp.finchley.client.feign.config;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;

public class MyFeignDecoder extends SpringDecoder {

	public MyFeignDecoder(ObjectFactory<HttpMessageConverters> messageConverters) {
		super(messageConverters);
	}

}
