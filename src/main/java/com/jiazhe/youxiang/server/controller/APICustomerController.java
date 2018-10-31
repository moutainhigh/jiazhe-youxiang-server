/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.controller;

import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.server.adapter.CustomerAdapter;
import com.jiazhe.youxiang.server.biz.CustomerBiz;
import com.jiazhe.youxiang.server.common.enums.CustomerCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.CustomerException;
import com.jiazhe.youxiang.server.dto.customer.CustomerAddDTO;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.dto.customer.CustomerUpdateDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.customer.CustomerAddReq;
import com.jiazhe.youxiang.server.vo.req.customer.CustomerListReq;
import com.jiazhe.youxiang.server.vo.req.customer.CustomerUpdateReq;
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
 * 用户信息Controller
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
        Paging paging = new Paging();
        paging.setOffset(req.getOffset());
        paging.setLimit(req.getLimit());
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


}