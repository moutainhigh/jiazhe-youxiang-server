/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.req.boc;

import com.jiazhe.youxiang.server.vo.BaseVO;

/**
 * 中行储蓄卡通用请求参数
 *
 * @author niexiao
 * @created 2019-09-09
 */
public class BOCDCCommonReq extends BaseVO {

    private static final long serialVersionUID = -908433517209217173L;
    /**
     * 由请求信息字段组成的XML格式字符串param，其中关键信息由RSA-1024加密（具体加密参数详见各接口）
     */
    private String param;

    /**
     * 将各接口中的请求参数（加密之后）按照顺序生成待签名字符串，使用SHA-256算法生成sign签名
     */
    private String sign;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}