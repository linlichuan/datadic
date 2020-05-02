package com.springboot.dbtool.dao.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisAccesstor {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public String get(String key){
        return redisTemplate.opsForValue().get(key);
    }

    public void set(String key,String value){
        redisTemplate.opsForValue().set(key,value);
    }

}
