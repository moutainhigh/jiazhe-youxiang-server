package com.jiazhe.youxiang.server.vo.req.rechargecard.rc;

import com.jiazhe.youxiang.server.vo.BaseVO;

import java.math.BigDecimal;

/**
 * @author TU
 * @description
 * @date 2018/10/23.
 */
public class DirectChargeReq extends BaseVO {

    Integer customerId;

    Integer batchId;

    BigDecimal faceValue;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public BigDecimal getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(BigDecimal faceValue) {
        this.faceValue = faceValue;
    }
}
