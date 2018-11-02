/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.biz;

import com.jiazhe.youxiang.server.dto.customer.AddressAddDTO;
import com.jiazhe.youxiang.server.dto.customer.AddressDTO;
import com.jiazhe.youxiang.server.dto.customer.AddressUpdateDTO;
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

    /**
     * 默认地址代码
     */
    public static final Integer CODE_ADDRESS_IS_DEFAULT = Integer.valueOf(1);
    /**
     * 非地址代码
     */
    public static final Integer CODE_ADDRESS_IS_NOT_DEFAULT = Integer.valueOf(0);

    /**
     * 没有默认地址的代码
     */
    public static final Integer CODE_CUSTOMER_NO_DEFAULT_ADDRESS = Integer.valueOf(0);

    /********************客户相关***********************/

    public void add(CustomerAddDTO customerAddDTO) {
        customerService.add(customerAddDTO);
    }

    public CustomerDTO getById(Integer id) {
        return customerService.getById(id);
    }
    /**
     * 根据客户手机号获得客户信息
     * @param mobile
     * @return
     */
    public CustomerDTO getByMobile(String mobile) {
        return customerService.getByMobile(mobile);
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


    /********************客户地址相关***********************/

    public void addAddress(AddressAddDTO addressAddDTO) {
        customerService.addAddress(addressAddDTO);

    }

    public AddressDTO getAddressById(Integer id) {
        return customerService.getAddressById(id);

    }

    public List<AddressDTO> getAddressList(Integer customerId,Paging paging) {
        return customerService.getAddressList(customerId, paging);
    }

    public void updateAddress(AddressUpdateDTO addressUpdateDTO) {
        customerService.updateAddress(addressUpdateDTO);

    }

    public void deleteAddress(Integer id) {
        customerService.deleteAddress(id);
    }

    public void setAddressDefault(Integer id, Integer isDefault) {
        customerService.setAddressDefault(id, isDefault);

    }
}