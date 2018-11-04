package com.jiazhe.youxiang.server.vo.req.voucher.voucher;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author tu
 * @description：
 * @date 2018/11/4
 */
public class VoucherEditReq extends BaseVO{

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("使用城市的codes")
    private String cityCodes;

    @ApiModelProperty("使用商品的ids")
    private String productIds;

    @ApiModelProperty("代金券名称")
    private String name;

    @ApiModelProperty("描述")
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("过期时间")
    private Date expiryTime;

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

    public Date getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }
}
