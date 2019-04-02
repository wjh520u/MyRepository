package com.wjh.cache.redis.redis1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.wjh.cache.redis.redis1.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;  

	@Override
	public String get(String string) {
		Object object = redisTemplate.opsForValue().get("test");
		return (String) object;
	}

	@Override
	public String put(String string) {
		redisTemplate.opsForValue().set("test", "test");
		Object object = redisTemplate.opsForValue().get("test");
		return (String) object;
	}

}
