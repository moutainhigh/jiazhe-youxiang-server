/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.service;

import com.jiazhe.youxiang.server.dto.customer.CustomerAddDTO;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.dto.customer.CustomerUpdateDTO;
import com.jiazhe.youxiang.server.vo.Paging;

import java.util.List;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/31
 */
public interface CustomerService {

    void add(CustomerAddDTO customerAddDTO);

    CustomerDTO getById(Integer id);

    List<CustomerDTO> getList(String mobile, String name, Paging paging);

    void update(CustomerUpdateDTO customerUpdateDTO);

    void delete(Integer id);
}
