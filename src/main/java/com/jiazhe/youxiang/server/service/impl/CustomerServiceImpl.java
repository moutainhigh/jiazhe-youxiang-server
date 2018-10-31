/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.service.impl;

import com.jiazhe.youxiang.server.adapter.CustomerAdapter;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.CustomerCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.CustomerException;
import com.jiazhe.youxiang.server.dao.mapper.CustomerPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.CustomerPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.CustomerPO;
import com.jiazhe.youxiang.server.dto.customer.CustomerAddDTO;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.dto.customer.CustomerUpdateDTO;
import com.jiazhe.youxiang.server.service.CustomerService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 客户管理ServiceImpl
 *
 * @author niexiao
 * @created 2018/10/31
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerPOMapper customerPOMapper;

    @Autowired
    private CustomerPOManualMapper customerPOManualMapper;

    @Override
    public void add(CustomerAddDTO customerAddDTO) {
        Integer count = customerPOManualMapper.count(customerAddDTO.getMobile(), null);
        if(count>0){
            throw new CustomerException(CustomerCodeEnum.CUSTOMER_MOBILE_REPEAT);
        }
        CustomerPO customerPO = CustomerAdapter.customerAddDTO2PO(customerAddDTO);
        customerPOMapper.insertSelective(customerPO);
    }

    @Override
    public CustomerDTO getById(Integer id) {
        CustomerPO customerPO = customerPOMapper.selectByPrimaryKey(id);
        return CustomerAdapter.customerPO2DTO(customerPO);
    }

    @Override
    public List<CustomerDTO> getList(String mobile, String name, Paging paging) {
        Integer count = customerPOManualMapper.count(mobile, name);
        List<CustomerPO> customerPOList = customerPOManualMapper.query(mobile, name, paging.getOffset(), paging.getLimit());
        paging.setTotal(count);
        return customerPOList.stream().map(CustomerAdapter::customerPO2DTO).collect(Collectors.toList());
    }

    @Override
    public void update(CustomerUpdateDTO customerUpdateDTO) {
        CustomerPO customerPO = CustomerAdapter.customerUpdateDTO2PO(customerUpdateDTO);
        customerPO.setModTime(new Date());
        customerPOMapper.updateByPrimaryKeySelective(customerPO);
    }

    @Override
    public void delete(Integer id) {
        CustomerPO customerPO = new CustomerPO();
        customerPO.setId(id);
        customerPO.setModTime(new Date());
        customerPO.setIsDeleted(CommonConstant.CODE_DELETED);
        customerPOMapper.updateByPrimaryKeySelective(customerPO);
    }
}