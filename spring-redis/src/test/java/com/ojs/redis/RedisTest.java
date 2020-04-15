package com.ojs.redis;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;
    
    @Test
    public void testDataHandling() {
        
        redisTemplate.getConnectionFactory().getConnection().info().toString();
        
        String key = "joongsuk";
        String value = "oh";
        redisTemplate.opsForValue().set(key, value);
        String returnValue = (String) redisTemplate.opsForValue().get(key);
        
        System.out.println(value);
    }
	
}
