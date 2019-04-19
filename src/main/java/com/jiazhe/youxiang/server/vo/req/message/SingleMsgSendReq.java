package com.jiazhe.youxiang.server.vo.req.message;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author TU
 * @description
 * @date 2019-04-08.
 */
public class SingleMsgSendReq extends BaseVO {

    @ApiModelProperty("类型")
    private Byte type;

    @ApiModelProperty("电话号码")
    private String mobile;

    @ApiModelProperty("主题")
    private String topic;

    @ApiModelProperty("短信模板")
    private int messageTemplateId;

    @ApiModelProperty("短信内容，按照模板用英文分号间隔，必须对应工整")
    private String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getMessageTemplateId() {
        return messageTemplateId;
    }

    public void setMessageTemplateId(int messageTemplateId) {
        this.messageTemplateId = messageTemplateId;
    }
}
