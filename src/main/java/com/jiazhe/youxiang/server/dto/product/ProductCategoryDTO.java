/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.dto.product;

/**
 * 商品分类DTO
 *
 * @author niexiao
 * @created 2018/10/18
 */
public class ProductCategoryDTO {

    /**
     * 商品分类ID
     */
    private Integer id;
    /**
     * 商品分类名称
     */
    private String name;
    /**
     * 商品分类描述
     */
    private String description;
    /**
     * 缩略图url
     */
    private String thumbnailUrl;
    /**
     * 详情图url
     */
    private String detailImgUrl;
    /**
     * 排序
     */
    private Integer priority;
    /**
     * 商品分类上架状态 0:下架,1:上架
     */
    private Integer status;

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

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getDetailImgUrl() {
        return detailImgUrl;
    }

    public void setDetailImgUrl(String detailImgUrl) {
        this.detailImgUrl = detailImgUrl;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}