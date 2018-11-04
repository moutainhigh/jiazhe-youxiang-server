package com.jiazhe.youxiang.server.vo.req.voucher.exchangecode;

import com.jiazhe.youxiang.server.vo.req.PageSizeNumReq;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author TU
 * @description
 * @date 2018/10/24.
 */
public class VoucherExchangeCodePageReq extends PageSizeNumReq {

    @ApiModelProperty("批次id")
    private Integer batchId;

    @ApiModelProperty("启用停用状态")
    private Byte status;

    @ApiModelProperty("使用状态")
    private Byte used;

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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getUsed() {
        return used;
    }

    public void setUsed(Byte used) {
        this.used = used;
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
