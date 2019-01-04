package com.jiazhe.youxiang.server.vo.req.rechargecard.rc;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author TU
 * @description
 * @date 2018/11/2.
 */
public class RCEditReq extends BaseVO {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("使用城市的codes")
    private String cityCodes;

    @ApiModelProperty("使用商品的ids")
    private String productIds;

    @ApiModelProperty("充值卡名称")
    private String name;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("生效时间")
    private Long effectiveTime;

    @ApiModelProperty("过期时间")
    private Long expiryTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityCodes() {
        return cityCodes;
    }

    public void setCityCodes(String cityCodes) {
        this.cityCodes = cityCodes;
    }

    public String getProductIds() {
        return productIds;
    }

    public void setProductIds(String productIds) {
        this.productIds = productIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Long expiryTime) {
        this.expiryTime = expiryTime;
    }

    public Long getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Long effectiveTime) {
        this.effectiveTime = effectiveTime;
    }
}
