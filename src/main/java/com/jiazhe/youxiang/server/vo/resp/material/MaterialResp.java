package com.jiazhe.youxiang.server.vo.resp.material;

import com.jiazhe.youxiang.server.vo.BaseVO;

import java.math.BigDecimal;

/**
 * @author TU
 * @description
 * @date 2019-05-14.
 */
public class MaterialResp extends BaseVO{

    private Integer id;

    private Long transferTime;

    private BigDecimal transferAmount;

    private BigDecimal materialValue;

    private Integer payerId;

    private String payerName;

    private Integer payeeId;

    private String payeeName;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(Long transferTime) {
        this.transferTime = transferTime;
    }

    public BigDecimal getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(BigDecimal transferAmount) {
        this.transferAmount = transferAmount;
    }

    public BigDecimal getMaterialValue() {
        return materialValue;
    }

    public void setMaterialValue(BigDecimal materialValue) {
        this.materialValue = materialValue;
    }

    public Integer getPayerId() {
        return payerId;
    }

    public void setPayerId(Integer payerId) {
        this.payerId = payerId;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public Integer getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(Integer payeeId) {
        this.payeeId = payeeId;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
