package com.jiazhe.youxiang.server.vo.req.login;

import com.jiazhe.youxiang.server.vo.BaseVO;

/**
 * @author TU
 * @description
 * @date 2018/10/29.
 */
public class SendMsgReq extends BaseVO {

    private String loginname;

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }
}
