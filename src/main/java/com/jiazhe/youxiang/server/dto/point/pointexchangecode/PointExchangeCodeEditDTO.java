package com.jiazhe.youxiang.server.dto.point.pointexchangecode;

import com.jiazhe.youxiang.server.vo.BaseObject;

import java.util.Date;

/**
 * @author TU
 * @description
 * @date 2018/12/14.
 */
public class PointExchangeCodeEditDTO extends BaseObject {

    private static final long serialVersionUID = 6287663046896637706L;

    private Integer id;

    private String pointName;

    private String batchDescription;

    private String cityCodes;

    private String productIds;

    private Date expiryTime;

    private Date pointExpiryTime;

    private Integer validityPeriod;

    private Byte expiryType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public String getBatchDescription() {
        return batchDescription;
    }

    public void setBatchDescription(String batchDescription) {
        this.batchDescription = batchDescription;
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

    public Date getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }

    public Date getPointExpiryTime() {
        return pointExpiryTime;
    }

    public void setPointExpiryTime(Date pointExpiryTime) {
        this.pointExpiryTime = pointExpiryTime;
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
