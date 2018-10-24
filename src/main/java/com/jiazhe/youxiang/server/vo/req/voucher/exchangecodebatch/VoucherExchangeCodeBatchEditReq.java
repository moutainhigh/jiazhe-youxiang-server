package com.jiazhe.youxiang.server.vo.req.voucher.exchangecodebatch;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author TU
 * @description
 * @date 2018/10/24.
 */
public class VoucherExchangeCodeBatchEditReq {

    @ApiModelProperty("代金券兑换码批次id")
    private Integer id;

    @ApiModelProperty("代金券兑换码批次名称")
    private String name;

    @ApiModelProperty("描述信息")
    private String description;

    @ApiModelProperty("城市ids，用逗号连接")
    private String cityIds;

    @ApiModelProperty("商品ids，用逗号连接")
    private String productIds;

    @ApiModelProperty("代金券兑换码批次过期时间")
    private Date expiryTime;

    @ApiModelProperty("兑换后，代金券过期时间")
    private Date voucherExpiryTime;

    @ApiModelProperty("自兑换之日起，代金券***天内有效")
    private Integer validityPeriod;

    @ApiModelProperty("代金券过期类型")
    private Byte expiryType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCityIds() {
        return cityIds;
    }

    public void setCityIds(String cityIds) {
        this.cityIds = cityIds;
    }

    public String getProductIds() {
        return productIds;
    }

    public void setProductIds(String productIds) {
        this.productIds = productIds;
    }

    public Date getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }

    public Date getVoucherExpiryTime() {
        return voucherExpiryTime;
    }

    public void setVoucherExpiryTime(Date voucherExpiryTime) {
        this.voucherExpiryTime = voucherExpiryTime;
    }

    public Integer getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(Integer validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public Byte getExpiryType() {
        return expiryType;
    }

    public void setExpiryType(Byte expiryType) {
        this.expiryType = expiryType;
    }
}
