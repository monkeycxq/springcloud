package com.example.webcustomer.controller;

import com.example.common.domain.UserParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@Slf4j
public class UserController {

    @PostMapping("/addUser")
    public UserParam  addUser(@RequestBody @Valid UserParam userParam){
        return userParam;
    }


    @GetMapping("/getUser")
    public UserParam  userParam(@RequestParam Integer id){
        UserParam userParam = new UserParam(12,"tome","123456","18809876543","21354872389@163.com");
        return userParam;
    }
}
