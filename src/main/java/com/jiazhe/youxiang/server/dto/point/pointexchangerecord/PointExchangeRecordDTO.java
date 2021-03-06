package com.jiazhe.youxiang.server.dto.point.pointexchangerecord;

import com.jiazhe.youxiang.server.vo.BaseObject;
import org.apache.commons.lang.StringUtils;

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

    private String extInfo;

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

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }

    public String getReceiptInfo() {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotEmpty(this.extInfo)) {
            String[] s = this.extInfo.split(",");
            if (s != null && s.length == 7 && StringUtils.isNotEmpty(s[3]) && StringUtils.isNotEmpty(s[4]) && StringUtils.isNotEmpty(s[5])) {
                String cardNo = s[3];
                cardNo = cardNo.substring(cardNo.lastIndexOf('*') + 1);
                sb.append(cardNo);
                sb.append(',');
                sb.append(s[4]);
                sb.append(',');
                sb.append(s[5]);
            }
        }
        return sb.toString();
    }


    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
