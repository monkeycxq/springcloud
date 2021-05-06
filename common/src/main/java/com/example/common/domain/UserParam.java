package com.example.common.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;



public class UserParam{
    private Long id; // 主键

    @NotEmpty(message = "用户姓名不能为空！")
    private String name; //用户名（必须）

    @NotEmpty(message = "用户密码不能为空！")
    @Size(min = 6, max = 11, message = "密码长度必须是6-16个字符！")
    private String password; //密码（必须）

    @NotEmpty(message = "手机号不能为空")
    @Size(min = 11, max = 11, message = "手机号必须是11位！")
    private String phone; //手机号（必须）

    @NotEmpty(message = "用户邮箱不能为空！")
    @Email(message = "邮箱格式不正确！")
    private String email; //邮箱（必须）

    public UserParam(Long id, String name,String password, String phone,  String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }

    public UserParam(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
