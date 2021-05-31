package com.example.common.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @authoe cxq
 * @date 2021/4/22
 */
//@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient() {
       Config config = new Config();
       config.useSingleServer().setAddress("redis://localhost:6379").setPassword("just4fun");

       RedissonClient redisson = Redisson.create(config);
       return redisson;
     }

}
