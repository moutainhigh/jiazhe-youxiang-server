package com.jiazhe.youxiang.server.vo.req;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author tu
 * @description：
 * @date 2018/12/10
 */
public class AdvancePaySaveReq extends BaseVO {

    @ApiModelProperty("预支金额")
    private BigDecimal advancePay ;

    @ApiModelProperty("预支时间")
    private Long advanceTime;

    @ApiModelProperty("备注")
    private String remark;

    public BigDecimal getAdvancePay() {
        return advancePay;
    }

    public void setAdvancePay(BigDecimal advancePay) {
        this.advancePay = advancePay;
    }

    public Long getAdvanceTime() {
        return advanceTime;
    }

    public void setAdvanceTime(Long advanceTime) {
        this.advanceTime = advanceTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
