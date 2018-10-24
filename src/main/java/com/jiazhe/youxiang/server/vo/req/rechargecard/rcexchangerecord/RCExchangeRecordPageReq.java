package com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangerecord;

import com.jiazhe.youxiang.server.vo.req.PageSizeNumReq;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author TU
 * @description 组合条件查询充值卡兑换记录
 * @date 2018/10/24.
 */
public class RCExchangeRecordPageReq extends PageSizeNumReq {

    @ApiModelProperty("充值起始时间")
    private Date beginDate;

    @ApiModelProperty("充值结束时间")
    private Date endDate;

    @ApiModelProperty("兑换码")
    private String code;

    @ApiModelProperty("密钥")
    private String keyt;

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
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
