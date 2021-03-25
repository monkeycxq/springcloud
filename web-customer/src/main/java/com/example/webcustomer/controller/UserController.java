package com.example.webcustomer.controller;

import com.alibaba.fastjson.JSON;
import com.example.common.domain.UserParam;
import com.example.common.exception.APIException;
import com.example.common.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 用户接口
 * @author cxq
 * @date 2020/12/10
 */
@RestController
@Slf4j
@RequestMapping("/api/user/")
public class UserController {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 添加用户
     * @author cxq
     * @date 2020/12/9
     * @param userParam 用户对象
     * @return com.example.common.domain.UserParam
     */
    @PostMapping("addUser")
    public UserParam  addUser(@Valid @RequestBody UserParam userParam){
        List<Object> user = redisUtil.lGet("user", 0, -1);
        if(!CollectionUtils.isEmpty(user)){
            List<UserParam> userList = JSON.parseArray(JSON.toJSONString(user),UserParam.class);
            Optional<UserParam> first = userList.stream().filter(o -> o.getName().equals(userParam.getName())).findFirst();
            if(first.isPresent()){
                throw new APIException("名字重复！");
            }
        }
        redisUtil.lSet("user",userParam);
        log.info("添加数据到缓存：{}",JSON.toJSON(userParam));
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
    public UserParam  getUser(@RequestParam Integer id){
        UserParam userParam = new UserParam(12,"tome","123456","18809876543","21354872389@163.com");

        return userParam;
    }

    /**
     * 查询所有用户
     * @author cxq
     * @date 2020/12/9
     * @return com.example.common.domain.UserParam
     */
    @GetMapping("getUserList")
    public List<UserParam>  getUserList(){
        List<Object> user = redisUtil.lGet("user", 0, -1);
        if(CollectionUtils.isEmpty(user)) {
            return new ArrayList<>();
        }
        List<UserParam> userList = JSON.parseArray(JSON.toJSONString(user),UserParam.class);
        return userList;
    }
}
