package com.wjh.ext.mail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wjh.common.bean.R;
import com.wjh.ext.mail.service.MailService;

@RestController
public class TestController {
	
	@Autowired
	MailService mailService;
	

    @Value("${mail.fromMail.addr}")
    private String from;
	
	@RequestMapping("test")
	public R test() {
		mailService.sendSimpleMail(from,"357243610@qq.com", "王俊辉2，帅哥！", "你好帅2！");
		return R.ok();
	}

}
