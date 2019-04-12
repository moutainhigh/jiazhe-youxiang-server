package com.jiazhe.youxiang.server.vo.resp.message;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author TU
 * @description
 * @date 2019-04-12.
 */
public class UploadMsgExcelResp extends BaseVO {

    private static final long serialVersionUID = -4334860619685414521L;

    @ApiModelProperty("excel路径")
    private String url;

    @ApiModelProperty("短信总数量")
    private Integer count;

    @ApiModelProperty("短信合法数量")
    private Integer validCount;

    @ApiModelProperty("检查状态，只要有一条信息不合格就为0")
    private Byte success;

    @ApiModelProperty("短息内容")
    private List<MessageTextResp> msgTxtRespList;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Byte getSuccess() {
        return success;
    }

    public void setSuccess(Byte success) {
        this.success = success;
    }

    public List<MessageTextResp> getMsgTxtRespList() {
        return msgTxtRespList;
    }

    public void setMsgTxtRespList(List<MessageTextResp> msgTxtRespList) {
        this.msgTxtRespList = msgTxtRespList;
    }

    public Integer getValidCount() {
        return validCount;
    }

    public void setValidCount(Integer validCount) {
        this.validCount = validCount;
    }
}
