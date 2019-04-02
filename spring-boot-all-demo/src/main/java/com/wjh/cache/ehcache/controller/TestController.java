package com.wjh.cache.ehcache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wjh.cache.ehcache.service.CacheService;
import com.wjh.common.bean.R;

@RestController
public class TestController {
	
	@Autowired
	CacheService cacheService;
	
	@RequestMapping("test")
	public R test(String value) {

		String fString = cacheService.getData(value);
		
		return R.ok_data(fString);
		
	}

}
