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
}
