package com.forezp.finchley.client.feign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.forezp.finchley.client.feign.service.SchedualServiceHi;
import com.forezp.finchley.common.entity.User;

@RestController
public class HiController {


    //编译器报错，无视。 因为这个Bean是在程序启动的时候注入的，编译器感知不到，所以报错。
    @Autowired
    SchedualServiceHi schedualServiceHi;

    @GetMapping(value = "/hi")
    @ResponseBody
    public User sayHi(@RequestParam String name) {
    	User user = new User();
    	user.setId(1);
    	user.setName("王俊辉22");
    	return schedualServiceHi.sayHiFromClientOne( user,name );
    }
}