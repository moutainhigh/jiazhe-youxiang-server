/*
 * Copyright (c) 2019 橙谊科技
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.req.boc;

import com.jiazhe.youxiang.server.vo.BaseVO;

/**
 * @author tu
 * @version 1.0
 * @description 第三方请求中行参数格式
 * @created 2019-09-16 21:03
 */
public class BOCCCReq extends BaseVO {

    /**
     * 请求类型  S-已使用 R-退货更新
     */
    private String requestType;

    /**
     * 请求参数加密后的字符串
     */
    private String data;

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
