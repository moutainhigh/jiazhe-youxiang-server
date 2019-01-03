/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jiazhe.youxiang.server.adapter.CustomerAdapter;
import com.jiazhe.youxiang.server.biz.CustomerBiz;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.CityCodeEnum;
import com.jiazhe.youxiang.server.common.enums.CustomerCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.CustomerException;
import com.jiazhe.youxiang.server.dao.mapper.CustomerAddressPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.CustomerPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.SysCityPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.CustomerAddressPOManualMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.CustomerPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.CustomerAddressPO;
import com.jiazhe.youxiang.server.domain.po.CustomerAddressPOExample;
import com.jiazhe.youxiang.server.domain.po.CustomerPO;
import com.jiazhe.youxiang.server.domain.po.CustomerPOExample;
import com.jiazhe.youxiang.server.domain.po.SysCityPO;
import com.jiazhe.youxiang.server.domain.po.SysCityPOExample;
import com.jiazhe.youxiang.server.dto.customer.AddressAddDTO;
import com.jiazhe.youxiang.server.dto.customer.AddressDTO;
import com.jiazhe.youxiang.server.dto.customer.AddressUpdateDTO;
import com.jiazhe.youxiang.server.dto.customer.CustomerAddDTO;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.dto.customer.CustomerUpdateDTO;
import com.jiazhe.youxiang.server.service.CustomerService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

/**
 * 客户管理ServiceImpl
 *
 * @author niexiao
 * @created 2018/10/31
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerPOMapper customerPOMapper;

    @Autowired
    private CustomerPOManualMapper customerPOManualMapper;

    @Autowired
    private CustomerAddressPOMapper customerAddressPOMapper;

    @Autowired
    private CustomerAddressPOManualMapper customerAddressPOManualMapper;

    @Autowired
    private SysCityPOMapper sysCityPOMapper;


    @Override
    public void add(CustomerAddDTO customerAddDTO) {
        Integer count = customerPOManualMapper.count(customerAddDTO.getMobile(), null);
        if (count > 0) {
            throw new CustomerException(CustomerCodeEnum.CUSTOMER_MOBILE_REPEAT);
        }
        CustomerPO customerPO = CustomerAdapter.customerAddDTO2PO(customerAddDTO);
        customerPOMapper.insertSelective(customerPO);
    }

    @Override
    public CustomerDTO getById(Integer id) {
        CustomerPO customerPO = customerPOMapper.selectByPrimaryKey(id);
        CustomerDTO customerDTO = CustomerAdapter.customerPO2DTO(customerPO);
        if (!CustomerBiz.CODE_CUSTOMER_NO_DEFAULT_ADDRESS.equals(customerPO.getDefaultAddressId())) {
            CustomerAddressPO customerAddressPO = customerAddressPOMapper.selectByPrimaryKey(customerPO.getDefaultAddressId());
            customerDTO.setDefaultAddress(CustomerAdapter.addressPO2DTO(customerAddressPO));
        }
        return customerDTO;
    }

    @Override
    public CustomerDTO getByMobile(String mobile) {
        CustomerPOExample customerPOExample = new CustomerPOExample();
        CustomerPOExample.Criteria criteria = customerPOExample.createCriteria();
        criteria.andMobileEqualTo(mobile);
        criteria.andIsDeletedEqualTo(CommonConstant.CODE_NOT_DELETED);
        List<CustomerPO> customerPOList = customerPOMapper.selectByExample(customerPOExample);
        if (CollectionUtils.isEmpty(customerPOList)) {
            return null;
        }
        if (customerPOList.size() != 1) {
            LOGGER.error("发现客户手机号重复:{}", mobile);
            throw new CustomerException(CustomerCodeEnum.CUSTOMER_MOBILE_REPEAT);
        } else {
            CustomerPO customerPO = customerPOList.get(0);
            CustomerDTO customerDTO = CustomerAdapter.customerPO2DTO(customerPO);
            if (!CustomerBiz.CODE_CUSTOMER_NO_DEFAULT_ADDRESS.equals(customerPO.getDefaultAddressId())) {
                CustomerAddressPO customerAddressPO = customerAddressPOMapper.selectByPrimaryKey(customerPO.getDefaultAddressId());
                customerDTO.setDefaultAddress(CustomerAdapter.addressPO2DTO(customerAddressPO));
            }
            return customerDTO;
        }
    }

    @Override
    public List<CustomerDTO> getList(String mobile, String name, Paging paging) {
        Integer count = customerPOManualMapper.count(mobile, name);
        List<CustomerPO> customerPOList = customerPOManualMapper.query(mobile, name, paging.getOffset(), paging.getLimit());
        paging.setTotal(count);

        Map<Integer, AddressDTO> defaultAddressMap = getDefaultAddressMap(customerPOList);
        List<CustomerDTO> result = customerPOList.stream().map(CustomerAdapter::customerPO2DTO).collect(Collectors.toList());
        result.forEach(item -> {
            item.setDefaultAddress(defaultAddressMap.get(item.getId()));
        });
        return result;
    }

    private Map<Integer, AddressDTO> getDefaultAddressMap(List<CustomerPO> customerPOList) {
        Map<Integer, AddressDTO> result = Maps.newHashMap();
        List<Integer> addressIdList = Lists.newArrayList();
        customerPOList.stream().forEach(item -> {
            if (item.getDefaultAddressId() != null && item.getDefaultAddressId().byteValue() != CustomerBiz.CODE_CUSTOMER_NO_DEFAULT_ADDRESS) {
                addressIdList.add(item.getDefaultAddressId());
            }
        });
        if (CollectionUtils.isNotEmpty(addressIdList)) {
            CustomerAddressPOExample customerAddressPOExample = new CustomerAddressPOExample();
            CustomerAddressPOExample.Criteria criteria = customerAddressPOExample.createCriteria();
            criteria.andIdIn(addressIdList);
            criteria.andIsDeletedEqualTo(CommonConstant.CODE_NOT_DELETED);
            List<CustomerAddressPO> customerAddressPOList = customerAddressPOMapper.selectByExample(customerAddressPOExample);
            Map<Integer, CustomerAddressPO> customerAddressPOMap = customerAddressPOList.stream().collect(toMap(CustomerAddressPO::getCustomerId, Function.identity()));
            customerAddressPOMap.keySet().stream().forEach(key -> {
                result.put(key, CustomerAdapter.addressPO2DTO(customerAddressPOMap.get(key)));
            });
        }
        return result;
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

    /********************客户地址相关***********************/

    @Transactional
    @Override
    public void addAddress(AddressAddDTO addressAddDTO) {
        CustomerAddressPO customerAddressPO = CustomerAdapter.addressAddDTO2PO(addressAddDTO);
        //查询CityCode所对应城市
        SysCityPOExample sysCityPOExample = new SysCityPOExample();
        SysCityPOExample.Criteria criteria = sysCityPOExample.createCriteria();
        criteria.andStatusEqualTo(CommonConstant.CODE_CITY_OPEN);
        criteria.andIsDeletedEqualTo(CommonConstant.CODE_NOT_DELETED);
        criteria.andCityCodeEqualTo(addressAddDTO.getCityCode());
        List<SysCityPO> sysCityPOList = sysCityPOMapper.selectByExample(sysCityPOExample);
        if (CollectionUtils.isEmpty(sysCityPOList) || sysCityPOList.size() > 1) {
            throw new CustomerException(CustomerCodeEnum.CITY_CODE_ERROR);
        }
        String cityName = sysCityPOList.get(0).getCityName();
        customerAddressPO.setCityName(cityName);
        customerAddressPOManualMapper.insertSelectiveGetID(customerAddressPO);
        if (customerAddressPO != null && addressAddDTO.getIsDefault() != null) {
            setAddressDefault(customerAddressPO.getCustomerId(), customerAddressPO.getId(), addressAddDTO.getIsDefault());
        }
    }


    @Override
    public AddressDTO getAddressById(Integer id) {
        AddressDTO addressDTO = CustomerAdapter.addressPO2DTO(customerAddressPOMapper.selectByPrimaryKey(id));
        if (addressDTO != null) {
            CustomerPO customerPO = customerPOMapper.selectByPrimaryKey(addressDTO.getCustomerId());
            if (customerPO == null) {
                throw new CustomerException(CustomerCodeEnum.CUSTOMER_INEXISTENCE);
            }
            setIsDefault(customerPO, addressDTO);
        }
        return addressDTO;
    }

    @Override
    public AddressDTO getDefaultAddress(Integer customerId) {
        CustomerPO customerPO = customerPOMapper.selectByPrimaryKey(customerId);
        if (customerPO == null) {
            throw new CustomerException(CustomerCodeEnum.CUSTOMER_INEXISTENCE);
        }
        if (customerPO.getDefaultAddressId() == null) {
            throw new CustomerException(CustomerCodeEnum.CUSTOMER_HAS_NOT_DEFAULT_ADDRESS);
        }
        AddressDTO addressDTO = CustomerAdapter.addressPO2DTO(customerAddressPOMapper.selectByPrimaryKey(customerPO.getDefaultAddressId()));
        if (addressDTO == null) {
            LOGGER.error("getDefaultAddress addressDTO不存在，addressId:{}", customerPO.getDefaultAddressId());
            throw new CustomerException(CustomerCodeEnum.CUSTOMER_HAS_NOT_DEFAULT_ADDRESS);
        }
        return addressDTO;
    }

    @Override
    public List<AddressDTO> getAddressList(Integer customerId, Paging paging) {
        CustomerPO customerPO = customerPOMapper.selectByPrimaryKey(customerId);
        if (customerPO == null) {
            throw new CustomerException(CustomerCodeEnum.CUSTOMER_INEXISTENCE);
        }
        Integer count = customerAddressPOManualMapper.count(customerId);
        List<CustomerAddressPO> addressPOList = customerAddressPOManualMapper.query(customerId, paging.getOffset(), paging.getLimit());
        paging.setTotal(count);
        List<AddressDTO> result = addressPOList.stream().map(CustomerAdapter::addressPO2DTO).collect(Collectors.toList());
        result.forEach(item -> {
            setIsDefault(customerPO, item);
        });
        return result;
    }

    @Transactional
    @Override
    public void setAddressDefault(Integer id, Integer isDefault) {
        AddressDTO addressDTO = getAddressById(id);
        if (addressDTO == null) {
            throw new CustomerException(CustomerCodeEnum.CUSTOMER_ADDRESS_INEXISTENCE);
        }
        setAddressDefault(addressDTO.getCustomerId(), id, isDefault);
    }

    private void setAddressDefault(Integer customerId, Integer addressId, Integer isDefault) {
        CustomerPO customerPO = customerPOMapper.selectByPrimaryKey(customerId);
        if (customerPO == null) {
            throw new CustomerException(CustomerCodeEnum.CUSTOMER_INEXISTENCE);
        }
        if (CustomerBiz.CODE_ADDRESS_IS_DEFAULT.equals(isDefault)) {
            customerPO.setDefaultAddressId(addressId);
        } else {
            //如果该地址是默认地址，则修改客户信息的默认地址记录，
            if (addressId.equals(customerPO.getDefaultAddressId())) {
                customerPO.setDefaultAddressId(CustomerBiz.CODE_CUSTOMER_NO_DEFAULT_ADDRESS);
            } else {
                //否则什么也不做,马上返回
                return;
            }
        }
        customerPO.setModTime(new Date());
        customerPOMapper.updateByPrimaryKeySelective(customerPO);
    }

    @Transactional
    @Override
    public void deleteAddress(Integer id) {
        //删除地址前先将默认地址信息清空
        setAddressDefault(id, CustomerBiz.CODE_ADDRESS_IS_NOT_DEFAULT);
        CustomerAddressPO record = new CustomerAddressPO();
        record.setId(id);
        record.setModTime(new Date());
        record.setIsDeleted(CommonConstant.CODE_DELETED);
        customerAddressPOMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional
    @Override
    public void updateAddress(AddressUpdateDTO addressUpdateDTO) {
        CustomerAddressPO customerAddressPO = CustomerAdapter.addressUpdateDTO2PO(addressUpdateDTO);
        customerAddressPO.setModTime(new Date());
        customerAddressPOMapper.updateByPrimaryKeySelective(customerAddressPO);
        setAddressDefault(addressUpdateDTO.getId(), addressUpdateDTO.getIsDefault());
    }


    private void setIsDefault(CustomerPO customerPO, AddressDTO addressDTO) {
        if (addressDTO.getId().equals(customerPO.getDefaultAddressId())) {
            addressDTO.setIsDefault(CustomerBiz.CODE_ADDRESS_IS_DEFAULT);
        } else {
            addressDTO.setIsDefault(CustomerBiz.CODE_ADDRESS_IS_NOT_DEFAULT);
        }
    }
}