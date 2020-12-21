package com.example.webcustomer.interview;

import java.util.List;

/**
 * 等级类
 */
public class Level {
    private Integer id;
    private String name;
    private Integer parentId;
    private List<Level> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<Level> getChildren() {
        return children;
    }

    public void setChildren(List<Level> children) {
        this.children = children;
    }
}
