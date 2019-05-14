package com.jiazhe.youxiang.server.vo.req.material;

import com.jiazhe.youxiang.server.vo.req.PageSizeNumReq;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author TU
 * @description
 * @date 2019-05-14.
 */
public class MaterialPageReq extends PageSizeNumReq{

    @ApiModelProperty("付款人ids")
    private String payerIds;

    @ApiModelProperty("收款人ids")
    private String payeeIds;

    @ApiModelProperty("转账时间起")
    private Long transferTimeBegin;

    @ApiModelProperty("转账时间起")
    private Long transferTimeEnd;

    public String getPayerIds() {
        return payerIds;
    }

    public void setPayerIds(String payerIds) {
        this.payerIds = payerIds;
    }

    public String getPayeeIds() {
        return payeeIds;
    }

    public void setPayeeIds(String payeeIds) {
        this.payeeIds = payeeIds;
    }

    public Long getTransferTimeBegin() {
        return transferTimeBegin;
    }

    public void setTransferTimeBegin(Long transferTimeBegin) {
        this.transferTimeBegin = transferTimeBegin;
    }

    public Long getTransferTimeEnd() {
        return transferTimeEnd;
    }

    public void setTransferTimeEnd(Long transferTimeEnd) {
        this.transferTimeEnd = transferTimeEnd;
    }
}
