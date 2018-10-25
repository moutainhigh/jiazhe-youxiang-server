/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.biz;

import com.jiazhe.youxiang.server.dto.customer.CustomerAddDTO;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/23
 */
@Service("customerBiz")
public class CustomerBiz {

    public void add(CustomerAddDTO customerAddDTO) {
    }

    public CustomerDTO getById(Integer id) {
        return null;
    }

    public List<CustomerDTO> getList(String mobile, String name, Paging paging) {
        return null;
    }

    public void update(CustomerDTO customerDTO) {
    }

    public void delete(Integer id) {
    }
}