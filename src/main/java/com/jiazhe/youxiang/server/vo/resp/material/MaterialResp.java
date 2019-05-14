package com.jiazhe.youxiang.server.vo.resp.material;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author TU
 * @description
 * @date 2019-05-14.
 */
public class MaterialResp extends BaseVO{

    @ApiModelProperty("转账记录id")
    private Integer id;

    @ApiModelProperty("转账时间")
    private Long transferTime;

    @ApiModelProperty("转账金额")
    private BigDecimal transferAmount;

    @ApiModelProperty("物料价值")
    private BigDecimal materialValue;

    @ApiModelProperty("付款人id")
    private Integer payerId;

    @ApiModelProperty("付款人姓名")
    private String payerName;

    @ApiModelProperty("收款人id")
    private Integer payeeId;

    @ApiModelProperty("收款人姓名")
    private String payeeName;

    @ApiModelProperty("备注")
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
