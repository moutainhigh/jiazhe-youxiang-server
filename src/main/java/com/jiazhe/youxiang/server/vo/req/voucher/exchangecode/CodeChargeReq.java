package com.jiazhe.youxiang.server.vo.req.voucher.exchangecode;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author TU
 * @description
 * @date 2018/10/24.
 */
public class CodeChargeReq extends BaseVO{

    @ApiModelProperty("密钥")
    String keyt ;

    @ApiModelProperty("用户手机号")
    Integer customerId;

    public String getKeyt() {
        return keyt;
    }

    public void setKeyt(String keyt) {
        this.keyt = keyt;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
