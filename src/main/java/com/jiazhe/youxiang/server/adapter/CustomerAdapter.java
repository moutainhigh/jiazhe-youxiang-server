/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.adapter;

import com.jiazhe.youxiang.server.dto.customer.CustomerAddDTO;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.vo.req.customer.CustomerAddReq;
import com.jiazhe.youxiang.server.vo.req.customer.CustomerUpdateReq;
import com.jiazhe.youxiang.server.vo.resp.customer.CustomerResp;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/23
 */
public class CustomerAdapter {
    public static CustomerAddDTO customerAddReq2DTO(CustomerAddReq req) {
        if (req == null) {
            return null;
        }
        CustomerAddDTO customerAddDTO = new CustomerAddDTO();
        customerAddDTO.setMobile(req.getMobile());
        customerAddDTO.setName(req.getName());
        customerAddDTO.setRemark(req.getRemark());
        return customerAddDTO;
    }

    public static CustomerResp customerDTO2VO(CustomerDTO dto) {
        if (dto == null) {
            return null;
        }
        CustomerResp customerResp = new CustomerResp();
        customerResp.setId(dto.getId());
        customerResp.setMobile(dto.getMobile());
        customerResp.setName(dto.getName());
        customerResp.setRemark(dto.getRemark());
        customerResp.setDefaultAddressId(dto.getDefaultAddressId());
        return customerResp;
    }

    public static CustomerDTO customerUpdateReq2DTO(CustomerUpdateReq req) {
        if (req == null) {
            return null;
        }
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName(req.getName());
        customerDTO.setRemark(req.getRemark());
        customerDTO.setId(req.getId());
        return customerDTO;
    }
}