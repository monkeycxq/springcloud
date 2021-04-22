package com.example.webcustomer.service;

import com.example.common.util.RedisUtil;
import com.example.webcustomer.util.RestHelper;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RestHelper restHelper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedissonClient redissonClient;


    final String USER_SERVICE = "http://service-user/";


    final String listUserUrl = "http://106.52.132.48:8081/basic_project/?status=0";


    public String hiService(String name){
        return restTemplate.getForObject(USER_SERVICE + "hi?name=" + name, String.class);
    }

    public void delete(Integer id) {
        restTemplate.delete(USER_SERVICE + "delete?id=" + id);
    }


    public Integer visitNumINC(){
        int visitNum2 = 0;

        String lockKey = "redisLock-visitNum";
        RLock lock = redissonClient.getLock(lockKey);
        try {
            boolean res = lock.tryLock(10, TimeUnit.SECONDS);
            if (res) {
                String key = "visitNum";
                int visitNum1 = (Integer) redisUtil.get(key);
                log.info("新增前 visitNum:{}", visitNum1);
                redisUtil.set(key, visitNum1 + 1);
                visitNum2 = (Integer) redisUtil.get(key);
                log.info("新增后：visitNum:{}", visitNum2);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return visitNum2;
    }

}
