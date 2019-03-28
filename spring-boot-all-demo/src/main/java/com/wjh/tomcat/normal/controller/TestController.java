package com.wjh.tomcat.normal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wjh.common.bean.R;

@RestController
public class TestController {
	
	@RequestMapping("test")
	public R test() {
		return R.ok();
	}

}
