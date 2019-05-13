package com.jiazhe.youxiang.server.dto.material;

import java.math.BigDecimal;

/**
 * @author TU
 * @description
 * @date 2019-05-13.
 */
public class MaterialSummaryDto {

    private Integer id ;

    private String payeeName;

    private BigDecimal receiveTotal;

    private BigDecimal productValueTotal ;

    private BigDecimal usedProductValueTotal ;

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

}
