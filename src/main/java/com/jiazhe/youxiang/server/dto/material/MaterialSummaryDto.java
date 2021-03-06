package com.jiazhe.youxiang.server.dto.material;

import java.math.BigDecimal;

/**
 * @author TU
 * @description
 * @date 2019-05-13.
 */
public class MaterialSummaryDto {

    /**
     * 员工id（收款人id）
     */
    private Integer id;

    /**
     * 收款人姓名
     */
    private String payeeName;

    /**
     * 总收款金额
     */
    private BigDecimal receiveTotal;

    /**
     * 总购买物料价值
     */
    private BigDecimal productValueTotal;

    /**
     * 总消耗物料价值(已审核)
     */
    private BigDecimal usedProductValueTotal;

    /**
     * 总消耗物料价值(已审核)
     */
    private BigDecimal uncheckProductValueTotal;

    /**
     * 剩余物料价值
     */
    private BigDecimal leftProductValueTotal;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public BigDecimal getReceiveTotal() {
        return receiveTotal;
    }

    public void setReceiveTotal(BigDecimal receiveTotal) {
        this.receiveTotal = receiveTotal;
    }

    public BigDecimal getProductValueTotal() {
        return productValueTotal;
    }

    public void setProductValueTotal(BigDecimal productValueTotal) {
        this.productValueTotal = productValueTotal;
    }

    public BigDecimal getUsedProductValueTotal() {
        return usedProductValueTotal;
    }

    public void setUsedProductValueTotal(BigDecimal usedProductValueTotal) {
        this.usedProductValueTotal = usedProductValueTotal;
    }

    public BigDecimal getLeftProductValueTotal() {
        return leftProductValueTotal;
    }

    public void setLeftProductValueTotal(BigDecimal leftProductValueTotal) {
        this.leftProductValueTotal = leftProductValueTotal;
    }

    public BigDecimal getUncheckProductValueTotal() {
        return uncheckProductValueTotal;
    }

    public void setUncheckProductValueTotal(BigDecimal uncheckProductValueTotal) {
        this.uncheckProductValueTotal = uncheckProductValueTotal;
    }
}
