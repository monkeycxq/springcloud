package com.example.webcustomer.controller;

import com.example.common.util.LocalDateUtil;
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

    final String listUserUrl = "http://106.52.132.48:8081/basic_project/";

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
    /*@GetMapping(value = "/listUser")
    public String listUser(){
        log.debug("查询用户列表接口");
        ListUserForm userForm = new ListUserForm();
        userForm.setStatus("0");
        Object json = JSONObject.toJSON(userForm);
        String param = json.toString();
        log.debug("查询用户列表入参：{}",param);
        String status = "0";
        String result = Feign.builder().target(OuterService.class, listUserUrl).listUser(status);
        log.info("result:{}",result);
        JSONObject jsonObject = JSONObject.parseObject(result);
        int code = jsonObject.getIntValue("code");
        String message = jsonObject.getString("message");
        String data = jsonObject.getString("data");
       if(code != 0){
            throw new APIException(message);
        }
        return data;
    }*/

    /**
     * 用feign消费服务(内部服务)
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

    @PostMapping(value = "/withdraw")
    public String testPostOuter(){
        //String url = "https://s-pc.csair.com/service/proc/2012040000016/task/50d7d385-35e0-11eb-bfc7-66dea233e2f5/withdraw?userCode=707505";
        String url = "https://s-pc.csair.com/service/proc/2012040000016/task/50d7d385-35e0-11eb-bfc7-66dea233e2f5?userCode=707505";
        String procInstanceId = "2012040000016";
        String taskId = "50d7d385-35e0-11eb-bfc7-66dea233e2f5";
        String userCode = "707505";
        String data = "{\"comment\":\"测试撤回\"}";
        String result = null;
        result = Feign.builder().target(OuterService.class, url).withdraw(data);
        log.info("撤回返回结果：{}",result);
        return result;
    }


}
