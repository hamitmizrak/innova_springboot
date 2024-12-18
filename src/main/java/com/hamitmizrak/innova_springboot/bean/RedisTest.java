package com.hamitmizrak.innova_springboot.bean;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisTest {

    @Bean
    public CommandLineRunner testRedis(StringRedisTemplate redisTemplate) {
        return args -> {
            try {
                redisTemplate.opsForValue().set("test-key", "test-value");
                String value = redisTemplate.opsForValue().get("test-key");
                System.out.println("Redis is working. Retrieved value: " + value);
            } catch (Exception e) {
                System.err.println("Redis is not working: " + e.getMessage());
            }
        };
    }
}
