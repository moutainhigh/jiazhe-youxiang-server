package com.jiazhe.youxiang.server.vo.resp.voucher.exchangerecord;

import com.jiazhe.youxiang.server.vo.BaseObject;
import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author TU
 * @description
 * @date 2018/10/24.
 */
public class VoucherExchangeRecordResp extends BaseVO{

    @ApiModelProperty("兑换记录id")
    private Integer id;

    @ApiModelProperty("代金券id")
    private Integer voucherId;

    @ApiModelProperty("代金券兑换码id")
    private Integer exchangeCodeId;

    @ApiModelProperty("兑换类型 0-后台兑换码兑换，1-客户自行兑换码兑换，2-直接充值 ")
    private Integer exchangeType;

    @ApiModelProperty("操作人姓名")
    private String operatorName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Integer voucherId) {
        this.voucherId = voucherId;
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
}
