package com.example.webcustomer.controller;

import com.example.common.util.LocalDateUtil;
import com.example.webcustomer.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;

@RestController
@Slf4j
public class HelloController {

    @Autowired
    HelloService helloService;

    @RequestMapping(value = "/hi")
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



}
