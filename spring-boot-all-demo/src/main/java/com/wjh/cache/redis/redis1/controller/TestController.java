package com.wjh.cache.redis.redis1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wjh.cache.redis.redis1.service.RedisService;
import com.wjh.common.bean.R;

@RestController
public class TestController {
	
	@Autowired
	RedisService redisService;
	
	@RequestMapping("test")
	public R test(String value) {
		if (value != null) {
			String string = redisService.put("test");
			return R.ok(string);
		}else {
			String string = redisService.get("test");
			return R.ok(string);
		}
		
	}

}
