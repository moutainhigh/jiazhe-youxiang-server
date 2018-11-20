package com.jiazhe.youxiang.server.vo.req.auditrecord;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author TU
 * @description
 * @date 2018/10/25.
 */
public class AuditRecordCheckReq extends BaseVO {

    @ApiModelProperty("记录id")
    private Integer id ;

    @ApiModelProperty("版本号")
    private Integer version ;


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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
