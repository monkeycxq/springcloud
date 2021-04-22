package com.example.webcustomer.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @authoe cxq
 * @date 2021/4/19
 */
@Data
public class ProductDO {
    private Integer id;
    private String name;
    private String code;
    private BigDecimal price;
    private String comment;
    private TypeDO typeDO;
}
