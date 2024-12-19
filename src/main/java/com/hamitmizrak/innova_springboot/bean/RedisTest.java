package com.hamitmizrak.innova_springboot.bean;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

// LOMBOK
@Log4j2

@Component
public class RedisTest {

    @Order(3)
    @Bean
    public CommandLineRunner testRedis(StringRedisTemplate redisTemplate) {
        return args -> {
            try {
                redisTemplate.opsForValue().set("test-key", "test-value");
                String value = redisTemplate.opsForValue().get("test-key");
                System.out.println("Redis is working. Retrieved value: " + value);
                log.warn("Redis is working. Retrieved value: " + value);
            } catch (Exception e) {
                System.err.println("Redis is not working: " + e.getMessage());
                log.error("Redis is not working: " + e.getMessage());
            }
        };
    }
}
