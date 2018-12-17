/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.adapter;

import com.jiazhe.youxiang.server.domain.po.CustomerAddressPO;
import com.jiazhe.youxiang.server.domain.po.CustomerPO;
import com.jiazhe.youxiang.server.dto.customer.AddressAddDTO;
import com.jiazhe.youxiang.server.dto.customer.AddressDTO;
import com.jiazhe.youxiang.server.dto.customer.AddressUpdateDTO;
import com.jiazhe.youxiang.server.dto.customer.CustomerAddDTO;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.dto.customer.CustomerRegisterDTO;
import com.jiazhe.youxiang.server.dto.customer.CustomerUpdateDTO;
import com.jiazhe.youxiang.server.vo.req.customer.AddressAddReq;
import com.jiazhe.youxiang.server.vo.req.customer.AddressUpdateReq;
import com.jiazhe.youxiang.server.vo.req.customer.CustomerAddReq;
import com.jiazhe.youxiang.server.vo.req.customer.CustomerRegisterReq;
import com.jiazhe.youxiang.server.vo.req.customer.CustomerUpdateReq;
import com.jiazhe.youxiang.server.vo.resp.customer.AddressResp;
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
        customerResp.setPointBalance(dto.getPointBalance());
        customerResp.setRechargeCardBalance(dto.getRechargeCardBalance());
        customerResp.setVoucherCount(dto.getVoucherCount());
        customerResp.setRemark(dto.getRemark());
        customerResp.setDefaultAddress(addressDTO2VO(dto.getDefaultAddress()));
        return customerResp;
    }

    public static CustomerUpdateDTO customerUpdateReq2DTO(CustomerUpdateReq req) {
        if (req == null) {
            return null;
        }
        CustomerUpdateDTO customerUpdateDTO = new CustomerUpdateDTO();
        customerUpdateDTO.setName(req.getName());
        customerUpdateDTO.setRemark(req.getRemark());
        customerUpdateDTO.setId(req.getId());
        return customerUpdateDTO;
    }

    public static CustomerPO customerAddDTO2PO(CustomerAddDTO customerAddDTO) {
        if (customerAddDTO == null) {
            return null;
        }
        CustomerPO customerPO = new CustomerPO();
        customerPO.setMobile(customerAddDTO.getMobile());
        customerPO.setName(customerAddDTO.getName());
        customerPO.setRemark(customerAddDTO.getRemark());
        return customerPO;
    }

    public static CustomerDTO customerPO2DTO(CustomerPO customerPO) {
        if (customerPO == null) {
            return null;
        }
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customerPO.getId());
        customerDTO.setMobile(customerPO.getMobile());
        customerDTO.setName(customerPO.getName());
        customerDTO.setRemark(customerPO.getRemark());
        return customerDTO;
    }

    public static CustomerPO customerUpdateDTO2PO(CustomerUpdateDTO customerUpdateDTO) {
        if (customerUpdateDTO == null) {
            return null;
        }
        CustomerPO customerPO = new CustomerPO();
        customerPO.setId(customerUpdateDTO.getId());
        customerPO.setName(customerUpdateDTO.getName());
        customerPO.setRemark(customerUpdateDTO.getRemark());
        return customerPO;
    }

    public static AddressAddDTO AddressAddReq2DTO(AddressAddReq req) {
        if (req == null) {
            return null;
        }
        AddressAddDTO addressAddDTO = new AddressAddDTO();
        addressAddDTO.setCustomerId(req.getCustomerId());
        addressAddDTO.setGender(req.getGender());
        addressAddDTO.setCityCode(req.getCityCode());
        addressAddDTO.setAddress(req.getAddress());
        addressAddDTO.setMobile(req.getMobile());
        addressAddDTO.setName(req.getName());
        addressAddDTO.setRemark(req.getRemark());
        addressAddDTO.setIsDefault(req.getIsDefault());
        return addressAddDTO;
    }

    public static AddressResp addressDTO2VO(AddressDTO dto) {
        if (dto == null) {
            return null;
        }
        AddressResp addressResp = new AddressResp();
        addressResp.setId(dto.getId());
        addressResp.setCustomerId(dto.getCustomerId());
        addressResp.setGender(dto.getGender());
        addressResp.setCityCode(dto.getCityCode());
        addressResp.setCityName(dto.getCityName());
        addressResp.setAddress(dto.getAddress());
        addressResp.setMobile(dto.getMobile());
        addressResp.setName(dto.getName());
        addressResp.setRemark(dto.getRemark());
        addressResp.setIsDefault(dto.getIsDefault());
        return addressResp;
    }

    public static AddressUpdateDTO addressUpdateReq2DTO(AddressUpdateReq req) {
        if (req == null) {
            return null;
        }
        AddressUpdateDTO addressUpdateDTO = new AddressUpdateDTO();
        addressUpdateDTO.setId(req.getId());
        addressUpdateDTO.setGender(req.getGender());
        addressUpdateDTO.setCityCode(req.getCityCode());
        addressUpdateDTO.setAddress(req.getAddress());
        addressUpdateDTO.setMobile(req.getMobile());
        addressUpdateDTO.setName(req.getName());
        addressUpdateDTO.setRemark(req.getRemark());
        addressUpdateDTO.setIsDefault(req.getIsDefault());
        return addressUpdateDTO;
    }

    public static CustomerAddressPO addressAddDTO2PO(AddressAddDTO addressAddDTO) {
        if (addressAddDTO == null) {
            return null;
        }
        CustomerAddressPO customerAddressPO = new CustomerAddressPO();
        customerAddressPO.setCustomerId(addressAddDTO.getCustomerId());
        customerAddressPO.setGender(addressAddDTO.getGender().byteValue());
        customerAddressPO.setCityCode(addressAddDTO.getCityCode());
        customerAddressPO.setAddress(addressAddDTO.getAddress());
        customerAddressPO.setMobile(addressAddDTO.getMobile());
        customerAddressPO.setName(addressAddDTO.getName());
        customerAddressPO.setRemark(addressAddDTO.getRemark());
        return customerAddressPO;
    }

    public static AddressDTO addressPO2DTO(CustomerAddressPO po) {
        if (po == null) {
            return null;
        }
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(po.getId());
        addressDTO.setCustomerId(po.getCustomerId());
        addressDTO.setGender(po.getGender().intValue());
        addressDTO.setCityCode(po.getCityCode());
        addressDTO.setCityName(po.getCityName());
        addressDTO.setAddress(po.getAddress());
        addressDTO.setMobile(po.getMobile());
        addressDTO.setName(po.getName());
        addressDTO.setRemark(po.getRemark());
        return addressDTO;
    }

    public static CustomerAddressPO addressUpdateDTO2PO(AddressUpdateDTO addressUpdateDTO) {
        if (addressUpdateDTO == null) {
            return null;
        }
        CustomerAddressPO customerAddressPO = new CustomerAddressPO();
        customerAddressPO.setId(addressUpdateDTO.getId());
        customerAddressPO.setGender(addressUpdateDTO.getGender().byteValue());
        customerAddressPO.setCityCode(addressUpdateDTO.getCityCode());
        customerAddressPO.setAddress(addressUpdateDTO.getAddress());
        customerAddressPO.setMobile(addressUpdateDTO.getMobile());
        customerAddressPO.setName(addressUpdateDTO.getName());
        customerAddressPO.setRemark(addressUpdateDTO.getRemark());
        return customerAddressPO;
    }

    public static CustomerRegisterDTO CustomerRegisterReq2DTO(CustomerRegisterReq req) {
        if (req == null) {
            return null;
        }
        CustomerRegisterDTO customerRegisterDTO = new CustomerRegisterDTO();
        customerRegisterDTO.setMobile(req.getMobile());
        customerRegisterDTO.setName(req.getName());
        return customerRegisterDTO;
    }
}