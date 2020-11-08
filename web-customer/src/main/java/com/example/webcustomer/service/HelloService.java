package com.example.webcustomer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate;

    final String USER_SERVICE = "http://service-user/";

    public String hiService(String name)
    {
        return restTemplate.getForObject(USER_SERVICE + "hi?name=" + name, String.class);
    }

    public void delete(Integer id) {
        restTemplate.delete(USER_SERVICE + "delete?id=" + id);
    }
}
