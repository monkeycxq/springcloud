package com.example.webcustomer.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;

@Data
public class ListUserForm {

    /**
     * 用户状态
     */
    @NotEmpty(message = "用户状态不能为空")
    @Range(min =  -1 , max = 1 , message = "用户状态有误")
    private String status;

}
