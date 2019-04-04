package com.wjh.cache.ehcache.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.wjh.cache.ehcache.service.CacheService;

@Service
//@CacheConfig(cacheManager = "RedisCacheManager")
public class CacheServiceImpl implements CacheService {

	@Override
	@Cacheable(key="#key",cacheNames="data",cacheManager = "EhCacheCacheManager")
	public String getData(String key) {
		// TODO Auto-generated method stub
		System.err.println(key);
		return key;
	}

}
