package com.jiazhe.youxiang.server.vo.req.message;

import com.jiazhe.youxiang.server.vo.req.PageSizeNumReq;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author tu
 * @description：
 * @date 2019-04-07
 */
public class MessagePageReq extends PageSizeNumReq {

    @ApiModelProperty("发送状态")
    private Byte status;

    @ApiModelProperty("类型")
    private Byte type;

    @ApiModelProperty("电话号码")
    private String mobile;

    @ApiModelProperty("主题")
    private String topic;

    @ApiModelProperty("发送时间起")
    private long sendStartTime;

    @ApiModelProperty("发送时间止")
    private long sendEndTime;

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public long getSendStartTime() {
        return sendStartTime;
    }

    public void setSendStartTime(long sendStartTime) {
        this.sendStartTime = sendStartTime;
    }

    public long getSendEndTime() {
        return sendEndTime;
    }

    public void setSendEndTime(long sendEndTime) {
        this.sendEndTime = sendEndTime;
    }
}
