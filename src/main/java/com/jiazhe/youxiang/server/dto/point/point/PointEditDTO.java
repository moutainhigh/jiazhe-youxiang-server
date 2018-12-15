package com.jiazhe.youxiang.server.dto.point.point;

import com.jiazhe.youxiang.server.vo.BaseObject;

import java.util.Date;

/**
 * @author TU
 * @description
 * @date 2018/12/14.
 */
public class PointEditDTO extends BaseObject{

    private static final long serialVersionUID = 4886392410987290548L;

    private Integer id;

    private String cityCodes;

    private String productIds;

    private String name;

    private String description;

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
