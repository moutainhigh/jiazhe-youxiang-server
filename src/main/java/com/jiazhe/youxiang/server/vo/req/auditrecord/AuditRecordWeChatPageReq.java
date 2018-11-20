package com.jiazhe.youxiang.server.vo.req.auditrecord;

import com.jiazhe.youxiang.server.vo.req.PageSizeNumReq;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author TU
 * @description  银行经理请求自己提交的消费记录
 * @date 2018/11/20.
 */
public class AuditRecordWeChatPageReq extends PageSizeNumReq{

    @ApiModelProperty("员工【银行经理】id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
