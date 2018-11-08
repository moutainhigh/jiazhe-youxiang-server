package com.jiazhe.youxiang.server.vo.req.voucher.voucher;

import com.jiazhe.youxiang.server.vo.req.PageSizeNumReq;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author TU
 * @description
 * @date 2018/11/8.
 */
public class VoucherGoodsAttrPageReq extends PageSizeNumReq {

    @ApiModelProperty("客户id")
    private Integer customerId;

    @ApiModelProperty("商品id")
    private Integer productId;

    @ApiModelProperty("城市code")
    private String cityCode;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
}
