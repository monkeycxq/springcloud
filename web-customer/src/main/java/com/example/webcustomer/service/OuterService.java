package com.example.webcustomer.service;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface OuterService {




    /**
     * GET 调用外部系统接口
     * @return
     */
    @RequestLine("GET /{interfaceName}")
    @Headers({ "Charset: UTF-8"})
    String doGet(@Param("interfaceName") String interfaceName,String param);

    /**
     * POST 调用外部系统接口
     * @return
     */
    @RequestLine("POST")
    @Headers({ "Charset: UTF-8"})
    String doPost(String param);

    /**
     * 查询用户列表
     * @return
     */
    @RequestLine("GET /user/listUser")
    @Headers({"Charset: UTF-8"})
    String listUser();




}
