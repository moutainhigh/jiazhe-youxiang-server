/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo;

import com.jiazhe.youxiang.server.common.enums.CommonCodeEnum;

import java.util.List;

/**
 * api返回结果的工厂类
 *
 * @author niexiao
 * @created 2018/10/16
 */
public class ResponseFactory<T> {

    private static final Integer DEFAULT_SUCCESS_CODE = CommonCodeEnum.SUCCESS.getCode();
    private static final String DEFAULT_OPERATION_SUCCESS_TYPE = CommonCodeEnum.SUCCESS.getType();
    private static final String DEFAULT_OPERATION_SUCCESS_MESSAGE = CommonCodeEnum.SUCCESS.getMessage();
    private static final Response<ResponseMsg> DEFAULT_SUCCESS_RESPONSE = new Response<ResponseMsg>(new ResponseMsg(DEFAULT_SUCCESS_CODE, DEFAULT_OPERATION_SUCCESS_TYPE, DEFAULT_OPERATION_SUCCESS_MESSAGE));

    private static final String DEFAULT_FAIL_TYPE = CommonCodeEnum.INTERNAL_ERROR.getType();
    private static final Integer DEFAULT_FAIL_CODE = CommonCodeEnum.INTERNAL_ERROR.getCode();
    private static final String DEFAULT_SYSTEM_FAIL_MESSAGE = CommonCodeEnum.INTERNAL_ERROR.getMessage();

    public static final Response<ResponseMsg> buildSuccess(Integer code, String type, String message) {
        return new Response<ResponseMsg>(new ResponseMsg(code, type, message));
    }

    public static final Response<ResponseMsg> buildSuccess(Integer code, String message) {
        return new Response<ResponseMsg>(new ResponseMsg(code, DEFAULT_OPERATION_SUCCESS_TYPE, message));
    }

    public static final Response<ResponseMsg> buildSuccess(String message) {
        return new Response<ResponseMsg>(new ResponseMsg(DEFAULT_SUCCESS_CODE, DEFAULT_OPERATION_SUCCESS_TYPE, message));
    }

    public static final Response<ResponseMsg> buildSuccess() {
        return DEFAULT_SUCCESS_RESPONSE;
    }

    public static final FailResponse buildFailure(Integer code, String type, String message) {
        return new FailResponse(code, type, message);
    }


    public static final FailResponse buildFailure(Integer code, String message) {
        return new FailResponse(code, DEFAULT_FAIL_TYPE, message);
    }

    public static final <T> Response<T> buildResponse(T vo) {
        return new Response(vo);
    }


    public static final <T> PaginationResponse<T> buildPaginationResponse(List<T> data, Integer offset, Integer limit, Integer total, Boolean hasMore) {
        return new PaginationResponse(data, offset, limit, total, hasMore);
    }


    public static final <T> PaginationResponse<T> buildPaginationResponse(List<T> data, Paging paging) {
        return new PaginationResponse(data, paging);
    }
}