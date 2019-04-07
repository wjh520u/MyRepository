package com.forezp.finchley.client.ribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.forezp.finchley.common.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    //@HystrixCommand(fallbackMethod = "error")
    public User hiService(String name) {
    	//int a = 1/0;
    	User user = new User();
    	user.setName("wjh");
    	User postForObject = restTemplate.postForObject("http://SERVICE-HI/hi?name="+name,user,User.class);
        System.out.println(postForObject);
    	return postForObject;
    }

    public User error(String name) {
		return new User();
	}

}