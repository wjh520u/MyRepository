package com.forezp.finchley.provide.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.forezp.finchley.common.entity.User;
@RestController
public class TestController {

	 	@Value("${server.port}")
		String port;
		
		@RequestMapping(value = "/hi",method = RequestMethod.POST)
		public User home(@RequestBody User user,@RequestParam(value = "name", defaultValue = "forezp") String name) {
			System.out.println(user);
			User user2 = new User();
			user2.id=-1;
			user2.name="王俊辉"+port;
			return user2;
		}

}
