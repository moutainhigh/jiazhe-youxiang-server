/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.resp.syscity;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * 城市信息Resp
 *
 * @author niexiao
 * @created 2018/10/16
 */
public class SysCityResp extends BaseVO {

    private static final long serialVersionUID = -9088027419739735153L;
    @ApiModelProperty("城市ID")
    private Integer id;
    @ApiModelProperty("城市编码")
    private String cityCode;
    @ApiModelProperty("城市名称")
    private String cityName;
    @ApiModelProperty("城市拼音")
    private String cityPinyin;
    @ApiModelProperty("城市级别")
    private Integer cityLevel;
    @ApiModelProperty("排序")
    private Integer priority;
    @ApiModelProperty("上级城市Code")
    private String parentCode;
    @ApiModelProperty("城市开通状况")
    private Byte status;

    public String getCityCode() {
        return cityCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityPinyin() {
        return cityPinyin;
    }

    public void setCityPinyin(String cityPinyin) {
        this.cityPinyin = cityPinyin;
    }

    public Integer getCityLevel() {
        return cityLevel;
    }

    public void setCityLevel(Integer cityLevel) {
        this.cityLevel = cityLevel;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}