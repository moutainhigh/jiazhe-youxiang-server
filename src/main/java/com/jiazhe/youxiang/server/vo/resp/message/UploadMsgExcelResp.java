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

    @ApiModelProperty("短信数量")
    private Integer count;

    @ApiModelProperty("检查状态")
    private Byte success;

    @ApiModelProperty("错误信息")
    private String errMsg;

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

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public List<MessageTextResp> getMsgTxtRespList() {
        return msgTxtRespList;
    }

    public void setMsgTxtRespList(List<MessageTextResp> msgTxtRespList) {
        this.msgTxtRespList = msgTxtRespList;
    }
}
