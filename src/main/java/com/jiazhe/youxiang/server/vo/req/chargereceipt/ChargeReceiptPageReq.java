package com.jiazhe.youxiang.server.vo.req.chargereceipt;

import com.jiazhe.youxiang.server.vo.req.PageSizeNumReq;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author TU
 * @description
 * @date 2019-03-19.
 */
public class ChargeReceiptPageReq extends PageSizeNumReq {

    @ApiModelProperty("充值记录id")
    private Integer auditRecordId ;

    public Integer getAuditRecordId() {
        return auditRecordId;
    }

    public void setAuditRecordId(Integer auditRecordId) {
        this.auditRecordId = auditRecordId;
    }
}
