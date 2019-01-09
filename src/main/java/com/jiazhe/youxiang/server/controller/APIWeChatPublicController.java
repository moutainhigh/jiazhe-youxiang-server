/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.controller;

import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.server.adapter.WeChatPublicAdapter;
import com.jiazhe.youxiang.server.biz.WeChatPublicBiz;
import com.jiazhe.youxiang.server.common.annotation.AppApi;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.common.enums.WeChatPublicCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.WeChatPublicException;
import com.jiazhe.youxiang.server.dto.wechatpublic.SignatureDTO;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.wechatpublic.CheckSignatureReq;
import com.jiazhe.youxiang.server.vo.req.wechatpublic.SignatureReq;
import com.jiazhe.youxiang.server.vo.resp.wechatpublic.CheckSignatureResp;
import com.jiazhe.youxiang.server.vo.resp.wechatpublic.SignatureResp;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信相关Controller
 *
 * @author niexiao
 * @created 2019/1/8
 */
@RestController
@RequestMapping("api/wechatpublic")
public class APIWeChatPublicController {

    private static final Logger LOGGER = LoggerFactory.getLogger(APIWeChatPublicController.class);

    @Value("${clientKey}")
    private String CLIENT_KEY;

    @Autowired
    private WeChatPublicBiz weChatPublicBiz;

    /**
     * 获得签名
     *
     * @return
     */
    @AppApi
    @ApiOperation(value = "获得签名", httpMethod = "GET", response = SignatureResp.class, notes = "获得签名")
    @RequestMapping(value = "getsignature", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.WECHAT_PUBLIC, operate = "获得签名", level = LogLevelEnum.LEVEL_2)
    public Object getSignature(@ModelAttribute SignatureReq req) {
        CommonValidator.validateNull(req);
        if (Strings.isBlank(req.getClientKey()) || !req.getClientKey().equals(CLIENT_KEY)) {
            throw new WeChatPublicException(WeChatPublicCodeEnum.CLIENT_KEY_ERROR);
        }
        if (Strings.isBlank(req.getTimestamp())) {
            throw new WeChatPublicException(WeChatPublicCodeEnum.TIMESTAMP_IS_NULL);
        }
        if (Strings.isBlank(req.getNonceStr())) {
            throw new WeChatPublicException(WeChatPublicCodeEnum.NONCE_STR_IS_NULL);
        }
        if (Strings.isBlank(req.getUrl())) {
            throw new WeChatPublicException(WeChatPublicCodeEnum.NONCE_STR_IS_NULL);
        }
        //调用BIZ方法
        SignatureDTO dto = weChatPublicBiz.getSignature(req.getTimestamp(), req.getNonceStr(), req.getUrl());

        //用ResponseFactory将返回值包装
        return ResponseFactory.buildResponse(WeChatPublicAdapter.SignatureDTO2VO(dto));
    }

    /**
     * 验证签名
     *
     * @return
     */
    @AppApi
    @ApiOperation(value = "验证签名", httpMethod = "GET", response = CheckSignatureResp.class, notes = "验证签名")
    @RequestMapping(value = "checkSignature", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.WECHAT_PUBLIC, operate = "验证签名", level = LogLevelEnum.LEVEL_2)
    public Object checkSignature(@ModelAttribute CheckSignatureReq req) {
        CommonValidator.validateNull(req);
        if (Strings.isBlank(req.getSignature())) {
            throw new WeChatPublicException(WeChatPublicCodeEnum.SIGNATURE_IS_NULL);
        }
        if (Strings.isBlank(req.getTimestamp())) {
            throw new WeChatPublicException(WeChatPublicCodeEnum.TIMESTAMP_IS_NULL);
        }
        if (Strings.isBlank(req.getNonce())) {
            throw new WeChatPublicException(WeChatPublicCodeEnum.NONCE_STR_IS_NULL);
        }
        if (Strings.isBlank(req.getEchostr())) {
            throw new WeChatPublicException(WeChatPublicCodeEnum.NONCE_STR_IS_NULL);
        }
        //调用BIZ方法
        boolean success = weChatPublicBiz.checkSignature(req.getSignature(), req.getTimestamp(), req.getNonce());
        if (success) {
            //特殊接口，不用包装
            return req.getEchostr();
        }
        return "";
    }
}