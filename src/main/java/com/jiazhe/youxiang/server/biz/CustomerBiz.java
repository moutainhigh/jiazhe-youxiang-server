/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.biz;

import com.jiazhe.youxiang.server.dto.customer.CustomerAddDTO;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.dto.customer.CustomerUpdateDTO;
import com.jiazhe.youxiang.server.service.CustomerService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CustomerService customerService;

    public void add(CustomerAddDTO customerAddDTO) {
        customerService.add(customerAddDTO);
    }

    public CustomerDTO getById(Integer id) {
        return customerService.getById(id);
    }

    public List<CustomerDTO> getList(String mobile, String name, Paging paging) {
        return customerService.getList(mobile, name, paging);
    }

    public void update(CustomerUpdateDTO customerUpdateDTO) {
        customerService.update(customerUpdateDTO);
    }

    public void delete(Integer id) {
        customerService.delete(id);
    }
}