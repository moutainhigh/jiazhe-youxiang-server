package com.jiazhe.youxiang.server.vo.resp.message;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author tu
 * @description：
 * @date 2019-04-07
 */
public class MessageResp extends BaseVO{

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("电话号码")
    private String mobile;

    @ApiModelProperty("短信内容")
    private String content;

    @ApiModelProperty("短信类型 1其他 2业务类 3营销类 4验证码类")
    private Byte type;

    @ApiModelProperty("短信主题")
    private String topic;

    @ApiModelProperty("发送状态 1成功 0失败")
    private Byte status;

    @ApiModelProperty("短信实际发送条数")
    private Byte count;

    @ApiModelProperty("短信服务商 1腾讯 2阿里")
    private Byte serviceProvider;

    @ApiModelProperty("发送时间")
    private long sendTime;

    @ApiModelProperty("操作人")
    private String operatorName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getCount() {
        return count;
    }

    public void setCount(Byte count) {
        this.count = count;
    }

    public Byte getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(Byte serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

}
