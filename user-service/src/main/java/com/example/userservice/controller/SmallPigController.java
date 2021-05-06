package com.example.userservice.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.common.domain.UserParam;
import com.example.userservice.domain.SmallPig;
import com.example.userservice.service.SmallPigService;
import com.example.userservice.util.MongodbUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @authoe cxq
 * @date 2021/3/12
 */
@RequestMapping("/smallPig/")
@RestController
@Slf4j
public class SmallPigController {

    @Autowired
    private SmallPigService smallPigService;

    @Autowired
    private MongodbUtil mongodbUtil;

    final private String TABLE = "small_pig";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("save")
    public void save(SmallPig smallPig){
        mongodbUtil.insertJSON(TABLE,JSON.toJSONString(smallPig));
    }


    @PostMapping("delById")
    public void delById(String id){
        mongodbUtil.deleteJSON(TABLE,id);
    }


    @GetMapping("queryPage")
    public List<SmallPig> queryPage(SmallPig smallPig, Integer pageNo, Integer pageSize){
        return smallPigService.queryPage(smallPig,pageNo,pageSize);
    }

    /**
     * 用mongoDB工具类获取指定数据
     * @author cxq
     * @date 2021/3/12
     * @param id
     */
    @GetMapping("getJSONById")
    public List<JSONObject> getJSONById(String id){
        List<JSONObject> jsonObject = mongodbUtil.getJSONById(TABLE, id);
        log.info("smallPig:{}", JSON.toJSON(jsonObject));
        return jsonObject;
    }

    /**
     * 用mongoDB工具类获取所有数据
     * @author cxq
     * @date 2021/3/12
     */
    @GetMapping("getJSONList")
    public List<JSONObject> getJSONList(){
        List<JSONObject> jsonObject = mongodbUtil.getJSONList(TABLE);
        log.info("smallPig:{}", JSON.toJSON(jsonObject));
        return jsonObject;
    }

    /**
     * 发送消息 rabbitmq
     * @author cxq
     * @date 2021/4/25
     * @param userParam
     * @return void
     */
    @PostMapping("sendAddUser")
    public void sendAddUser(@Valid @RequestBody UserParam userParam){
        rabbitTemplate.convertAndSend("addUser",userParam);
    }


}
