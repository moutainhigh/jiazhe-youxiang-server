package com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangecode;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author TU
 * @description
 * @date 2018/10/23.
 */
public class CodeChargeReq extends BaseVO {

    @ApiModelProperty("密钥")
    String keyt ;

    @ApiModelProperty("客户手机号")
    String mobile;

    public String getKeyt() {
        return keyt;
    }

    public void setKeyt(String keyt) {
        this.keyt = keyt;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
