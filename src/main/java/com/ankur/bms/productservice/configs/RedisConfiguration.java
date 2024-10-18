package com.ankur.bms.productservice.configs;


import org.springframework.context.annotation.*;
import org.springframework.data.redis.*;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.core.*;

@Configuration
public class RedisConfiguration {

    @Bean
    public RedisTemplate<String,Object> createRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }



}
