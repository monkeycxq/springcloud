package com.example.webcustomer.controller;

import com.alibaba.fastjson.JSON;
import com.example.common.domain.UserParam;
import com.example.common.exception.APIException;
import com.example.common.util.RedisUtil;
import com.example.webcustomer.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @author cxq
 * @date 2021/5/6
 */
@RestController
@Slf4j
@RequestMapping("/sys/")
public class LoginController {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 登录
     * @author cxq
     * @date 2021-05-06
     */
    @PostMapping("login")
    public String login(@RequestBody UserParam userParam){
        if(StringUtils.isEmpty(userParam.getName()) || StringUtils.isEmpty(userParam.getPassword())){
            throw new APIException("用户名或密码不能为空！");
        }
        List<Object> users = redisUtil.lGet("user", 0, -1);
        if(CollectionUtils.isEmpty(users)){
            throw new APIException("用户列表数据为空！");
        }
        List<UserParam> userList = JSON.parseArray(JSON.toJSONString(users),UserParam.class);
        Optional<UserParam> first = userList.stream().filter(o -> o.getName().equals(userParam.getName())).findFirst();
        if(!first.isPresent()){
            throw new APIException("用户名不存在！");
        }
        UserParam user = first.get();
        if(!userParam.getPassword().equals(user.getPassword())){
            throw new APIException("密码错误！");
        }
        String token = TokenUtil.getToken(user.getId());
        log.info("生成token为：{}",token);
        redisUtil.hset("token",token,user,1800);
        return token;
    }

    /**
     * 退出登录
     * @author cxq
     * @date 2021-05-06
     */
    @PostMapping("logout")
    public Boolean logout(String token){
        Long num = redisUtil.hdel("token", token);
        log.info("退出登录，删除token：{}",num);
        return num > 0;
    }
}
