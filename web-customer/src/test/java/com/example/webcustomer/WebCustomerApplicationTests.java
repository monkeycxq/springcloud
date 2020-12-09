package com.example.webcustomer;

import com.alibaba.fastjson.JSON;
import com.example.common.util.UniRestUtil;
import com.example.common.web.ResultVO;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@Slf4j
class WebCustomerApplicationTests {

    @Test
    void contextLoads() {
    }


    /**
     * 测试  Unirest 请求接口
     * @author cxq
     * @date 2020/12/4
     */
    @Test
    void testWithdraw(){
        String url = "https://s-pc.csair.com/service/proc/2012040000016/task/50d7d385-35e0-11eb-bfc7-66dea233e2f5/withdraw?userCode=707505";
        Map<String,String> headers = new HashMap<>();
        headers.put("X-Tenant-ID","etm");
        headers.put("Content-Type","text/plain");
        JSONObject param = new JSONObject();
        param.put("comment","测试撤回");
        HttpResponse<String> response = UniRestUtil.postAsString(url,headers,param);
        log.info("response:{}",response.getBody());
        String responseBody = response.getBody();
        if("-1".equals(responseBody)){
            log.info("撤回成功!");
        }else{
            ResultVO resultVO = JSON.parseObject(responseBody, ResultVO.class);
            log.info("resultVO:{}",JSON.toJSONString(resultVO));
        }
    }



}
