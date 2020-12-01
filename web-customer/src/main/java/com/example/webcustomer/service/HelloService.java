package com.example.webcustomer.service;

import com.example.webcustomer.domain.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    final String USER_SERVICE = "http://service-user/";


    final String listUserUrl = "http://106.52.132.48:8081/basic_project/user/listUser?current=1&orderBy=asc&orderColumn=id&size=10&status=0";


    public String hiService(String name){
        return restTemplate.getForObject(USER_SERVICE + "hi?name=" + name, String.class);
    }

    public void delete(Integer id) {
        restTemplate.delete(USER_SERVICE + "delete?id=" + id);
    }

    public List<UserVO> listUser() {
        return  restTemplate.getForObject(listUserUrl, ArrayList.class);
    }
}
