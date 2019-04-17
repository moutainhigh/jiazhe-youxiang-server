package com.jiazhe.youxiang.server.vo.req.message;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author TU
 * @description
 * @date 2019-04-15.
 */
public class BatchMsgSendReq extends BaseVO {

    @ApiModelProperty("类型")
    private Byte type;

    @ApiModelProperty("主题")
    private String topic;

    @ApiModelProperty("短信模板")
    private int messageTemplateId;

    @ApiModelProperty("excel的路径")
    private String excelUrl;

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

    public int getMessageTemplateId() {
        return messageTemplateId;
    }

    public void setMessageTemplateId(int messageTemplateId) {
        this.messageTemplateId = messageTemplateId;
    }

    public String getExcelUrl() {
        return excelUrl;
    }

    public void setExcelUrl(String excelUrl) {
        this.excelUrl = excelUrl;
    }
}
