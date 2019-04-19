package com.jiazhe.youxiang.server.vo.resp.message;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author TU
 * @description
 * @date 2019-04-12.
 */
public class MessageTextResp extends BaseVO {

    @ApiModelProperty("电话号码")
    private String mobile;

    @ApiModelProperty("短信内容")
    private String content;

    @ApiModelProperty("短信检查情况 0不合格 1合格")
    private Byte valid;

    @ApiModelProperty("不合格理由")
    private String errMsg;

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

    public Byte getValid() {
        return valid;
    }

    public void setValid(Byte valid) {
        this.valid = valid;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
