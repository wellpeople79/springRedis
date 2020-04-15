package com.ojs.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class RedisConfig {

	/**
     * Redis Cluster 구성 설정
     */
    @Autowired
    private RedisClusterConfigurationProperties clusterProperties;
    
    /**
     * JedisPool관련 설정
     * @return
     */
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        return new JedisPoolConfig();
    }
    
    
    /**
     * Redis Cluster 구성 설정
     */
    @Bean
    public RedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
        return new JedisConnectionFactory(new RedisClusterConfiguration(clusterProperties.getNodes()),jedisPoolConfig);
    }
    
    /**
     * RedisTemplate관련 설정
     * 
     * -Thread-safety Bean
     * @param jedisConnectionConfig - RedisTemplate에 설정할 JedisConnectionConfig
     * @return
     */
    @Bean(name="redisTemplate")
    public RedisTemplate redisTemplateConfig(JedisConnectionFactory jedisConnectionConfig) {
        
        RedisTemplate redisTemplate = new RedisTemplate<>();
 
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(jedisConnectionConfig);
        
        return redisTemplate;
        
    }
    
    /**
     * 문자열 중심 편의 RedisTemplate
     * 
     * @param jedisConnectionConfig
     * @return
     */
    @Bean(name="stringRedisTemplate")
    public StringRedisTemplate stringRedisTemplate(JedisConnectionFactory jedisConnectionConfig) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(jedisConnectionConfig);
        
        return stringRedisTemplate;
    }
	
}
