package com.example.common.constant;

import lombok.Getter;

@Getter
public enum SexEnum {
    MAN("man","男"),
    WOMEN("women","女"),
    NONE("none","未知");

    private String key;
    private String value;

    SexEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
