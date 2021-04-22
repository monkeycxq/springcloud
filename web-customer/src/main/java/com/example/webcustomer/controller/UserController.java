package com.example.webcustomer.controller;

import com.alibaba.fastjson.JSON;
import com.example.common.domain.UserParam;
import com.example.common.exception.APIException;
import com.example.common.thread.ThreadPoolExecutorUtil;
import com.example.common.util.RedisUtil;
import com.example.webcustomer.domain.ShopVo;
import com.example.webcustomer.service.HelloService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    HelloService helloService;

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


    // 测试redis 缓存复杂对象

    /**
     * 添加店铺
     * @author cxq
     * @date 2021-04-19
     * @param shopVo 店铺对象
     */
    @PostMapping("addShop")
    public ShopVo  addShop(@Valid @RequestBody ShopVo shopVo){
        List<Object> shopList = redisUtil.lGet("shop", 0, -1);
        if(!CollectionUtils.isEmpty(shopList)){
            List<ShopVo> userList = JSON.parseArray(JSON.toJSONString(shopList),ShopVo.class);
            Optional<ShopVo> first = userList.stream().filter(o -> o.getName().equals(shopVo.getName())).findFirst();
            if(first.isPresent()){
                throw new APIException("店铺名字重复！");
            }
        }
        redisUtil.lSet("shop",shopVo);
        log.info("添加数据到缓存：{}",JSON.toJSON(shopVo));
        return shopVo;
    }

    /**
     * 查询所有店铺
     * @author cxq
     * @date 2021-04-19
     */
    @GetMapping("getShopList")
    public List<ShopVo>  getShopList(){
        List<Object> shopList = redisUtil.lGet("shop", 0, -1);
        if(CollectionUtils.isEmpty(shopList)) {
            return new ArrayList<>();
        }
        List<ShopVo> userList = JSON.parseArray(JSON.toJSONString(shopList),ShopVo.class);
        return userList;
    }

    /**
     * 根据id查询店铺
     * @author cxq
     * @date 2021-04-19
     */
    @GetMapping("getShop")
    public ShopVo getShop(@RequestParam @NotNull Integer id){
        List<Object> shopList = redisUtil.lGet("shop", 0, -1);
        if(CollectionUtils.isEmpty(shopList)) {
            return null;
        }
        List<ShopVo> userList = JSON.parseArray(JSON.toJSONString(shopList),ShopVo.class);
        Optional<ShopVo> first = userList.stream().filter(o -> id.equals(o.getId())).findFirst();
        return first.orElse(null);
    }


    // 测试 redis 分布式锁

    /**
     * 访问次数加一
     * @author cxq
     * @date 2021-04-20
     */
    @PostMapping("visitNumINC")
    public Integer  visitNumINC(){
        String key = "visitNum";
        for(int i =0;i<100;i++) {
            ThreadPoolExecutorUtil.getPoll().execute(() -> {
                helloService.visitNumINC();
            });
        }
        Integer visitNum = (Integer)redisUtil.get(key);
        return visitNum;
    }



}
