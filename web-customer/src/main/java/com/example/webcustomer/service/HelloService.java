package com.example.webcustomer.service;

import com.example.webcustomer.util.RestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RestHelper restHelper;

    final String USER_SERVICE = "http://service-user/";


    final String listUserUrl = "http://106.52.132.48:8081/basic_project/?status=0";


    public String hiService(String name){
        return restTemplate.getForObject(USER_SERVICE + "hi?name=" + name, String.class);
    }

    public void delete(Integer id) {
        restTemplate.delete(USER_SERVICE + "delete?id=" + id);
    }


}
