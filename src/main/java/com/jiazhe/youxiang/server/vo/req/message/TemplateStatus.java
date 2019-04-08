package com.jiazhe.youxiang.server.vo.req.message;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author TU
 * @description
 * @date 2019-04-08.
 */
public class TemplateStatus extends BaseVO {

    @ApiModelProperty("模板可用性")
    private Byte status;

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
