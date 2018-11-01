/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.controller;

import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.server.adapter.CustomerAdapter;
import com.jiazhe.youxiang.server.biz.CustomerBiz;
import com.jiazhe.youxiang.server.common.enums.CustomerCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.CustomerException;
import com.jiazhe.youxiang.server.dto.customer.AddressAddDTO;
import com.jiazhe.youxiang.server.dto.customer.AddressDTO;
import com.jiazhe.youxiang.server.dto.customer.AddressUpdateDTO;
import com.jiazhe.youxiang.server.dto.customer.CustomerAddDTO;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.dto.customer.CustomerUpdateDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.customer.AddressAddReq;
import com.jiazhe.youxiang.server.vo.req.customer.AddressSetDefaultReq;
import com.jiazhe.youxiang.server.vo.req.customer.AddressUpdateReq;
import com.jiazhe.youxiang.server.vo.req.customer.CustomerAddReq;
import com.jiazhe.youxiang.server.vo.req.customer.CustomerListReq;
import com.jiazhe.youxiang.server.vo.req.customer.CustomerUpdateReq;
import com.jiazhe.youxiang.server.vo.req.customer.AddressListReq;
import com.jiazhe.youxiang.server.vo.resp.customer.AddressResp;
import com.jiazhe.youxiang.server.vo.resp.customer.CustomerResp;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 客户信息Controller
 *
 * @author niexiao
 * @created 2018/10/23
 */
@RestController
@RequestMapping("api/customer")
public class APICustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(APICustomerController.class);

    @Autowired
    private CustomerBiz customerBiz;

    /********************客户相关***********************/

    /**
     * 添加用户
     *
     * @return
     */
    @ApiOperation(value = "添加用户", httpMethod = "POST", notes = "添加用户")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Object add(@ModelAttribute CustomerAddReq req) {
        CommonValidator.validateNull(req);
        CommonValidator.validateMobile(req.getMobile(), new CustomerException(CustomerCodeEnum.CUSTOMER_MOBILE_ERROR));
        CommonValidator.validateNull(req.getName(), new CustomerException(CustomerCodeEnum.CUSTOMER_NAME_IS_NULL));
        CustomerAddDTO customerAddDTO = CustomerAdapter.customerAddReq2DTO(req);
        //调用BIZ方法
        customerBiz.add(customerAddDTO);
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildSuccess();
    }

    /**
     * 查询某一用户信息
     *
     * @return
     */
    @ApiOperation(value = "查询某一用户信息", httpMethod = "GET", response = CustomerResp.class, notes = "查询某一用户信息")
    @RequestMapping(value = "getbyid", method = RequestMethod.GET)
    public Object getById(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        //调用BIZ方法
        CustomerDTO customerDTO = customerBiz.getById(req.getId());
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildResponse(CustomerAdapter.customerDTO2VO(customerDTO));
    }

    /**
     * 查询客户列表
     *
     * @return
     */
    @ApiOperation(value = "查询客户列表", httpMethod = "GET", response = CustomerResp.class, responseContainer = "List", notes = "查询客户列表")
    @RequestMapping(value = "getlist", method = RequestMethod.GET)
    public Object getList(@ModelAttribute CustomerListReq req) {
        CommonValidator.validatePaging(req);
        Paging paging =  PagingParamUtil.pagingParamSwitch(req);
        //调用BIZ方法
        List<CustomerDTO> customerDTOList = customerBiz.getList(req.getMobile(), req.getName(), paging);
        //将DTO转成VO
        List<CustomerResp> result = customerDTOList.stream().map(CustomerAdapter::customerDTO2VO).collect(Collectors.toList());
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildPaginationResponse(result, paging);
    }

    /**
     * 编辑客户信息
     *
     * @return
     */
    @ApiOperation(value = "编辑客户信息", httpMethod = "POST", notes = "编辑客户信息")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Object update(@ModelAttribute CustomerUpdateReq req) {
        CommonValidator.validateNull(req);
        CommonValidator.validateId(req);
        CommonValidator.validateNull(req.getName(), new CustomerException(CustomerCodeEnum.CUSTOMER_NAME_IS_NULL));
        CustomerUpdateDTO customerUpdateDTO = CustomerAdapter.customerUpdateReq2DTO(req);
        //调用BIZ方法
        customerBiz.update(customerUpdateDTO);
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildSuccess();
    }


    /**
     * 删除客户
     *
     * @return
     */
    @ApiOperation(value = "删除客户", httpMethod = "GET", notes = "删除客户")
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public Object delete(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        //调用BIZ方法
        customerBiz.delete(req.getId());
        return ResponseFactory.buildSuccess();
    }

    /********************客户地址相关***********************/

    /**
     * 添加地址
     *
     * @return
     */
    @ApiOperation(value = "添加地址", httpMethod = "POST", notes = "添加地址")
    @RequestMapping(value = "addaddress", method = RequestMethod.POST)
    public Object addAddress(@ModelAttribute AddressAddReq req) {
        CommonValidator.validateNull(req);
        CommonValidator.validateId(req.getCustomerId(), new CustomerException(CustomerCodeEnum.CUSTOMER_ID_IS_NULL));
        validateGender(req.getGender());
        CommonValidator.validateNull(req.getCityCode(), new CustomerException(CustomerCodeEnum.CUSTOMER_ADDRESS_CITY_CODE_IS_NULL));
        CommonValidator.validateNull(req.getAddress(), new CustomerException(CustomerCodeEnum.CUSTOMER_ADDRESS_IS_NULL));
        CommonValidator.validateMobile(req.getMobile(), new CustomerException(CustomerCodeEnum.CUSTOMER_MOBILE_ERROR));
        CommonValidator.validateNull(req.getName(), new CustomerException(CustomerCodeEnum.CUSTOMER_ADDRESS_NAME_IS_NULL));
        AddressAddDTO addressAddDTO = CustomerAdapter.AddressAddReq2DTO(req);
        //调用BIZ方法
        customerBiz.addAddress(addressAddDTO);
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildSuccess();
    }

    /**
     * 查询某一地址信息
     *
     * @return
     */
    @ApiOperation(value = "查询某一地址信息", httpMethod = "GET", response = AddressResp.class, notes = "查询某一地址信息")
    @RequestMapping(value = "getaddressbyid", method = RequestMethod.GET)
    public Object getAddressById(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        //调用BIZ方法
        AddressDTO addressDTO = customerBiz.getAddressById(req.getId());
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildResponse(CustomerAdapter.addressDTO2VO(addressDTO));
    }


    /**
     * 查询客户的地址列表
     *
     * @return
     */
    @ApiOperation(value = "查询客户的地址列表", httpMethod = "GET", response = AddressResp.class, responseContainer = "List", notes = "查询客户的地址列表")
    @RequestMapping(value = "getaddresslist", method = RequestMethod.GET)
    public Object getAddressList(@ModelAttribute AddressListReq req) {
        CommonValidator.validatePaging(req);
        CommonValidator.validateId(req.getCustomerId(), new CustomerException(CustomerCodeEnum.CUSTOMER_ID_IS_NULL));
        Paging paging =  PagingParamUtil.pagingParamSwitch(req);
        //调用BIZ方法
        List<AddressDTO> addressDTOList = customerBiz.getAddressList(req.getCustomerId(), paging);
        //将DTO转成VO
        List<AddressResp> result = addressDTOList.stream().map(CustomerAdapter::addressDTO2VO).collect(Collectors.toList());
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildPaginationResponse(result, paging);
    }


    /**
     * 编辑地址信息
     *
     * @return
     */
    @ApiOperation(value = "编辑地址信息", httpMethod = "POST", notes = "编辑地址信息")
    @RequestMapping(value = "updateaddress", method = RequestMethod.POST)
    public Object updateAddress(@ModelAttribute AddressUpdateReq req) {
        CommonValidator.validateNull(req);
        CommonValidator.validateId(req.getId());
        validateGender(req.getGender());
        CommonValidator.validateNull(req.getCityCode(), new CustomerException(CustomerCodeEnum.CUSTOMER_ADDRESS_CITY_CODE_IS_NULL));
        CommonValidator.validateNull(req.getAddress(), new CustomerException(CustomerCodeEnum.CUSTOMER_ADDRESS_IS_NULL));
        CommonValidator.validateMobile(req.getMobile(), new CustomerException(CustomerCodeEnum.CUSTOMER_MOBILE_ERROR));
        CommonValidator.validateNull(req.getName(), new CustomerException(CustomerCodeEnum.CUSTOMER_ADDRESS_NAME_IS_NULL));
        AddressUpdateDTO addressUpdateDTO = CustomerAdapter.addressUpdateReq2DTO(req);
        //调用BIZ方法
        customerBiz.updateAddress(addressUpdateDTO);
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildSuccess();
    }

    /**
     * 删除地址信息
     *
     * @return
     */
    @ApiOperation(value = "删除地址信息", httpMethod = "GET", notes = "删除地址信息")
    @RequestMapping(value = "deleteaddress", method = RequestMethod.GET)
    public Object deleteAddress(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        //调用BIZ方法
        customerBiz.deleteAddress(req.getId());
        return ResponseFactory.buildSuccess();
    }

    /**
     * 设置某一地址为默认
     *
     * @return
     */
    @ApiOperation(value = "设置某一地址为默认", httpMethod = "GET", response = AddressResp.class, notes = "设置某一地址为默认")
    @RequestMapping(value = "setaddressdefault", method = RequestMethod.GET)
    public Object setAddressDefault(@ModelAttribute AddressSetDefaultReq req) {
        CommonValidator.validateId(req);
        validateIsDefault(req.getIsDefault());
        //调用BIZ方法
        customerBiz.setAddressDefault(req.getId(), req.getIsDefault());
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildSuccess();
    }

    /********************通用方法***********************/
    private void validateGender(Integer gender) {
        if (null == gender || gender < 0 || gender > 1) {
            throw new CustomerException(CustomerCodeEnum.CUSTOMER_GENDER_ERROR);
        }
    }

    private void validateIsDefault(Integer isDefault) {
        if (null == isDefault || isDefault < 0 || isDefault > 1) {
            throw new CustomerException(CustomerCodeEnum.CUSTOMER_ADDRESS_ISDEFAULT_ERROR);
        }
    }
}