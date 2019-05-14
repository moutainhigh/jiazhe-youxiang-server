package com.jiazhe.youxiang.server.dto.material;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author TU
 * @description
 * @date 2019-05-14.
 */
public class MaterialDto {

    /**
     * 转账记录id
     */
    private Integer id;

    /**
     * 转账时间
     */
    private Date transferTime;

    /**
     * 转账金额
     */
    private BigDecimal transferAmount;

    /**
     * 物料价值
     */
    private BigDecimal materialValue;

    /**
     * 付款人id
     */
    private Integer payerId;

    /**
     * 付款人姓名
     */
    private String payerName;

    /**
     * 收款人id
     */
    private Integer payeeId;

    /**
     * 收款人姓名
     */
    private String payeeName;

    /**
     * 备注
     */
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(Date transferTime) {
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
