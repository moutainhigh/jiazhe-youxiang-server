package com.jiazhe.youxiang.server.vo.resp.rechargecard.rcexchangerecord;

import com.jiazhe.youxiang.server.vo.BaseVO;

import java.util.Date;

/**
 * @author TU
 * @description
 * @date 2018/10/24.
 */
public class RCExchangeRecordListResp extends BaseVO{

    private Integer id;

    private Integer exchangeCodeId;

    private Integer exchangeType;

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
}
