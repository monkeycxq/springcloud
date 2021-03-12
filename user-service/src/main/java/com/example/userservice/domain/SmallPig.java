package com.example.userservice.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

/**
 * @authoe cxq
 * @date 2021/3/12
 */

@Document(collection = "small_pig")
public class SmallPig{

    @MongoId
    private String _id;

    @Field("name")
    private String name;

    @Field("age")
    private Integer age;

    @Field("wight")
    private Double wight;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getWight() {
        return wight;
    }

    public void setWight(Double wight) {
        this.wight = wight;
    }
}
