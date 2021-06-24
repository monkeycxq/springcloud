package com.example.webcustomer.service;

import com.example.webcustomer.domain.ShopVo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author cxq
 * @date 2021/5/12
 */
public interface EsDao extends ElasticsearchRepository<ShopVo,String> {
}
