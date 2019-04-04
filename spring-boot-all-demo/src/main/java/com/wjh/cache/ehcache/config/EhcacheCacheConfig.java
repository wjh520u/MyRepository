package com.wjh.cache.ehcache.config;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;

import net.sf.ehcache.CacheManager;
@Configuration
public class EhcacheCacheConfig {
	
	@Bean("cacheManager")
    public CacheManager EhCacheManagerFactoryBean() {
    	EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
    	ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("resources/cache/ehcache/ehcache.xml"));
    	ehCacheManagerFactoryBean.setShared(true);
    	//必须初始化
    	ehCacheManagerFactoryBean.afterPropertiesSet();
    	return ehCacheManagerFactoryBean.getObject();
	}
    
    @Bean("EhCacheCacheManager")
    @Primary
    public EhCacheCacheManager ehCacheCacheManager(@Qualifier("cacheManager")CacheManager cacheManager) {
    	EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager(cacheManager);
    	//必须初始化
    	ehCacheCacheManager.afterPropertiesSet();
    	System.out.println(ehCacheCacheManager.getCacheNames());
        return ehCacheCacheManager;
    }
    
    
    
  //自定义key生成方式
    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                String[] value = new String[1];
                Cacheable cacheable = method.getAnnotation(Cacheable.class);
                if (cacheable != null) {
                    value = cacheable.value();
                }
                CachePut cachePut = method.getAnnotation(CachePut.class);
                if (cachePut != null) {
                    value = cachePut.value();
                }
                CacheEvict cacheEvict = method.getAnnotation(CacheEvict.class);
                if (cacheEvict != null) {
                    value = cacheEvict.value();
                }
                sb.append(value[0]);
                sb.append(":");
                sb.append(params[0].toString());
                return sb.toString();
            }
        };
    }
}