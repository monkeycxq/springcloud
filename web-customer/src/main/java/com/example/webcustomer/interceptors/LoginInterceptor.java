package com.example.webcustomer.interceptors;

import com.example.common.exception.APIException;
import com.example.common.util.RedisUtil;
import com.example.common.web.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 * @author cxq
 * @date 2021/5/6
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token)){
            throw new APIException(ResultCode.VALIDATE_FAILED.getCode(),"非法请求!");
        }
        Object user = redisUtil.hget("token", token);
        if(user == null){
            throw new APIException(ResultCode.EXPIRED_TOKEN.getCode(),"请先登录!");
        }
        return true;
    }

}
