package com.jiazhe.youxiang.server.dto.point.pointexchangerecord;

import com.jiazhe.youxiang.server.vo.BaseObject;

import java.util.Date;

/**
 * @author tu
 * @description：
 * @date 2018/12/13
 */
public class PointExchangeRecordDTO extends BaseObject {

    private static final long serialVersionUID = 7340559057415927111L;

    private Integer id;

    private Integer pointId;

    private Integer exchangeCodeId;

    private Integer exchangeType;

    private Integer operatorId;

    private String operatorName;

    private Date addTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPointId() {
        return pointId;
    }

    public void setPointId(Integer pointId) {
        this.pointId = pointId;
    }

    public Integer getExchangeCodeId() {
        return exchangeCodeId;
    }

    public void setExchangeCodeId(Integer exchangeCodeId) {
        this.exchangeCodeId = exchangeCodeId;
    }

    public Integer getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(Integer exchangeType) {
        this.exchangeType = exchangeType;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
