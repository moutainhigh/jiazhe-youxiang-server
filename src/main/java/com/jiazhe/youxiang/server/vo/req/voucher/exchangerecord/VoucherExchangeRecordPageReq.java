package com.jiazhe.youxiang.server.vo.req.voucher.exchangerecord;

import com.jiazhe.youxiang.server.vo.req.PageSizeNumReq;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author TU
 * @description
 * @date 2018/10/24.
 */
public class VoucherExchangeRecordPageReq extends PageSizeNumReq{

    @ApiModelProperty("兑换起始时间")
    private Long beginDate;

    @ApiModelProperty("兑换结束时间")
    private Long endDate;

    @ApiModelProperty("兑换码")
    private String code;

    @ApiModelProperty("兑换密钥")
    private String keyt;

    public Long getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Long beginDate) {
        this.beginDate = beginDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
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
