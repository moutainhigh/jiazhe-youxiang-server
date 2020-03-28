/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.dto.product;

import com.jiazhe.youxiang.server.vo.BaseObject;

import java.util.List;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/18
 */
public class ProductDTO extends BaseObject{
    private static final long serialVersionUID = -4433094311734261831L;
    /**
     * 商品ID
     */
    private Integer id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品描述
     */
    private String description;
    /**
     * 商品延迟购买天数
     */
    private Integer delayDays;
    /**
     * 商品可购买天数
     */
    private Integer bookDays;
    /**
     * 商品缩略图url
     */
    private String thumbnailUrl;
    /**
     * 商品头部图url
     */
    private String headerImgUrl;
    /**
     * 商品详情图url
     */
    private String detailImgUrl;
    /**
     * 商品分类，0-服务，1-电子卡
     */
    private Integer productType;
    /**
     * 单位名称
     */
    private String unitName;
    /**
     * 最少购买数量
     */
    private Integer lastNum;
    /**
     * 状态：0:下架,1:上架
     */
    private Byte status;

    /**
     * 短信发送模板
     */
    private String smsTemplate;

    /**
     * 有效期天数
     */
    private Integer effectiveDays;

    /**
     * 商品类别
     */
    private ProductCategoryDTO productCategory;

    /**
     * 商品价格列表
     */
    private List<ProductPriceDTO> productPriceList;

    /**
     * 商品排序
     */
    private Integer priority;

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

    public Integer getDelayDays() {
        return delayDays;
    }

    public void setDelayDays(Integer delayDays) {
        this.delayDays = delayDays;
    }

    public Integer getBookDays() {
        return bookDays;
    }

    public void setBookDays(Integer bookDays) {
        this.bookDays = bookDays;
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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

    public ProductCategoryDTO getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategoryDTO productCategory) {
        this.productCategory = productCategory;
    }

    public List<ProductPriceDTO> getProductPriceList() {
        return productPriceList;
    }

    public void setProductPriceList(List<ProductPriceDTO> productPriceList) {
        this.productPriceList = productPriceList;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}