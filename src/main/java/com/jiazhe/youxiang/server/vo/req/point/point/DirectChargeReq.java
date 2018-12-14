package com.jiazhe.youxiang.server.vo.req.point.point;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author TU
 * @description
 * @date 2018/12/14.
 */
public class DirectChargeReq extends BaseVO {

    @ApiModelProperty("充值客户id")
    Integer id;

    @ApiModelProperty("虚拟批次的id")
    Integer batchId;

    @ApiModelProperty("充值积分数")
    BigDecimal faceValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public BigDecimal getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(BigDecimal faceValue) {
        this.faceValue = faceValue;
    }
}
