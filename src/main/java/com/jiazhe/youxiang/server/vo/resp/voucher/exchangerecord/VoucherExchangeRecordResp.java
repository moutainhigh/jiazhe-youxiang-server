package com.jiazhe.youxiang.server.vo.resp.voucher.exchangerecord;

import com.jiazhe.youxiang.server.vo.BaseObject;
import com.jiazhe.youxiang.server.vo.BaseVO;

import java.util.Date;

/**
 * @author TU
 * @description
 * @date 2018/10/24.
 */
public class VoucherExchangeRecordResp extends BaseVO{

    private Integer id;

    private Integer voucherId;

    private Integer exchangeCodeId;

    private Integer exchangeType;

    private String operatorName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Integer voucherId) {
        this.voucherId = voucherId;
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
}