/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.resp.boc;

import com.jiazhe.youxiang.server.vo.BaseVO;

/**
 * 中行储蓄卡通用Resp
 *
 * @author niexiao
 * @created 2019-09-09
 */
public class BOCDCCommonResp extends BaseVO {

    private static final long serialVersionUID = -5437589913387189193L;

    /**
     * 处理结果（0000代表成功）
     */
    private String bizCode;

    /**
     * 信息描述
     */
    private String bizDesc;

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getBizDesc() {
        return bizDesc;
    }

    public void setBizDesc(String bizDesc) {
        this.bizDesc = bizDesc;
    }
}