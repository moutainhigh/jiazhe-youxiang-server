package com.jiazhe.youxiang.server.vo.req.voucher.exchangecodebatch;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author tu
 * @description：
 * @date 2018/11/3
 */
public class VoucherExchangeCodeBatchSaveReq  extends BaseVO{

    @ApiModelProperty("批次id，0为添加 其他为修改")
    private Integer id;

    @ApiModelProperty("批次名称")
    private String name;

    @ApiModelProperty("兑换成代金券的名称")
    private String voucherName;

    @ApiModelProperty("兑换码数量")
    private Integer amount;

    @ApiModelProperty("可兑换数量")
    private Integer count;

    @ApiModelProperty("对应项目id")
    private Integer projectId;

    @ApiModelProperty("对应城市codes")
    private String cityCodes;

    @ApiModelProperty("对应商品ids")
    private String productIds;

    @ApiModelProperty("批次过期时间")
    private Long expiryTime;

    @ApiModelProperty("代金券生效时间")
    private Long voucherEffectiveTime;

    @ApiModelProperty("代金券过期时间")
    private Long voucherExpiryTime;

    @ApiModelProperty("自兑换之日起，代金券**天内有效")
    private Integer validityPeriod;

    @ApiModelProperty("代金券过期类型")
    private Byte expiryType;

    @ApiModelProperty("描述信息")
    private String description;

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

    public String getVoucherName() {
        return voucherName;
    }

    public void setVoucherName(String voucherName) {
        this.voucherName = voucherName;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
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

    public Long getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Long expiryTime) {
        this.expiryTime = expiryTime;
    }

    public Long getVoucherExpiryTime() {
        return voucherExpiryTime;
    }

    public void setVoucherExpiryTime(Long voucherExpiryTime) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getVoucherEffectiveTime() {
        return voucherEffectiveTime;
    }

    public void setVoucherEffectiveTime(Long voucherEffectiveTime) {
        this.voucherEffectiveTime = voucherEffectiveTime;
    }
}
