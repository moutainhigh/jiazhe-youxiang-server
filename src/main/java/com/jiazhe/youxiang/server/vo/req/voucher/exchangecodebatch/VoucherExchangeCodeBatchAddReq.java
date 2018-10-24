package com.jiazhe.youxiang.server.vo.req.voucher.exchangecodebatch;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author TU
 * @description
 * @date 2018/10/24.
 */
public class VoucherExchangeCodeBatchAddReq {

    @ApiModelProperty("代金券兑换码批次名称")
    private String name;

    @ApiModelProperty("描述信息")
    private String description;

    @ApiModelProperty("数量")
    private Integer amount;

    @ApiModelProperty("城市ids，用逗号连接")
    private String cityIds;

    @ApiModelProperty("商品ids，用逗号连接")
    private String productIds;

    @ApiModelProperty("可抵扣商品数量")
    private Integer count;

    @ApiModelProperty("码过期时间")
    private Date expiryTime;

    @ApiModelProperty("兑换后，代金券过期时间")
    private Date voucherExpiryTime;

    @ApiModelProperty("自兑换之日起，代价券***天内有效")
    private Integer validityPeriod;

    @ApiModelProperty("过期类型")
    private Byte expiryType;

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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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
