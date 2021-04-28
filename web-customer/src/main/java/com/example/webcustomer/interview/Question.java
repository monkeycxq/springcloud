package com.example.webcustomer.interview;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class Question {

    public static void  main(String[] args) throws JSONException {
        /*List<Level> children = recursion(1);
        log.info("json对象：{}", JSON.toJSON(children));*/

        List<Customer> customers = queryDistinctCustomer(getListCustomer());
        log.info("json对象：{}", JSON.toJSON(customers));
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

   // 2. 循环去重复
   public static List<Customer> queryDistinctCustomer(List<Customer> oldList){
       List<Customer> newList = new ArrayList<>();
       oldList.forEach(o -> {
           List<String> names = newList.stream().map(Customer::getName).collect(Collectors.toList());
           if(!names.contains(o.getName())){
               newList.add(o);
           }
       });
       return newList;
   }

    public static List<Customer> getListCustomer(){
        List<Customer> list = new ArrayList<>();
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("张三");
        customer.setAge(34);
        list.add(customer);

        Customer customer1 = new Customer();
        customer1.setId(2);
        customer1.setName("李四");
        customer1.setAge(34);
        list.add(customer1);

        Customer customer2 = new Customer();
        customer2.setId(3);
        customer2.setName("张三");
        customer2.setAge(20);
        list.add(customer2);

        Customer customer3 = new Customer();
        customer3.setId(4);
        customer3.setName("张三");
        customer3.setAge(25);
        list.add(customer3);

        Customer customer4 = new Customer();
        customer4.setId(5);
        customer4.setName("王伍");
        customer4.setAge(28);
        list.add(customer4);

        return list;
    }


}
