package com.example.common.web;

import lombok.Getter;

/**
 * 响应码枚举
 */
@Getter
public enum ResultCode {
    SUCCESS(1000, "操作成功"),

    FAILED(1001, "响应失败"),

    VALIDATE_FAILED(1002, "参数校验失败"),

    EXPIRED_TOKEN(1003, "token过期"),

    ERROR(5000, "未知错误"),
    UNKNOW(500, "未知错误");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
