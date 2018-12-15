package com.jiazhe.youxiang.server.vo.req.point.point;

import com.jiazhe.youxiang.server.vo.req.PageSizeNumReq;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author TU
 * @description
 * @date 2018/12/14.
 */
public class PointGoodsAttrPageReq extends PageSizeNumReq{

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
