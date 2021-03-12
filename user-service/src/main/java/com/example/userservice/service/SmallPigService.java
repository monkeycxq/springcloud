package com.example.userservice.service;

import com.example.userservice.domain.SmallPig;

import java.util.List;

/**
 * @authoe cxq
 * @date 2021/3/12
 */
public interface SmallPigService {

    List<SmallPig> queryPage(SmallPig smallPig, Integer pageNo, Integer pageSize);
}
