/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.service;

import com.jiazhe.youxiang.server.dto.customer.AddressAddDTO;
import com.jiazhe.youxiang.server.dto.customer.AddressDTO;
import com.jiazhe.youxiang.server.dto.customer.AddressUpdateDTO;
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

    /**
     * 添加大家保险的代理人
     * @param customerAddDTO
     */
    void addDJBXAgent(CustomerAddDTO customerAddDTO);

    CustomerDTO getById(Integer id);

    /**
     * 根据客户手机号获得客户信息
     * @param mobile
     * @return
     */
    CustomerDTO getByMobile(String mobile);

    List<CustomerDTO> getList(String mobile, String name, Paging paging);

    void update(CustomerUpdateDTO customerUpdateDTO);

    void delete(Integer id);

    List<AddressDTO> getAddressList(Integer customerId, Paging paging);

    void setAddressDefault(Integer id, Integer isDefault);

    void deleteAddress(Integer id);

    void updateAddress(AddressUpdateDTO addressUpdateDTO);

    AddressDTO getAddressById(Integer id);

    void addAddress(AddressAddDTO addressAddDTO);

    AddressDTO getDefaultAddress(Integer customerId);

}
