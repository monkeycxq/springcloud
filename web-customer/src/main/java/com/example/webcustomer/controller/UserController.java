package com.example.webcustomer.controller;

import com.example.common.domain.UserParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 用户接口
 * @author cxq
 * @date 2020/12/10
 */
@RestController
@Slf4j
@RequestMapping("/api/user/")
public class UserController {

    /**
     * 添加用户
     * @author cxq
     * @date 2020/12/9
     * @param userParam 用户对象
     * @return com.example.common.domain.UserParam
     */
    @PostMapping("addUser")
    public UserParam  addUser(@Valid @RequestBody UserParam userParam){
        return userParam;
    }


    /**
     * 根据id查询用户
     * @author cxq
     * @date 2020/12/9
     * @param id 用户主键
     * @return com.example.common.domain.UserParam
     */
    @GetMapping("getUser")
    public UserParam  userParam(@RequestParam Integer id){
        UserParam userParam = new UserParam(12,"tome","123456","18809876543","21354872389@163.com");
        return userParam;
    }
}
