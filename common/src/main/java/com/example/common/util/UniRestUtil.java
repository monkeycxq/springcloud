package com.example.common.util;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
/**
 * @authoe cxq
 * @date 2020/12/4
 */
@Slf4j
@Component
public class UniRestUtil {
    /**
     * post 请求，返回body为String
     * @author cxq
     * @date 2020/12/4
     * @param
     * @return HttpResponse<String>
     */
   public static HttpResponse<String> postAsString(String url, Map<String,String> headers,Object params){
       HttpResponse<String> response = Unirest.post(url)
               .headers(headers)
               .body(params)
               .asString();

       log.info("responseStatus:{}",response.getStatus());
       log.info("responseBody:{}",response.getBody());
       return response;
    }

}
