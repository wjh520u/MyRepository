package com.forezp.finchley.client.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.forezp.finchley.client.exclude_config.feign.FeignConfiguration;
import com.forezp.finchley.common.entity.User;

@FeignClient(value = "service-hi"/*,configuration = FeignConfiguration.class*/)
public interface SchedualServiceHi {
    @RequestMapping(value = "/hi",method = RequestMethod.POST)
    public User sayHiFromClientOne(@RequestBody User user,@RequestParam(value = "name") String name);
}