package com.example.webcustomer.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;
import java.util.List;

/**
 * @authoe cxq
 * @date 2021/4/19
 */
@Data
public class ShopVo {
    private Integer id;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String name;
    private String code;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate;
    private List<ProductDO> productList;
    private List<FansDO> fansList;
}
