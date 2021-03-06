/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.resp.product;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * 商品大类Resp
 *
 * @author niexiao
 * @created 2018/10/18
 */
public class ProductCategoryResp extends BaseVO {

    private static final long serialVersionUID = -1302754758059505012L;
    @ApiModelProperty("商品大类ID")
    private Integer id;
    @ApiModelProperty("商品大类名称")
    private String name;
    @ApiModelProperty("商品大类描述")
    private String description;
    @ApiModelProperty("缩略图url")
    private String thumbnailUrl;
    @ApiModelProperty("详情图url")
    private String detailImgUrl;
    @ApiModelProperty("排序")
    private Integer priority;
    @ApiModelProperty("商品大类上架状态 0:下架,1:上架")
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