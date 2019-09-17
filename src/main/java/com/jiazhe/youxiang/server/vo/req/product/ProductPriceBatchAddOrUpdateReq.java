/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.req.product;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/19
 */
public class ProductPriceBatchAddOrUpdateReq extends BaseVO {

    private static final long serialVersionUID = 8358796229274962283L;
    @ApiModelProperty("城市code集合,必填，不可空")
    private List<String> cityCodes;

    @ApiModelProperty("商品id,必填，不可空")
    private Integer productId;

    @ApiModelProperty("价格,必填，不可空")
    private BigDecimal price;

    public List<String> getCityCodes() {
        return cityCodes;
    }

    public void setCityCodes(List<String> cityCodes) {
        this.cityCodes = cityCodes;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}