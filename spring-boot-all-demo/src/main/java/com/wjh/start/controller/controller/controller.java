package com.wjh.start.controller.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wjh.common.bean.R;

@RestController
public class controller {
	
	@RequestMapping(value = "test")
	public R tset() {
		return R.error();
	}

}
