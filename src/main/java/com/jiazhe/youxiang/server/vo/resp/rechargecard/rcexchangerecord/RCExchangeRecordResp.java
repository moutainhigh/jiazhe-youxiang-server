package com.jiazhe.youxiang.server.vo.resp.rechargecard.rcexchangerecord;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author TU
 * @description
 * @date 2018/10/24.
 */
public class RCExchangeRecordResp extends BaseVO{

    private static final long serialVersionUID = 1698163218039110811L;

    @ApiModelProperty("兑换记录id")
    private Integer id;

    @ApiModelProperty("兑换码id")
    private Integer exchangeCodeId;

    @ApiModelProperty("兑换类型，0-后台兑换，1-客户兑换，2-直接充值")
    private Integer exchangeType;

    @ApiModelProperty("操作人姓名")
    private String operatorName;

    @ApiModelProperty("兑换记录生成时间")
    private Date addTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExchangeCodeId() {
        return exchangeCodeId;
    }

    public void setExchangeCodeId(Integer exchangeCodeId) {
        this.exchangeCodeId = exchangeCodeId;
    }

    public Integer getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(Integer exchangeType) {
        this.exchangeType = exchangeType;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
