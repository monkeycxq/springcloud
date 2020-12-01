package com.example.webcustomer.util;

import com.example.webcustomer.service.OuterService;
import feign.Feign;
import org.springframework.stereotype.Service;

/**
 * @authoe cxq
 * @date 2020/12/1
 */
@Service
public class RestHelper {

    public String getForOuter(String url,String interfaceName,String jsonParam){
        return Feign.builder().target(OuterService.class,url).doGet(interfaceName,jsonParam);
    }


    public String postForOuter(String url,String jsonParam){
        return Feign.builder().target(OuterService.class,url).doPost(jsonParam);
    }
}
