package com.wjh.cache.redis.redis1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.wjh.cache.redis.redis1.entity.Test;
import com.wjh.cache.redis.redis1.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;  

	@Override
	public Object get(String string) {
		Object object = redisTemplate.opsForValue().get("test");
		return object;
	}

	@Override
	public Object put(String string) {
		redisTemplate.opsForValue().set("test", new Test("wjh",23));
		Object object = redisTemplate.opsForValue().get("test");
		return  object;
	}

}
