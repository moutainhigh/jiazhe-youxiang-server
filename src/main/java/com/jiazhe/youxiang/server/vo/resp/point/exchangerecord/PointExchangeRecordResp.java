package com.jiazhe.youxiang.server.vo.resp.point.exchangerecord;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author TU
 * @description
 * @date 2018/12/14.
 */
public class PointExchangeRecordResp extends BaseVO{

    @ApiModelProperty("兑换记录id")
    private Integer id;

    @ApiModelProperty("兑换码id")
    private Integer exchangeCodeId;

    @ApiModelProperty("兑换类型，0-后台兑换，1-客户兑换，2-直接充值")
    private Integer exchangeType;

    @ApiModelProperty("操作人姓名")
    private String operatorName;

    @ApiModelProperty("兑换记录生成时间")
    private Long addTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }
}
