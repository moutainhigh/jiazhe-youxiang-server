package com.jiazhe.youxiang.server.vo.resp.login;

import com.jiazhe.youxiang.server.vo.BaseVO;

/**
 * @author TU
 * @description  用于发送短信成功，返回的短信凭证bizId
 * @date 2018/10/29.
 */
public class SendMsgResp extends BaseVO {

    private String bizId;

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }
}
