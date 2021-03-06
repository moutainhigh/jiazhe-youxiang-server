/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.adapter;

import com.jiazhe.youxiang.server.dto.wechatpublic.SignatureDTO;
import com.jiazhe.youxiang.server.vo.resp.wechatpublic.SignatureResp;

/**
 * 微信公众号相关Adapter
 *
 * @author niexiao
 * @created 2019/1/9
 */
public class WeChatPublicAdapter {


    public static SignatureResp SignatureDTO2VO(SignatureDTO dto) {
        if (dto == null) {
            return null;
        }
        SignatureResp signatureResp = new SignatureResp();
        signatureResp.setSignature(dto.getSignature());
        signatureResp.setAppid(dto.getAppid());
        signatureResp.setTimestamp(dto.getTimestamp());
        signatureResp.setNonceStr(dto.getNonceStr());
        signatureResp.setUrl(dto.getUrl());
        signatureResp.setAccessToken(dto.getAccessToken());
        signatureResp.setJsapiTicket(dto.getJsapiTicket());
        return signatureResp;
    }
}