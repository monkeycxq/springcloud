package com.example.webcustomer.interview;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class Question {

    public static void  main(String[] args) throws JSONException {
        List<Level> children = recursion(1);
        log.info("json对象：{}", JSON.toJSON(children));
    }

    // 1. create Level class (id,name,parentId)，递归输出树结构的json 对象;
    public static List<Level> recursion(Integer parentId){
        List<Level> levelList = getChildrenByParentId(parentId);
        if(!CollectionUtils.isEmpty(levelList)){
            levelList.stream().forEach(o ->{
                o.setChildren(recursion(o.getId()));
            });
        }
        return levelList;
    }

    private static List<Level> getChildrenByParentId(Integer parentId) {
        ArrayList<Level> list = new ArrayList<>();
        // 查询数据库
        if(parentId == 1){
            Level level = new Level();
            level.setId(2);
            level.setName("tom");
            level.setParentId(1);
            list.add(level);
        }
        if(parentId == 2){
            Level level = new Level();
            level.setId(3);
            level.setName("lily");
            level.setParentId(2);
            list.add(level);

            Level level1 = new Level();
            level1.setId(4);
            level1.setName("monkey");
            level1.setParentId(2);
            list.add(level1);
        }
        return list;
    }




}
