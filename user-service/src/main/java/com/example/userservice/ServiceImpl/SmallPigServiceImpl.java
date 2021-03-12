package com.example.userservice.ServiceImpl;

import com.example.userservice.domain.SmallPig;
import com.example.userservice.service.SmallPigService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @authoe cxq
 * @date 2021/3/12
 */
@Slf4j
@Service
public class SmallPigServiceImpl implements SmallPigService {

    @Autowired
    public MongoTemplate mongoTemplate;


    /**
     * 分页查询
     * @author cxq
     * @date 2021/3/12
     * @param smallPig
     * @param pageNo 页码
     * @param pageSize  页大小
     */
    @Override
    public List<SmallPig> queryPage(SmallPig smallPig, Integer pageNo, Integer pageSize) {
        Query query = new Query();
        if(StringUtils.isNotEmpty(smallPig.getName())){
            query.addCriteria(Criteria.where("name").is(smallPig.getName()));
        }
        int skip = (pageNo - 1) * pageSize;
        query.skip(skip);// 从那条记录开始
        query.limit(pageSize);// 取多少条记录
        List<SmallPig> list = mongoTemplate.find(query,SmallPig.class);
        return list;
    }
}
