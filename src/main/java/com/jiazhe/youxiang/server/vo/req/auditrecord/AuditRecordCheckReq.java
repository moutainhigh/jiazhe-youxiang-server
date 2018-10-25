package com.jiazhe.youxiang.server.vo.req.auditrecord;

import com.jiazhe.youxiang.server.vo.req.IdReq;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author TU
 * @description
 * @date 2018/10/25.
 */
public class AuditRecordCheckReq extends IdReq {

    @ApiModelProperty("审核理由")
    private String reason ;

    @ApiModelProperty("批次id")
    private Integer rechargeCardCodeBatchId;

    public Integer getRechargeCardCodeBatchId() {
        return rechargeCardCodeBatchId;
    }

    public void setRechargeCardCodeBatchId(Integer rechargeCardCodeBatchId) {
        this.rechargeCardCodeBatchId = rechargeCardCodeBatchId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
