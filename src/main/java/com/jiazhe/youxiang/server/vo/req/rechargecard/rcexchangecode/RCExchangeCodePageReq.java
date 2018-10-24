package com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangecode;

import com.jiazhe.youxiang.server.vo.req.PageSizeNumReq;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author TU
 * @description
 * @date 2018/10/24.
 */
public class RCExchangeCodePageReq extends PageSizeNumReq{

    @ApiModelProperty("批次id")
    private Integer batchId;

    @ApiModelProperty("充值卡兑换码")
    private String code ;

    @ApiModelProperty("充值卡兑换密钥")
    private String keyt;

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getKeyt() {
        return keyt;
    }

    public void setKeyt(String keyt) {
        this.keyt = keyt;
    }
}
