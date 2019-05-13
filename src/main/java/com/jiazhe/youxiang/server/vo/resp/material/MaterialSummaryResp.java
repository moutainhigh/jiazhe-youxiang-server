package com.jiazhe.youxiang.server.vo.resp.material;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author TU
 * @description
 * @date 2019-05-13.
 */
public class MaterialSummaryResp extends BaseVO {

    @ApiModelProperty("员工id")
    private Integer id ;

    @ApiModelProperty("收款人姓名")
    private String payeeName;

    @ApiModelProperty("收款总额")
    private BigDecimal receiveTotal;

    @ApiModelProperty("采购实物价值")
    private BigDecimal productValueTotal ;

    @ApiModelProperty("消费实物价值")
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