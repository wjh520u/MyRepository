package com.wjh.cache.ehcache.config;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;


@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport{
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

    //缓存管理器
    @Bean("RedisCacheManager")
    public CacheManager cacheManager(RedisConnectionFactory jedisConnectionFactory) {
    	
    	RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig();
    	
    	SerializationPair<String> keySerializationPair = RedisSerializationContext.fromSerializer(new StringRedisSerializer()).getKeySerializationPair();
    	SerializationPair<String> valueSerializationPair = RedisSerializationContext.fromSerializer(new StringRedisSerializer()).getValueSerializationPair();
    	//默认配置
    	defaultCacheConfig.serializeKeysWith(keySerializationPair);
    	defaultCacheConfig.serializeValuesWith(valueSerializationPair);
    	defaultCacheConfig.entryTtl(Duration.ofSeconds(1));
    	
    	//自定义缓存名称配置
    	Map<String ,RedisCacheConfiguration> cacheNameRedisConfig = new HashMap<String, RedisCacheConfiguration>();
    	RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
    			.serializeKeysWith(keySerializationPair)
    			.entryTtl(Duration.ofSeconds(2));
    	cacheNameRedisConfig.put("testCacheName4", cacheConfig);
    	
        RedisCacheManager cacheManager = 
        		new RedisCacheManager(
        				RedisCacheWriter.nonLockingRedisCacheWriter(jedisConnectionFactory),
        				defaultCacheConfig,cacheNameRedisConfig);
        return cacheManager;
    }
    
    
    /**
     * JedisPoolConfig 连接池
     * @return
     */
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最大空闲数
        jedisPoolConfig.setMaxIdle(maxIdle);
        // 连接池的最大数据库连接数
        jedisPoolConfig.setMaxTotal(maxTotal);
        // 最大建立连接等待时间
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        // 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
        jedisPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        // 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
        jedisPoolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
        // 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        // 是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        // 在空闲时检查有效性, 默认false
        jedisPoolConfig.setTestWhileIdle(testWhileIdle);
        return jedisPoolConfig;
    }
    /**
     * 单机版配置
    * @Title: JedisConnectionFactory 
    * @param @param jedisPoolConfig
    * @param @return
    * @return JedisConnectionFactory
    * @autor lpl
    * @date 2018年2月24日
    * @throws
     */
    @Bean
    public RedisConnectionFactory JedisConnectionFactory(JedisPoolConfig jedisPoolConfig){
    	 //IP地址  
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
    	redisStandaloneConfiguration.setHostName(hostName);
    	redisStandaloneConfiguration.setPort(port);
        //获得默认的连接池构造
        //这里需要注意的是，edisConnectionFactoryJ对于Standalone模式的没有（RedisStandaloneConfiguration，JedisPoolConfig）的构造函数，对此
        //我们用JedisClientConfiguration接口的builder方法实例化一个构造器，还得类型转换
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpcf = (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
        //修改我们的连接池配置
        jpcf.poolConfig(jedisPoolConfig);
        //通过构造器来构造jedis客户端配置
        JedisClientConfiguration jedisClientConfiguration = jpcf.build();
        //连接池  
        //如果Redis设置有密码  
        //redisStandaloneConfiguration.setPassword(password);  
        //客户端超时时间单位是毫秒    
        return  new JedisConnectionFactory(redisStandaloneConfiguration,jedisClientConfiguration);
    }
    
    /**
     * 实例化 RedisTemplate 对象
     *
     * @return
     */
    @Bean
    public RedisTemplate<String, String> functionDomainRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        initDomainRedisTemplate(redisTemplate, redisConnectionFactory);
        return redisTemplate;
    }
    /**
     * 设置数据存入 redis 的序列化方式,并开启事务
     * 
     * @param redisTemplate
     * @param factory
     */
    private void initDomainRedisTemplate(RedisTemplate<String, String> redisTemplate, RedisConnectionFactory factory) {
        //如果不配置Serializer，那么存储的时候缺省使用String，如果用User类型存储，那么会提示错误User can't cast to String！  
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        // 开启事务
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setConnectionFactory(factory);
    }
    
    @Value("${redis.maxIdle}")
    private Integer maxIdle;

    @Value("${redis.maxTotal}")
    private Integer maxTotal;

    @Value("${redis.maxWaitMillis}")
    private Integer maxWaitMillis;

    @Value("${redis.minEvictableIdleTimeMillis}")
    private Integer minEvictableIdleTimeMillis;

    @Value("${redis.numTestsPerEvictionRun}")
    private Integer numTestsPerEvictionRun;

    @Value("${redis.timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;

    @Value("${redis.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${redis.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${redis.hostName}")
    private String hostName;
    
    @Value("${redis.password}")
    private Integer password;
    
    @Value("${redis.port}")
    private Integer port;
}