/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.req.product;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/19
 */
public class ProductAddReq extends BaseVO {

    private static final long serialVersionUID = -735115738153523093L;
    @ApiModelProperty("商品大类ID，必填")
    private Integer productCategoryId;
    @ApiModelProperty("商品名称，必填")
    private String name;
    @ApiModelProperty("商品描述")
    private String description;
    @ApiModelProperty("商品延迟购买天数，可空")
    private Integer delayDays;
    @ApiModelProperty("商品缩略图url，可空")
    private String thumbnailUrl;
    @ApiModelProperty("商品头部图url，可空")
    private String headerImgUrl;
    @ApiModelProperty("商品详情图url，可空")
    private String detailImgUrl;
    @ApiModelProperty("商品分类，0-服务，1-电子卡，必填")
    private Integer productType;
    @ApiModelProperty("单位名称，可空")
    private String unitName;
    @ApiModelProperty("最少购买数量，可空，默认1")
    private Integer lastNum;
    @ApiModelProperty("短信发送模板 可空")
    private String smsTemplate;
    @ApiModelProperty("有效期天数 可空")
    private Integer effectiveDays;
    @ApiModelProperty("商品排序")
    private Integer priority;

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
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

    public Integer getDelayDays() {
        return delayDays;
    }

    public void setDelayDays(Integer delayDays) {
        this.delayDays = delayDays;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getHeaderImgUrl() {
        return headerImgUrl;
    }

    public void setHeaderImgUrl(String headerImgUrl) {
        this.headerImgUrl = headerImgUrl;
    }

    public String getDetailImgUrl() {
        return detailImgUrl;
    }

    public void setDetailImgUrl(String detailImgUrl) {
        this.detailImgUrl = detailImgUrl;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Integer getLastNum() {
        return lastNum;
    }

    public void setLastNum(Integer lastNum) {
        this.lastNum = lastNum;
    }

    public String getSmsTemplate() {
        return smsTemplate;
    }

    public void setSmsTemplate(String smsTemplate) {
        this.smsTemplate = smsTemplate;
    }

    public Integer getEffectiveDays() {
        return effectiveDays;
    }

    public void setEffectiveDays(Integer effectiveDays) {
        this.effectiveDays = effectiveDays;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}