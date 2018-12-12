package com.jiazhe.youxiang.server.dto.rechargecard.rcexchangerecord;

import com.jiazhe.youxiang.server.vo.BaseObject;

import java.util.Date;

/**
 * @author TU
 * @description
 * @date 2018/10/24.
 */
public class RCExchangeRecordDTO extends BaseObject {

    private Integer id;

    private Integer rechargeCardId;

    private Integer exchangeCodeId;

    private Integer exchangeType;

    private Integer operatorId;

    private String operatorName;

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

    public Integer getRechargeCardId() {
        return rechargeCardId;
    }

    public void setRechargeCardId(Integer rechargeCardId) {
        this.rechargeCardId = rechargeCardId;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }
}
