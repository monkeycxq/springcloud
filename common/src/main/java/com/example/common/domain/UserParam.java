package com.example.common.domain;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
public class UserParam {
    private Integer id; // 主键

    @NotEmpty(message = "用户姓名不能为空！")
    private String name; // 用户名

    @NotEmpty(message = "用户密码不能为空！")
    @Size(min = 6, max = 11, message = "密码长度必须是6-16个字符！")
    private String password;

    @NotEmpty(message = "手机号不能为空")
    @Size(min = 11, max = 11, message = "手机号必须是11位！")
    private String phone;

    @NotEmpty(message = "用户邮箱不能为空！")
    @Email(message = "邮箱格式不正确！")
    private String email;

    public UserParam(Integer id, String name,String password, String phone,  String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }
}
