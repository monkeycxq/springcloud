package com.example.common.web;

import lombok.Getter;

@Getter
public class ResultVO<T> {
    /**
     * 状态码，比如1000代表响应成功
     */
    private int code;
    /**
     * 响应信息，用来说明响应情况
     */
    private String msg;
    /**
     * 响应的具体数据
     */
    private T data;

    public ResultVO(T data) {
        this(ResultCode.SUCCESS, data);
    }

    public ResultVO(ResultCode resultCode,  T data) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.data = data;
    }

    public ResultVO(ResultCode resultCode,  String msg,T data) {
        this.code = resultCode.getCode();
        this.msg = msg;
        this.data = data;
    }

    public ResultVO(int code,  String msg,T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
