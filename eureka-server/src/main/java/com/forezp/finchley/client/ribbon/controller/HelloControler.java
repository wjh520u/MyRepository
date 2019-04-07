package com.forezp.finchley.client.ribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.forezp.finchley.client.ribbon.service.HelloService;
import com.forezp.finchley.common.entity.User;

@RestController
public class HelloControler {

    @Autowired
    HelloService helloService;

    @GetMapping(value = "/hi")
    public User hi(@RequestParam String name) {
        return helloService.hiService( name );
    }
    
    @Value("${foo}")
	String foo;
	@RequestMapping(value = "/hi2")
	public String hi(){
		return foo;
	}
}