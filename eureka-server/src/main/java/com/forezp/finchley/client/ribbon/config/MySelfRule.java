package com.forezp.finchley.client.ribbon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;

/**
 * 放开影响全局,局部放scan范围外
 * 自定义负载均衡算法
 */
@Configuration
public class MySelfRule {
     @Bean
    public IRule myRule() {
        //return new RoundRobinRule();//轮询算法
        //return new RandomRule();//随机算法
        return new MyRule();
    }
 
}