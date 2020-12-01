package com.example.webcustomer.controller;

import com.example.common.util.LocalDateUtil;
import com.example.webcustomer.domain.ListUserForm;
import com.example.webcustomer.service.HelloService;
import com.example.webcustomer.service.OuterService;
import com.example.webcustomer.service.UserService;
import feign.Feign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;

@RestController
@Slf4j
public class HelloController {

    @Autowired
    HelloService helloService;

    @Autowired
    UserService userService;

    final String listUserUrl = "http://106.52.132.48:8081/basic_project/?status=0";

    @GetMapping(value = "/hi")
    public String hi(@RequestParam String name){
        Date date = new Date();
        LocalDate localDate = LocalDateUtil.dateToLocalDate(date);
        log.debug("localDate:{}",localDate);
        return helloService.hiService(name);
    }


    @PostMapping(value = "/delete")
    public String delete(@RequestParam Integer id){
         helloService.delete(id);
         return "删除成功！";
    }


    /**
     * 查询用户列表
     * @return
     */
    @GetMapping(value = "/listUser")
    public String listUser(){
        log.debug("查询用户列表接口");
        ListUserForm userForm = new ListUserForm();
        userForm.setStatus("0");
        log.debug("查询用户猎列表入参：{}",userForm.toString());
        return Feign.builder().target(OuterService.class,listUserUrl).listUser();
    }

    /**
     * 用feign消费服务
     * @author cxq
     * @date 2020/12/1
     * @param name
     * @return java.lang.String
     */
    @GetMapping(value = "/feign/hi")
    public String sayHi(@RequestParam String name){
        log.debug("查询用户列表接口:");
        return userService.sayHiFromClientOne(name);
    }


}
