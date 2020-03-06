package com.jiazhe.youxiang.server.domain.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ChargeOffPointPO implements Serializable {
    private Integer id;

    private Integer chargeOffId;

    private Integer pointExchangeCodeId;

    private String pointName;

    private BigDecimal pointValue;

    private String pointExchangeCodeCode;

    private String pointExchangeCodeKeyt;

    private String extInfo;

    private Byte isDeleted;

    private Date addTime;

    private Date modTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getChargeOffId() {
        return chargeOffId;
    }

    public void setChargeOffId(Integer chargeOffId) {
        this.chargeOffId = chargeOffId;
    }

    public Integer getPointExchangeCodeId() {
        return pointExchangeCodeId;
    }

    public void setPointExchangeCodeId(Integer pointExchangeCodeId) {
        this.pointExchangeCodeId = pointExchangeCodeId;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName == null ? null : pointName.trim();
    }

    public BigDecimal getPointValue() {
        return pointValue;
    }

    public void setPointValue(BigDecimal pointValue) {
        this.pointValue = pointValue;
    }

    public String getPointExchangeCodeCode() {
        return pointExchangeCodeCode;
    }

    public void setPointExchangeCodeCode(String pointExchangeCodeCode) {
        this.pointExchangeCodeCode = pointExchangeCodeCode == null ? null : pointExchangeCodeCode.trim();
    }

    public String getPointExchangeCodeKeyt() {
        return pointExchangeCodeKeyt;
    }

    public void setPointExchangeCodeKeyt(String pointExchangeCodeKeyt) {
        this.pointExchangeCodeKeyt = pointExchangeCodeKeyt == null ? null : pointExchangeCodeKeyt.trim();
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo == null ? null : extInfo.trim();
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getModTime() {
        return modTime;
    }

    public void setModTime(Date modTime) {
        this.modTime = modTime;
    }
}