package com.example.webcustomer.controller;

import com.example.common.util.LocalDateUtil;
import com.example.webcustomer.domain.UserVO;
import com.example.webcustomer.service.HelloService;
import com.example.webcustomer.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
public class HelloController {

    @Autowired
    HelloService helloService;

    @Autowired
    UserService userService;

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
    public List<UserVO> listUser(){
        log.debug("查询用户列表接口");
        return helloService.listUser();
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
