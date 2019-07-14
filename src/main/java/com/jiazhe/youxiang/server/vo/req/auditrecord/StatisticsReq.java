package com.jiazhe.youxiang.server.vo.req.auditrecord;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author tu
 * @description：小程序端统计自己在某个周期提交的记录
 * @date 2019-07-14
 */
public class StatisticsReq extends BaseVO{

    @ApiModelProperty("提交时间起")
    private Long submitStartTime;

    @ApiModelProperty("提交时间止")
    private Long submitEndTime;

    public Long getSubmitStartTime() {
        return submitStartTime;
    }

    public void setSubmitStartTime(Long submitStartTime) {
        this.submitStartTime = submitStartTime;
    }

    public Long getSubmitEndTime() {
        return submitEndTime;
    }

    public void setSubmitEndTime(Long submitEndTime) {
        this.submitEndTime = submitEndTime;
    }
}
